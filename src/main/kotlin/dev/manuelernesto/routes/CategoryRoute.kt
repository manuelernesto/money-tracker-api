package dev.manuelernesto.routes

import dev.manuelernesto.model.Category
import dev.manuelernesto.service.CategoryService
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  11/10/24 8:56 PM
 * @version 1.0
 */

fun Route.categoryRoute(categoryService: CategoryService) {
    route("/api/v1/categories") {

        get {
            call.respond(categoryService.getAllCategories() as Any)
        }

        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            call.respond(categoryService.getCategoryById(UUID.fromString(id)) as Any)
        }
        post {
            val category = call.receive<Category>()
            val createdCategory = categoryService.createCategory(category)
            call.respond(status = HttpStatusCode.Created, createdCategory as Any)
        }

        put("/{id}") {
            val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
            val category = call.receive<Category>()
            categoryService.updateCategory(UUID.fromString(id), category)
            call.respond(HttpStatusCode.OK)
        }

        delete("/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            categoryService.deleteCategory(UUID.fromString(id))
            call.respond(HttpStatusCode.NoContent)
        }
    }
}