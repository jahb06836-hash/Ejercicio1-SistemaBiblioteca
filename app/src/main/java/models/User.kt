package models

data class User(
    val id: String,
    val nombre: String,
    val librosPrestados: MutableList<Book> = mutableListOf())