package br.com.rotacilio.meusgastos.api

import br.com.rotacilio.meusgastos.api.models.CreateUserResponse
import br.com.rotacilio.meusgastos.models.User
import br.com.rotacilio.meusgastos.utils.Utils
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersApi {
    @POST(Utils.EndPoints.CREATE_USER)
    fun storeUser(@Body user: User): Call<CreateUserResponse>
}