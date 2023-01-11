package application.repository

import application.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import java.util.Optional

// Difference between JpaRepository and CrudRepository
// https://www.tutorialspoint.com/difference-between-crudrepository-and-jparepository-in-java
// TL;DR: CrudRepository not support Paging/sorting, Advanced batch support while JpaRepository supports with usage of much more resources.

// Reason why JpaRepository should be interface
// ->
interface BookRepository: CrudRepository<Book, Long> {

}