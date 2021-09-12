package br.com.rotacilio.meusgastos.views.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rotacilio.meusgastos.api.models.LoginResponse
import br.com.rotacilio.meusgastos.api.models.PostLogin
import br.com.rotacilio.meusgastos.repositories.UserRepository
import br.com.rotacilio.meusgastos.session.impl.SessionManagerUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val repository: UserRepository): ViewModel() {

    val username = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")

    private val _successfulAuthenticated = MutableLiveData<Boolean>(false)
    val successfulAuthenticated: LiveData<Boolean> get() = _successfulAuthenticated

    fun onClickLogin() {
        try {
            isValidLoginForm {
                doLogin(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onClickForgotPassword() {
        // TODO: 9/12/2021 Implement forgot password
    }

    private fun isValidLoginForm(callback: (data: PostLogin) -> Unit) {
        val usernameStr = username.value
        val passwordStr = password.value

        if (!usernameStr.isNullOrEmpty() && !passwordStr.isNullOrEmpty()) {
            val data = PostLogin(
                username = usernameStr,
                password = passwordStr
            )
            callback(data)
            return
        }
        throw Exception("Verifique os campos informados.")
    }

    private fun doLogin(data: PostLogin) {
        repository.authenticate(data).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    Log.i("LoginViewModel", response.body().toString())
                    _successfulAuthenticated.postValue(true)
                    return
                }
                _successfulAuthenticated.postValue(false)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("LoginViewModel", t.message!!)
                _successfulAuthenticated.postValue(false)
            }

        })
    }
}