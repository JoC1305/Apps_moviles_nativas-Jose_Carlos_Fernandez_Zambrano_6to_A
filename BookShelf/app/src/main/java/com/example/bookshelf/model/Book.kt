package com.example.bookshelf.model

import com.google.gson.annotations.SerializedName

data class BookSearchResponse(
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("totalItems")
    val totalItems: Int = 0,
    @SerializedName("items")
    val items: List<BookItem>? = null
)

data class BookItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo? = null
)

data class VolumeInfo(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("authors")
    val authors: List<String>? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("imageLinks")
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    @SerializedName("smallThumbnail")
    val smallThumbnail: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null
) {

    val httpsThumbnail: String?
        get() = (thumbnail ?: smallThumbnail)?.replace("http://", "https://")
}

data class Book(
    val id: String,
    val title: String,
    val imgSrc: String?
)

fun BookItem.toBook(): Book {
    return Book(
        id = id,
        title = volumeInfo?.title ?: "Sin título",
        imgSrc = volumeInfo?.imageLinks?.httpsThumbnail
    )
}
