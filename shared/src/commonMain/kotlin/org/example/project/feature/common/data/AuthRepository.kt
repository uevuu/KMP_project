package org.example.project.feature.common.data

private const val NAME_MIN_LENGTH = 4
private const val PASSWORD_MIN_LENGTH = 8

internal class AuthRepository(
    private val localDataSource: AuthLocalDataSource
) {
    suspend fun loginUser(name: String, password: String) {
        require(localDataSource.comparePasswords(name, password))
        val userId = localDataSource.getUserByName(name)?.id ?: error("User does not exist")
        localDataSource.setCurrentUser(userId)
    }

    suspend fun registerUser(name: String, password: String) {
        require(name.length >= NAME_MIN_LENGTH)
        require(password.length >= PASSWORD_MIN_LENGTH)
        val userId = localDataSource.createUser(name, password)
        localDataSource.setCurrentUser(userId)
    }

    suspend fun signOutCurrentUser() {
        localDataSource.removeCurrentUser()
    }

    suspend fun isUserAuthorized() = localDataSource.getCurrentUser() != null

    suspend fun getCurrentUserId() = localDataSource.getCurrentUser()?.id
}
