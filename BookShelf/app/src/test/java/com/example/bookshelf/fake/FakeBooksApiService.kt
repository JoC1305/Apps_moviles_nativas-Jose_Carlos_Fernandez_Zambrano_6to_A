package com.example.bookshelf.fake

import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.BookSearchResponse
import com.example.bookshelf.network.BooksApiService

/**
 * Implementación simulada (Fake) de [BooksApiService] para pruebas de red sin conexión.
 */
class FakeBooksApiService : BooksApiService {

    override suspend fun searchBooks(query: String, apiKey: String?): BookSearchResponse {
        return FakeDataSource.bookSearchResponse
    }

    override suspend fun getBook(id: String, apiKey: String?): BookItem {
        return FakeDataSource.bookSearchResponse.items?.find { it.id == id }
            ?: BookItem(id = id, volumeInfo = null)
    }
}
