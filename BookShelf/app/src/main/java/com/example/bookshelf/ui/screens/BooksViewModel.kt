package com.example.bookshelf.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BooksApplication
import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.Book
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "BooksViewModel"

/**
 * Estado de la UI para la pantalla de libros.
 */
sealed interface BooksUiState {
    data class Success(val books: List<Book>) : BooksUiState
    data class Error(val message: String? = null) : BooksUiState
    object Loading : BooksUiState
}

/**
 * ViewModel que maneja la lógica de presentación y el estado para la pantalla de libros.
 */
class BooksViewModel(private val booksRepository: BooksRepository) : ViewModel() {

    /** Estado mutable que expone la condición actual de la interfaz */
    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set

    init {
        getBooks()
    }

    /**
     * Consulta libros desde el repositorio y actualiza el estado de la UI.
     */
    fun getBooks(query: String = "jazz+history") {
        viewModelScope.launch {
            booksUiState = BooksUiState.Loading
            booksUiState = try {
                val books = booksRepository.getBooks(query)
                BooksUiState.Success(books)
            } catch (e: IOException) {
                Log.e(TAG, "Error de red/conexión: ${e.message}", e)
                BooksUiState.Error("Error de conexión a Internet. Revisa tu red.")
            } catch (e: HttpException) {
                val code = e.code()
                Log.e(TAG, "Error HTTP $code devuelto por el servidor: ${e.message()}", e)
                if (code == 429) {
                    BooksUiState.Error("Error 429 (Cuota excedida de Google Books).\nGoogle ahora requiere una API Key para realizar búsquedas.")
                } else {
                    BooksUiState.Error("Error del servidor HTTP $code: ${e.message()}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error inesperado al cargar libros: ${e.message}", e)
                BooksUiState.Error("Error inesperado: ${e.localizedMessage}")
            }
        }
    }

    /**
     * Factoría para instanciar [BooksViewModel] inyectando el repositorio
     * desde el contenedor de dependencias de [BooksApplication].
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BooksApplication)
                val booksRepository = application.container.booksRepository
                BooksViewModel(booksRepository = booksRepository)
            }
        }
    }
}
