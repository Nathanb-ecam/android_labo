package com.example.labo1




class Datasource {
    fun fetchQuotes():List<Quote>{
        return listOf<Quote>(
            Quote("Otis","Je crois qu'il y ai de bnne ou de mauvaises rencontres"),
            Quote("Wilfried","Qui roule a vélo prendra le métro"),
            Quote("Jean","Je viens de nice, je suis surfeur")
        );
    }

}