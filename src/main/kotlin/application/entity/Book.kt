package application.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Book (
    @Column
    var title: String?,

    @Column
    var price: Int?,

    @Column
    var author: String?,

    @Column
    var publisher: String?,

    @Column
    var isbn: String?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)