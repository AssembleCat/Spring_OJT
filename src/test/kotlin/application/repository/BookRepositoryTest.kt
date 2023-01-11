package application.repository

import application.entity.Book
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager


// In kotlin, normal approach of DI using constructor is not working
// You should define @Autowired annotation explicitly
// Jupiter can't find bean of BookRepository because Jupiter is not Spring IoC container
@DataJpaTest
open class BookRepositoryTest @Autowired constructor(
    private val bookRepository: BookRepository,
    private val entityManager: EntityManager,
){
    // Logger
    private val log: Logger = LoggerFactory.getLogger(BookRepositoryTest::class.java)

    @Test
    fun `save book data`() {
        val book = Book(
            title = "title",
            author = "author",
            price = 1000,
            publisher = "pub",
            isbn = "978-3-16-148410-0"
        )

        val savedBook = bookRepository.save(book)

        log.info("Book Entity Saved. ID: ${savedBook.id}")
        // check EntityManager to see Book data is persisted
        assert(entityManager.find(Book::class.java, savedBook.id) != null)
    }

    @Test
    fun `update bookdata`() {
        val book = Book(
            title = "title",
            author = "author",
            price = 1000,
            publisher = "pub",
            isbn = "978-3-16-148410-0"
        )

        val savedBook = bookRepository.save(book)

        savedBook.title = "updated title"
        bookRepository.save(savedBook) // update

        assert(savedBook.title == "updated title")
    }
}