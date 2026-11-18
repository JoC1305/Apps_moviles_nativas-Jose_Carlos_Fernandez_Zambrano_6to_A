package com.example.bookshelf

import com.example.bookshelf.data.NetworkBooksRepository
import com.example.bookshelf.fake.FakeBooksApiService
import com.example.bookshelf.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkBooksRepositoryTest {

    @Test
    fun networkBooksRepository_getBooks_verifyBooksList() = runTest {
        val repository = NetworkBooksRepository(
            booksApiService = FakeBooksApiService()
        )
        // Verifica que realice la búsqueda y obtenga los libros con sus miniaturas
        assertEquals(FakeDataSource.booksList, repository.getBooks("jazz+history"))
    }

    @Test
    fun networkBooksRepository_getBook_verifySingleBook() = runTest {
        val repository = NetworkBooksRepository(
            booksApiService = FakeBooksApiService()
        )
        // Verifica la solicitud individual para un libro específico por su ID
        val expectedBook = FakeDataSource.booksList[0]
        assertEquals(expectedBook, repository.getBook(FakeDataSource.idOne))
    }
}
