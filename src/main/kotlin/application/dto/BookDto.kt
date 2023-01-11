package application.dto

/**
 * Dataclass(DTO) for the response of the /
 */
data class BookDto (
    val title: String?,
    val price: Int?,
    val author: String?,
    val publisher: String?,
    val isbn: String?,
)

data class BookResponseDto (
    // Default value가 있을 경우 생성자에서 굳이 넣어주지 않아도 됨.
    val title: String? = "임시 제목",
    val price: Int?,
    val author: String?,
    val publisher: String?,
    val isbn: String?,
    val id: Long?,
)