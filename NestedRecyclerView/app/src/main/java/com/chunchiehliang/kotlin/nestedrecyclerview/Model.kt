package com.chunchiehliang.kotlin.nestedrecyclerview


data class Movie(val id: Int, val title: String, val genres: List<Genre>)

data class Genre(val id: Int, val name: String)