package com.example.labo1

sealed class Screen(val route:String) {
    object QuotesScreen : Screen("quotes_screen")
    object ContentScreen : Screen("content_screen")

    fun withArgs(vararg args :String):String{
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}