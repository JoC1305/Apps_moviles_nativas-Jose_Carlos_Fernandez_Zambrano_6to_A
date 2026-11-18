package com.example.bookshelf.fake

import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.Book

/**
 * Implementación simulada (Fake) de [BooksRepository] para pruebas del ViewModel.
 */
class FakeNetworkBooksRepository : BooksRepository {

    override suspend fun getBooks(query: String): List<Book> {
        return FakeDataSource.booksList
    }

    override suspend fun getBook(id: String): Book? {
        return FakeDataSource.booksList.find { it.id == id }
    }
}
