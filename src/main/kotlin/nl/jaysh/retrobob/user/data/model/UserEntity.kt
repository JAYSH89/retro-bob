package nl.jaysh.retrobob.user.data.model

import nl.jaysh.retrobob.user.domain.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*

@Table("app_user")
data class UserEntity(
    @Id val id: UUID?,
    @Column("email") val email: String,
    @Column("password") val password: String,
    @Column("first_name") val firstName: String?,
    @Column("last_name") val lastName: String?,
    @Column("created_at") val createdAt: LocalDateTime,
    @Column("updated_at") val updatedAt: LocalDateTime,
) {
    companion object {
        fun fromUser(user: User) = UserEntity(
            id = user.id,
            email = user.email,
            password = user.password,
            firstName = user.firstName,
            lastName = user.lastName,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )
    }
}

fun UserEntity.toUser() = User(
    id = id,
    email = email,
    password = password,
    firstName = firstName,
    lastName = lastName,
    createdAt = createdAt,
    updatedAt = updatedAt,
)
