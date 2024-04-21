package nl.jaysh.retrobob.user.domain.model

import java.time.LocalDateTime
import java.util.*

data class User(
    val id: UUID? = null,
    val email: String,
    val password: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
)
