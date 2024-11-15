package dev.manuelernesto.model.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Table.PrimaryKey

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  16/10/24 8:21 PM
 * @version 1.0
 */

object Users : Table("usermanager") {
    val id = uuid("id")
    val username = varchar("username", 255).uniqueIndex()
    val password = varchar("password", 255)
    val email = varchar("email", 255).nullable()

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}
