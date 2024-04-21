package nl.jaysh.retrobob.user

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordService {
//    private val encoder = BCryptPasswordEncoder()

//    fun encodePassword(password: String): String = encoder.encode(password)
    fun encodePassword(password: String): String = password

//    fun matchesRawPasswordWithEncodedPassword(password: String, encodedPassword: String): Boolean {
//        return encoder.matches(password, encodedPassword)
//    }
}
