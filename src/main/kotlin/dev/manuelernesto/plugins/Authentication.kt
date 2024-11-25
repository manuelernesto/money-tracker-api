package dev.manuelernesto.plugins

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  25/11/24 10:11 PM
 * @version 1.0
 */

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.util.Date

val secret = "your_secret_key"
val issuer = "money-tracker-app"
val audience = "ktor-users"
val expirationTimeMs = 36_000_00 // 1 hour

fun Application.configureAuthentication() {
    install(Authentication) {
        jwt("auth-jwt") {
            realm = "money-tracker-app"
            verifier(
                JWT
                    .require(Algorithm.HMAC256(secret))
                    .withIssuer(issuer)
                    .withAudience(audience)
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim("username").asString() != null) {
                    JWTPrincipal(credential.payload)
                } else {
                    null // Invalid token
                }
            }
        }
    }
}

fun generateJwtToken(userId: Int, email: String): String {
    val algorithm = Algorithm.HMAC256(secret)
    return JWT.create()
        .withIssuer(issuer)
        .withAudience(audience)
        .withClaim("userId", userId) // Embed userId
        .withClaim("email", email)  // Embed email
        .withExpiresAt(Date(System.currentTimeMillis() + expirationTimeMs))
        .sign(algorithm)
}