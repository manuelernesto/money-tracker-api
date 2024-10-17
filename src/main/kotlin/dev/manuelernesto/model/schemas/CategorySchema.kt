package dev.manuelernesto.model.schemas

import org.jetbrains.exposed.sql.Table

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  17/10/24 8:56 PM
 * @version 1.0
 */
object Categories : Table("category") {
    val id = uuid("categoryId")
    val name = varchar("password", 255)

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}