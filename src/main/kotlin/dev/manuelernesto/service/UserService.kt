package dev.manuelernesto.service

import dev.manuelernesto.exceptions.UserAlreadyExistsException
import dev.manuelernesto.exceptions.UserCredentialException
import dev.manuelernesto.exceptions.UserNotFoundException
import dev.manuelernesto.model.PasswordUpdate
import dev.manuelernesto.model.User
import dev.manuelernesto.repository.UserRepository
import org.mindrot.jbcrypt.BCrypt
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  14/10/24 8:01 PM
 * @version 1.0
 */
class UserService(private val userRepository: UserRepository) {

    suspend fun getUserById(userId: UUID): User? =
        userRepository.getUserById(userId) ?: throw UserNotFoundException("User with ID $userId does not exist!")


    suspend fun updatePassword(userId: UUID, passwordUpdate: PasswordUpdate) {
        val user =
            userRepository.getUserById(userId) ?: throw UserNotFoundException("User with ID $userId does not exist!")

        if (!BCrypt.checkpw(passwordUpdate.oldPassword, user.password)) {
            throw UserCredentialException("Incorrect current password.")
        }

        if (!isValidPassword(passwordUpdate.newPassword)) {
            throw UserCredentialException(
                "Password must be at least 8 characters long," +
                        "at least one uppercase letter, " +
                        "at least one lowercase letter, " +
                        "must contain at least one digit and must contain at least one special character"
            )
        }

        userRepository.updatePassword(userId, hashPassword(passwordUpdate.newPassword))
    }

    suspend fun createUser(user: User): User? {
        if (userRepository.getUserByUsername(user.username) != null) {
            throw UserAlreadyExistsException("Username already exists!")
        }

        if (!isValidPassword(user.password)) {
            throw UserCredentialException(
                "Password must be at least 8 characters long," +
                        "at least one uppercase letter, " +
                        "at least one lowercase letter, " +
                        "must contain at least one digit and must contain at least one special character"
            )
        }

        user.password = hashPassword(user.password)
        return userRepository.save(user)
    }

    suspend fun deleteUserById(userId: UUID) {
        val user = userRepository.delete(userId)

        if (user <= 0) {
            throw UserNotFoundException("User with ID $userId does not exist!")
        }
    }


    private fun hashPassword(password: String): String = BCrypt.hashpw(password, BCrypt.gensalt())
    private fun isValidPassword(password: String): Boolean {
        if (password.length < 8) {
            return false
        }

        if (!password.any { it.isUpperCase() }) {
            return false
        }

        if (!password.any { it.isLowerCase() }) {
            return false
        }

        if (!password.any { it.isDigit() }) {
            return false
        }

        if (!password.any { !it.isLetterOrDigit() }) {
            return false
        }

        return true
    }

}