package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.toBook
import com.example.bookshelf.network.BooksApiService


interface BooksRepository {
    suspend fun getBooks(query: String = "jazz+history"): List<Book>

    suspend fun getBook(id: String): Book?
}

class NetworkBooksRepository(
    private val booksApiService: BooksApiService,
    private val apiKey: String? = null
) : BooksRepository {

    override suspend fun getBooks(query: String): List<Book> {
        // 1. Primera solicitud: buscar libros y obtener lista de IDs
        val searchResponse = booksApiService.searchBooks(query, apiKey)
        val items = searchResponse.items ?: return emptyList()

        // 2. Solicitudes individuales: obtener datos de cada libro por su ID
        val books = mutableListOf<Book>()
        for (item in items) {
            try {
                val bookDetails = booksApiService.getBook(item.id, apiKey)
                books.add(bookDetails.toBook())
            } catch (e: Exception) {
                // En caso de error en un libro individual, se usa la información de búsqueda
                books.add(item.toBook())
            }
        }
        return books
    }

    override suspend fun getBook(id: String): Book? {
        return try {
            booksApiService.getBook(id, apiKey).toBook()
        } catch (e: Exception) {
            null
        }
    }
}
