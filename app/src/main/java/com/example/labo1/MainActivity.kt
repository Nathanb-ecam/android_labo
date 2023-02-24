package com.example.labo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.labo1.ui.theme.Labo1Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labo1Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Navigation()
                }
            }
        }
    }
}


@Composable
fun QuoteContentScreen(content :String?){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text=content!!, fontSize = 17.sp)
    }

}
@Composable
fun QuotesScreen(
    navController: NavController,
    appViewModel: QuotesViewModel = viewModel()
){
    val uiState by appViewModel.uiState.collectAsState()
    appViewModel.getApiQuotes()
    Labo1Theme{
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ){
            QuoteList(quotes = uiState.quotes,navController)
        }
    }
}



@Composable
fun QuoteList(quotes:List<Quote>,navController: NavController){
    LazyColumn{
        items(quotes) { quote ->
            QuoteCard(quote, navController)
        }
    }
}

@Composable
fun QuoteCard(quote:Quote,navController: NavController,modifier: Modifier=Modifier){
    Card(modifier = modifier.padding(0.dp), elevation = 0.dp){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            ClickableText(
                text = AnnotatedString(quote.author) ,
                style = TextStyle(fontSize = 20.sp),
                onClick = {navController.navigate(Screen.ContentScreen.withArgs(quote.content))},// need to navigate to content of the quote
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Labo1Theme {
//        QuoteCard(Quote("Mamene","I believe i can fly"))
//        QuoteList(Datasource().fetchQuotes())
        Navigation()
    }
}