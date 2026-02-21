package interfaces

import models.Book
import models.User

interface ILibraryactions {
    fun realizarPrestamo(usuario: User, libro: Book): Boolean
    fun realizarDevolucion(usuario: User, libro: Book)
    fun obtenerDisponibles(): List<Book>
}