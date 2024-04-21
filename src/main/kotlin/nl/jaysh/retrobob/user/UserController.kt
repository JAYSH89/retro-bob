package nl.jaysh.retrobob.user

import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService) {

    @GetMapping("/users")
    suspend fun getUsers(): Flow<UserView> = userService.findAll()

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun signup(@RequestBody request: UserRegistrationRequest): UserView = userService.signup(request = request)

    @GetMapping("/users/{id}")
    suspend fun getUser(@PathVariable id: UUID): UserView = userService.findById(id)

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun deleteUser(@PathVariable id: UUID) = userService.delete(id)
}
