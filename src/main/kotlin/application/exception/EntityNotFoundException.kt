package application.exception

import java.lang.RuntimeException

class EntityNotFoundException(
    val entityName: String,
    val entityId: String
): RuntimeException(
    "Entity $entityName with id $entityId not found"
) {

}