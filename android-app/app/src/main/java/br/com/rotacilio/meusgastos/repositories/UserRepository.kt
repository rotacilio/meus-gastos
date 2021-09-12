package br.com.rotacilio.meusgastos.repositories

import br.com.rotacilio.meusgastos.api.UsersApi
import br.com.rotacilio.meusgastos.api.models.CreateUserResponse
import br.com.rotacilio.meusgastos.api.models.LoginResponse
import br.com.rotacilio.meusgastos.api.models.PostLogin
import br.com.rotacilio.meusgastos.models.User
import retrofit2.Call

class UserRepository(private val api: UsersApi) {
    fun storeUser(user: User): Call<CreateUserResponse> = api.storeUser(user)
    fun authenticate(data: PostLogin): Call<LoginResponse> = api.authenticate(data)
}