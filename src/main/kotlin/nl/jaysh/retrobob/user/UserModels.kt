package nl.jaysh.retrobob.user

import com.fasterxml.jackson.annotation.JsonProperty
import nl.jaysh.retrobob.user.domain.model.User
import java.util.*

data class UserRegistrationRequest(
    @JsonProperty("email") val email: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("first_name") val firstName: String?,
    @JsonProperty("last_name") val lastName: String?,
) {
    fun toUser(encodedPassword: String): User = User(
        email = email,
        password = encodedPassword,
        firstName = firstName,
        lastName = lastName,
    )
}

data class UserView(
    @JsonProperty("id") val id: UUID? = null,
    @JsonProperty("email") val email: String,
    @JsonProperty("first_name") val firstName: String? = null,
    @JsonProperty("last_name") val lastName: String? = null,
) {
    companion object {
        fun fromUser(user: User) = UserView(
            id = user.id,
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
        )
    }
}
