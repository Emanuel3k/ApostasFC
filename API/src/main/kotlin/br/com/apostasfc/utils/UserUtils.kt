package br.com.apostasfc.utils

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserUtils {

    // Checks if the username is a letter or number
    fun usernameIsValid(username: String): Boolean {
        return username.all { it.isLetterOrDigit() }
    }
}