package application.service

import application.dto.BookDto
import application.entity.Book
import application.exception.EntityNotFoundException
import application.repository.BookRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional



@Service
open class BookService(
    private val bookRepository: BookRepository
) {
    // Transactional Method should be opened
    // Open -> Mark method/field/class as open for inheritance
    // So proxy service can inherit this method to make transaction session
    @Transactional
    open fun createBook(book: BookDto): Book {
        val inputBook = Book(
            title = book.title,
            author = book.author,
            isbn = book.isbn,
            price = book.price,
            publisher = book.publisher
        )

        return bookRepository.save(inputBook)
    }

    @Transactional
    open fun updateBook(book: BookDto, id: Long): Book {
        val inputBook = Book(
            id = id,
            title = book.title,
            author = book.author,
            isbn = book.isbn,
            price = book.price,
            publisher = book.publisher
        )

        return bookRepository.save(inputBook)
    }

    @Transactional
    open fun deleteBook(id: Long) {
        bookRepository.deleteById(id)
    }

    open fun getBookById(id: Long): Book {
        val result = bookRepository.findById(id)
        if (result.isPresent) {
            return result.get()
        } else {
            throw EntityNotFoundException(
                entityName = "Book",
                entityId = "$id"
            )
        }
    }
}