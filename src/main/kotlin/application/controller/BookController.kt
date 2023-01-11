package application.controller

import application.dto.BookDto
import application.dto.BookResponseDto
import application.entity.Book
import application.exception.EntityNotFoundException
import application.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class BookController(
    private val bookService: BookService
) {
    @PutMapping("/book")
    fun createBook(@RequestBody bookDto: BookDto): ResponseEntity<CreateBookResponse> {
        val result = bookService.createBook(bookDto)

        return ResponseEntity(CreateBookResponse(
            status = "created",
            data = BookResponseDto(
                title = result.title,
                author = result.author,
                isbn = result.isbn,
                price = result.price,
                publisher = result.publisher,
                id = result.id
            )
        ), HttpStatus.CREATED)
    }

    @GetMapping("/book/{pk}")
    fun getBook(@PathVariable pk: Long): ResponseEntity<BookResponseDto> {
        try {
            val result = bookService.getBookById(pk)

            return ResponseEntity(
                BookResponseDto(
                    title = result.title,
                    author = result.author,
                    isbn = result.isbn,
                    price = result.price,
                    publisher = result.publisher,
                    id = result.id
                ), HttpStatus.OK
            )
        } catch (e: EntityNotFoundException) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/book/{pk}")
    fun updateBook(@PathVariable pk: Long, @RequestBody bookDto: BookDto): ResponseEntity<BookResponseDto> {
        val result = bookService.updateBook(bookDto, pk)

        return ResponseEntity(BookResponseDto(
            title = result.title,
            author = result.author,
            isbn = result.isbn,
            price = result.price,
            publisher = result.publisher,
            id = result.id
        ), HttpStatus.OK)
    }

    @DeleteMapping("/book/{pk}")
    fun deleteBook(@PathVariable pk: Long): ResponseEntity<Void> {
        bookService.deleteBook(pk)

        return ResponseEntity<Void>(HttpStatus.OK)
    }

    @GetMapping("/book/dto")
    fun getBookDto(@RequestParam param: Map<String, Any>): BookDto {
        return BookDto(
            title = param["title"] as String,
            author = param["author"] as String,
            price = (param["price"] as String).toInt(),
            publisher = param["publisher"] as String,
            isbn = param["isbn"] as String,
        )
    }
}

data class CreateBookResponse(
    val status: String,
    val data: BookResponseDto
)