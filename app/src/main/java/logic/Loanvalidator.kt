package logic

import models.Book
import models.User

class Loanvalidator {
    // Resumen: Verifica que el libro esté libre y el usuario tenga menos de 3 libros.
    fun esValido(u: User, l: Book): Boolean {
        return l.disponible && u.librosPrestados.size < 3
    }

    fun puedeRealizarPrestamo(usuario: User, libro: Book): Boolean {
        // Regla: El libro debe estar disponible Y el usuario debe tener cupo (< 3)
        return libro.disponible && usuario.librosPrestados.size < 3
    }
}

