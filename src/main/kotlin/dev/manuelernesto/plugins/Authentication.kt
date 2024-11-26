package dev.manuelernesto.plugins

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  25/11/24 10:11 PM
 * @version 1.0
 */

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*


const val JWT_EXPIRATION_TIME_MS = 36_000_00 // 1 hour
const val JWT_AUDIENCE = "ktor-users"
const val JWT_SECRET = "your_secret_key"
const val JWT_ISSUER = "money-tracker-app"
const val JWT_CONFIG_NAME = "auth-jwt"
const val JWT_REALM = "money-tracker-app"
const val JWT_USER_ID = "userId"
const val JWT_EMAIL = "email"

fun Application.configureAuthentication() {
    install(Authentication) {
        jwt(JWT_CONFIG_NAME) {
            realm = JWT_REALM
            verifier(
                JWT
                    .require(Algorithm.HMAC256(JWT_SECRET))
                    .withIssuer(JWT_ISSUER)
                    .withAudience(JWT_AUDIENCE)
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim(JWT_USER_ID).asString() != null) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
        }
    }
}
