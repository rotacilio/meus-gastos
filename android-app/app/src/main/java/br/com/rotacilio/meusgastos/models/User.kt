package br.com.rotacilio.meusgastos.models

data class User(
    val firstname: String? = null,
    val lastname: String? = null,
    val email: String? = null,
    val username: String? = null,
    val password: String? = null,
    val admin: Boolean? = false,
)
