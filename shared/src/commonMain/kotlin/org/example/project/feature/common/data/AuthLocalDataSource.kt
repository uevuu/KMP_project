package org.example.project.feature.common.data

import com.russhwolf.settings.Settings
import io.ktor.utils.io.core.toByteArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.example.project.AppDatabase
import org.example.project.UserDB

private const val CURRENT_USER_ID = "CURRENT_USER_ID"

internal class AuthLocalDataSource(
    private val settings: Settings,
    private val database: AppDatabase
) {

    suspend fun getCurrentUser(): UserDB? = withContext(Dispatchers.IO) {
        val currentUserId = settings.getLongOrNull(CURRENT_USER_ID) ?: return@withContext null
        getUserById(currentUserId)
    }

    suspend fun getUserById(id: Long): UserDB? = withContext(Dispatchers.IO) {
        database.usersQueries.selectById(id).executeAsOneOrNull()
    }

    suspend fun getUserByName(name: String): UserDB? = withContext(Dispatchers.IO) {
        database.usersQueries.selectByName(name).executeAsOneOrNull()
    }

    suspend fun createUser(name: String, password: String) = withContext(Dispatchers.IO) {
        if (getUserByName(name) != null) error("User already exists")
        database.usersQueries.insert(name, password.toByteArray())
        getUserByName(name)?.id ?: error("User not created")
    }

    suspend fun setCurrentUser(userId: Long) = withContext(Dispatchers.IO) {
        settings.putLong(CURRENT_USER_ID, userId)
    }

    suspend fun removeCurrentUser() = withContext(Dispatchers.IO) {
        settings.remove(CURRENT_USER_ID)
    }

    suspend fun comparePasswords(name: String, password: String): Boolean = withContext(Dispatchers.IO) {
        getUserByName(name)?.password.contentEquals(password.toByteArray())
    }
}
