package dev.manuelernesto.model

import dev.manuelernesto.model.schemas.Categories
import dev.manuelernesto.util.serializer.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  16/10/24 9:15 PM
 * @version 1.0
 */
@Serializable
data class Category(@Serializable(with = UUIDSerializer::class) val categoryId: UUID? = null, val name: String) {
    companion object {
        fun fromResultRow(row: ResultRow) = Category(
            categoryId = row[Categories.id],
            name = row[Categories.name]
        )
    }
}
