package dev.manuelernesto.repository

import dev.manuelernesto.config.dbQuery
import dev.manuelernesto.model.Category
import dev.manuelernesto.model.schemas.Categories
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.upperCase
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  17/10/24 9:05 PM
 * @version 1.0
 */

class CategoryRepository {
    suspend fun save(category: Category): Category? = dbQuery {
        val inserted = Categories.insert {
            it[id] = UUID.randomUUID()
            it[name] = category.name
        }
        inserted.resultedValues?.singleOrNull()?.let { Category.fromResultRow(it) }
    }

    suspend fun getAll(): List<Category> = dbQuery {
        Categories.selectAll().map { Category.fromResultRow(it) }
    }

    suspend fun getCategoryById(categoryId: UUID): Category? = dbQuery {
        Categories.selectAll().where { Categories.id eq categoryId }.map { Category.fromResultRow(it) }.singleOrNull()
    }

    suspend fun update(categoryId: UUID, category: Category) = dbQuery {
        Categories.update({ Categories.id eq categoryId }) {
            it[name] = category.name
        }
    }

    suspend fun delete(categoryId: UUID) = dbQuery {
        Categories.deleteWhere() { id eq categoryId }
    }

    suspend fun getCategoryByName(name: String): Category? = dbQuery {
        Categories.selectAll().where { Categories.name.upperCase() like name.uppercase() }
            .map { Category.fromResultRow(it) }
            .singleOrNull()
    }
}