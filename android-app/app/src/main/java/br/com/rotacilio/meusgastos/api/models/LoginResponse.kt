package br.com.rotacilio.meusgastos.api.models

data class LoginResponse(
    val success: Boolean? = false,
    val token: String? = null,
    val status: String? = null
)
