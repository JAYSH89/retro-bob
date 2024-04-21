package nl.jaysh.retrobob.user

import nl.jaysh.retrobob.user.data.model.UserEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface UserRepository : CoroutineCrudRepository<UserEntity, UUID> {
    suspend fun findByEmail(email: String): UserEntity?

    suspend fun existsByEmail(email: String): Boolean
}
