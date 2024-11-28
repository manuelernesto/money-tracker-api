package dev.manuelernesto.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import dev.manuelernesto.plugins.JWT_AUDIENCE
import dev.manuelernesto.plugins.JWT_EMAIL
import dev.manuelernesto.plugins.JWT_EXPIRATION_TIME_MS
import dev.manuelernesto.plugins.JWT_ISSUER
import dev.manuelernesto.plugins.JWT_SECRET
import dev.manuelernesto.plugins.JWT_USER_ID
import java.util.Date
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  26/11/24 8:54 PM
 * @version 1.0
 */


fun generateJwtToken(userId: UUID, email: String): String {
    val algorithm = Algorithm.HMAC256(JWT_SECRET)
    return JWT.create()
        .withIssuer(JWT_ISSUER)
        .withAudience(JWT_AUDIENCE)
        .withClaim(JWT_USER_ID, userId.toString())
        .withClaim(JWT_EMAIL, email)
        .withExpiresAt(Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME_MS))
        .sign(algorithm)
}