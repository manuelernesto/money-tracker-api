package dev.manuelernesto.service

import dev.manuelernesto.exceptions.CategoryAlreadyExistsException
import dev.manuelernesto.exceptions.CategoryNotExistsException
import dev.manuelernesto.model.Category
import dev.manuelernesto.repository.CategoryRepository
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  17/10/24 9:49 PM
 * @version 1.0
 */
class CategoryService(private val categoryRepository: CategoryRepository) {
    suspend fun getAllCategories(): List<Category>? = categoryRepository.getAll()

    suspend fun getCategoryById(categoryId: UUID): Category? =
        categoryRepository.getCategoryById(categoryId)
            ?: throw CategoryNotExistsException("Category with ID $categoryId does not exist!")


    suspend fun createCategory(category: Category): Category? {
        if (categoryRepository.getCategoryByName(category.name) != null) {
            throw CategoryAlreadyExistsException("category name already exists!")
        }

        return categoryRepository.save(category)
    }

    suspend fun deleteCategory(categoryId: UUID) {
        if (categoryRepository.delete(categoryId) <= 0) {
            throw CategoryNotExistsException("Category with ID $categoryId does not exist!")
        }
    }

    suspend fun updateCategory(categoryId: UUID, category: Category) {
        if (categoryRepository.getCategoryById(categoryId) == null) {
            throw CategoryNotExistsException("Category with ID $categoryId does not exist!")
        }
        if (categoryRepository.getCategoryByName(category.name) != null) {
            throw CategoryAlreadyExistsException("category name already exists!")
        }
        categoryRepository.update(categoryId, category)
    }

}