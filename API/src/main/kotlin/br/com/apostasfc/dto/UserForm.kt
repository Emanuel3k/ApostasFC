package br.com.apostasfc.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class UserForm(
        @field:NotBlank
        @field:NotEmpty
        @field:Size(min = 4)
        val username: String,

        @field:NotBlank
        @field:NotEmpty
        @field:Size(min = 8)
        val password: String
)
