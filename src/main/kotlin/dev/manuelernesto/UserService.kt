package dev.manuelernesto

import dev.manuelernesto.exceptions.UserAlreadyExistsException
import dev.manuelernesto.model.User
import dev.manuelernesto.repository.UserRepository
import org.mindrot.jbcrypt.BCrypt

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  14/10/24 8:01 PM
 * @version 1.0
 */
class UserService(private val userRepository: UserRepository) {

    suspend fun getUserById(id: Long): User? =
        userRepository.getUserById(id)

    suspend fun createUser(user: User): User? {
        if (userRepository.getUserByUsername(user.username) != null) {
            throw UserAlreadyExistsException("Username already exists!")
        }
        user.password = hashPassword(user.password)
        return userRepository.save(user)
    }


    private fun hashPassword(password: String): String = BCrypt.hashpw(password, BCrypt.gensalt())
}