package models

data class Loan(
    val libro: Book,
    val usuario: User,
    val fecha: String)