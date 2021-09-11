package br.com.rotacilio.meusgastos.api

import br.com.rotacilio.meusgastos.models.GithubUser
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    fun getUsers(): Call<List<GithubUser>>
}