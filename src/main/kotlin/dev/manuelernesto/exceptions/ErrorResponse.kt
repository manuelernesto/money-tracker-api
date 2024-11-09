package dev.manuelernesto.exceptions

import io.ktor.http.HttpStatusCode
import io.ktor.server.plugins.statuspages.StatusPagesConfig
import io.ktor.server.response.respond
import kotlinx.serialization.Serializable
import java.lang.IllegalArgumentException

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  15/10/24 8:25 PM
 * @version 1.0
 */
@Serializable
data class ErrorResponse(val status: Int, val message: String)

fun StatusPagesConfig.statusPageErrorConfig() {

    //User Exceptions
    exception<UserAlreadyExistsException> { call, cause ->
        val errorResponse = ErrorResponse(
            status = HttpStatusCode.BadRequest.value,
            message = cause.message.toString()
        )
        call.respond(HttpStatusCode.BadRequest, errorResponse)
    }

    exception<UserCredentialException> { call, cause ->
        val errorResponse = ErrorResponse(
            status = HttpStatusCode.BadRequest.value,
            message = cause.message.toString()
        )
        call.respond(HttpStatusCode.BadRequest, errorResponse)
    }

    exception<UserNotFoundException> { call, cause ->
        val errorResponse = ErrorResponse(
            status = HttpStatusCode.NotFound.value,
            message = cause.message.toString()
        )
        call.respond(HttpStatusCode.NotFound, errorResponse)
    }


    //Category Exceptions
    exception<CategoryAlreadyExistsException> { call, cause ->
        val errorResponse = ErrorResponse(
            status = HttpStatusCode.BadRequest.value,
            message = cause.message.toString()
        )
        call.respond(HttpStatusCode.BadRequest, errorResponse)
    }

    exception<CategoryNotExistsException> { call, cause ->
        val errorResponse = ErrorResponse(
            status = HttpStatusCode.NotFound.value,
            message = cause.message.toString()
        )
        call.respond(HttpStatusCode.NotFound, errorResponse)
    }

    //Account Exceptions
    exception<AccountNotFoundException> { call, cause ->
        val errorResponse = ErrorResponse(
            status = HttpStatusCode.NotFound.value,
            message = cause.message.toString()
        )
        call.respond(HttpStatusCode.NotFound, errorResponse)
    }

    exception<AccountBalanceNotEmptyException> { call, cause ->
        val errorResponse = ErrorResponse(
            status = HttpStatusCode.Conflict.value,
            message = cause.message.toString()
        )
        call.respond(HttpStatusCode.Conflict, errorResponse)
    }

    exception<AccountIsCloseException> { call, cause ->
        val errorResponse = ErrorResponse(
            status = HttpStatusCode.Conflict.value,
            message = cause.message.toString()
        )
        call.respond(HttpStatusCode.Conflict, errorResponse)
    }

    exception<NegativeAmountException> { call, cause ->
        val errorResponse = ErrorResponse(
            status = HttpStatusCode.BadRequest.value,
            message = cause.message.toString()
        )
        call.respond(HttpStatusCode.BadRequest, errorResponse)
    }

    exception<NoEnoughMoneyInAccountException> { call, cause ->
        val errorResponse = ErrorResponse(
            status = HttpStatusCode.Conflict.value,
            message = cause.message.toString()
        )
        call.respond(HttpStatusCode.Conflict, errorResponse)
    }

    exception<IllegalArgumentException> { call, cause ->
        val errorResponse = ErrorResponse(
            status = HttpStatusCode.BadRequest.value,
            message = cause.message.toString()
        )
        call.respond(HttpStatusCode.BadRequest, errorResponse)
    }

}
