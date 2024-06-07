package org.example.project.feature.auth.data

private const val NAME_MIN_LENGTH = 4
private const val PASSWORD_MIN_LENGTH = 8

internal class AuthRepository(
    private val localDataSource: AuthLocalDataSource
) {
    suspend fun loginUser(name: String, password: String) {
        require(localDataSource.comparePasswords(name, password))
    }

    suspend fun registerUser(name: String, password: String) {
        require(name.length >= NAME_MIN_LENGTH)
        require(password.length >= PASSWORD_MIN_LENGTH)
        localDataSource.createUser(name, password)
    }

    suspend fun isUserAuthorized() = localDataSource.getCurrentUser() != null
}
