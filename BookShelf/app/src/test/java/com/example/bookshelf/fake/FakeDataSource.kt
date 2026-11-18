package com.example.bookshelf.fake

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.BookItem
import com.example.bookshelf.model.BookSearchResponse
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.model.VolumeInfo

/**
 * Datos simulados para las pruebas unitarias de Bookshelf.
 */
object FakeDataSource {
    const val idOne = "book1"
    const val idTwo = "book2"
    const val titleOne = "Title One"
    const val titleTwo = "Title Two"
    const val imgOne = "https://books.google.com/thumbnail1.jpg"
    const val imgTwo = "https://books.google.com/thumbnail2.jpg"

    val bookSearchResponse = BookSearchResponse(
        totalItems = 2,
        items = listOf(
            BookItem(
                id = idOne,
                volumeInfo = VolumeInfo(
                    title = titleOne,
                    imageLinks = ImageLinks(thumbnail = imgOne)
                )
            ),
            BookItem(
                id = idTwo,
                volumeInfo = VolumeInfo(
                    title = titleTwo,
                    imageLinks = ImageLinks(thumbnail = imgTwo)
                )
            )
        )
    )

    val booksList = listOf(
        Book(
            id = idOne,
            title = titleOne,
            imgSrc = imgOne
        ),
        Book(
            id = idTwo,
            title = titleTwo,
            imgSrc = imgTwo
        )
    )
}
