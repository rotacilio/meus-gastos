package br.com.rotacilio.meusgastos.repositories

import br.com.rotacilio.meusgastos.api.UsersApi
import br.com.rotacilio.meusgastos.models.User

class UserRepository(private val api: UsersApi) {
    fun storeUser(user: User) = api.storeUser(user)
}