package logic
import interfaces.ILibraryactions
import models.Book
import models.User

class Librarysystem(
    private val catalogo: List<Book>,
    private val validador: Loanvalidator
) : ILibraryactions {

    override fun realizarPrestamo(usuario: User, libro: Book): Boolean {
        if (validador.puedeRealizarPrestamo(usuario, libro)) {
            libro.disponible = false
            usuario.librosPrestados.add(libro)
            return true
        }
        return false
    }

    override fun realizarDevolucion(usuario: User, libro: Book) {
        libro.disponible = true
        usuario.librosPrestados.remove(libro)
    }

    // Requisito: Mostrar libros disponibles
    override fun obtenerDisponibles(): List<Book> {
        return catalogo.filter { it.disponible }
    }

    // Requisito: Mostrar libros en préstamo
    fun obtenerPrestados(): List<Book> {
        return catalogo.filter { !it.disponible }
    }
}