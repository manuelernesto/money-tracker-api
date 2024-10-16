package dev.manuelernesto.util

import dev.manuelernesto.model.User
import dev.manuelernesto.model.UserResponse

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  16/10/24 8:20 PM
 * @version 1.0
 */


fun User.toUserResponse() = UserResponse(this.userId, this.username, this.email)