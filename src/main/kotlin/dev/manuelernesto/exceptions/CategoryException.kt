package dev.manuelernesto.exceptions

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  14/10/24 8:08 PM
 * @version 1.0
 */
class CategoryAlreadyExistsException(message: String) : Exception(message)
class CategoryNotExistsException(message: String) : Exception(message)