package org.example.project.feature.auth.data

import com.russhwolf.settings.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.example.project.AppDatabase
import org.example.project.UserDB
import org.example.project.feature.utils.sha256

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
        database.usersQueries.insert(name, password.sha256())
    }

    suspend fun comparePasswords(name: String, password: String): Boolean = withContext(Dispatchers.IO) {
        getUserByName(name)?.password.contentEquals(password.sha256())
    }
}
