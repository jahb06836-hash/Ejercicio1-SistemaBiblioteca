package edu.itvo.sistemabiblioteca


import models.Book
import models.User
import logic.Loanvalidator
import logic.Librarysystem
import org.junit.Test
import org.junit.Assert.*

class BibliotecaTest {

    @Test
    fun `ValidarQueUnUsuarioNoPuedaTenerMasDe3Libros`() {
        // Preparación
        val validador = Loanvalidator()
        val listaLibros = mutableListOf<Book>() // Catálogo vacío para la prueba
        val sistema = Librarysystem(listaLibros, validador)
        val usuario = User("1", "Jorge Alberto")

        // Simulamos 3 préstamos exitosos
        val l1 = Book("Libro 1", "Autor", "101")
        val l2 = Book("Libro 2", "Autor", "102")
        val l3 = Book("Libro 3", "Autor", "103")
        val l4 = Book("Libro 4", "Autor", "104")

        sistema.realizarPrestamo(usuario, l1)
        sistema.realizarPrestamo(usuario, l2)
        sistema.realizarPrestamo(usuario, l3)

        // Acción: Intentar el cuarto préstamo
        val pudoPrestarCuarto = sistema.realizarPrestamo(usuario, l4)

        // Verificación: Debe ser falso
        assertFalse("El usuario no debería poder prestar un cuarto libro", pudoPrestarCuarto)
    }



    @Test
    fun `NoDebePrestarUnLibroQueYaNoEstaDisponible`() {
        val validador = Loanvalidator()
        val libro = Book("Kotlin para Expertos", "Autor X", "999")
        val sistema = Librarysystem(listOf(libro), validador)

        val usuario1 = User("1", "Jorge")
        val usuario2 = User("2", "Jimena")

        // Prestamos el libro al primer usuario
        sistema.realizarPrestamo(usuario1, libro)

        // Intentamos prestárselo al segundo
        val pudoPrestarAUser2 = sistema.realizarPrestamo(usuario2, libro)

        assertFalse("No se debería prestar un libro que ya está ocupado", pudoPrestarAUser2)
    }


    @Test
    fun `MostrarLibrosdisponiblesenconsola`() {
        val validador = Loanvalidator()
        val libro1 = Book("Kotlin Clean Code", "Robert C.", "101", disponible = true)
        val libro2 = Book("Android Avanzado", "Google Professional", "102", disponible = false)
        val libro3 = Book("Principios SOLID", "Uncle Bob", "103", disponible = true)

        val catalogo = listOf(libro1, libro2, libro3)
        val sistema = Librarysystem(catalogo, validador)

        val disponibles = sistema.obtenerDisponibles()

        println("--- LISTA DE LIBROS DISPONIBLES ---")
        disponibles.forEach { libro ->
            println("Título: ${libro.titulo} | Autor: ${libro.autor} | ISBN: ${libro.isbn}")
        }
        println("-----------------------------------")

        // Verificación: Deberían aparecer solo 2 libros (el 1 y el 3)
        assertEquals(2, disponibles.size)
    }
}

