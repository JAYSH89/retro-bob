package nl.jaysh.retrobob.user

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nl.jaysh.retrobob.core.exceptions.InvalidRequestException
import nl.jaysh.retrobob.user.data.model.UserEntity
import nl.jaysh.retrobob.user.data.model.toUser
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UserService(
    private val passwordService: PasswordService,
    private val userRepository: UserRepository,
) {
    fun findAll(): Flow<UserView> = userRepository.findAll()
        .map { entity -> entity.toUser() }
        .map { user -> UserView.fromUser(user = user) }

    suspend fun findByEmail(email: String): UserView {
        val user = userRepository.findByEmail(email) ?: throw InvalidRequestException("email", "not found")
        return UserView.fromUser(user = user.toUser())
    }

    suspend fun findById(userId: UUID): UserView {
        val user = userRepository.findById(userId) ?: throw InvalidRequestException("id", "not found")
        return UserView.fromUser(user = user.toUser())
    }

    @Transactional
    suspend fun signup(request: UserRegistrationRequest): UserView {
        if (userRepository.existsByEmail(request.email)) {
            throw InvalidRequestException("Email", "already in use")
        }

        val encodedPassword = passwordService.encodePassword(request.password)
        val user = request.toUser(encodedPassword = encodedPassword)
        val savedUser = userRepository.save(UserEntity.fromUser(user = user))

        return UserView.fromUser(savedUser.toUser())
    }

    @Transactional
    suspend fun delete(id: UUID) {
        val user = userRepository.findById(id) ?: throw InvalidRequestException("id", "not found")
        userRepository.delete(user)
    }
}
