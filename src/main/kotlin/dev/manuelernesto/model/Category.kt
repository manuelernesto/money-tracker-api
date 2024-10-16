package dev.manuelernesto.model

import kotlinx.serialization.Serializable

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  16/10/24 9:15 PM
 * @version 1.0
 */
@Serializable
data class Category(val categoryId: String? = null, val name: String)
