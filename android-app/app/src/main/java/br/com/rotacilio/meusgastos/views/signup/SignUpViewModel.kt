package br.com.rotacilio.meusgastos.views.signup

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rotacilio.meusgastos.api.models.CreateUserResponse
import br.com.rotacilio.meusgastos.models.User
import br.com.rotacilio.meusgastos.repositories.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SignUpViewModel(private val repo: UserRepository) : ViewModel(), Callback<CreateUserResponse> {

    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    private val _successfulRegistered = MutableLiveData<Boolean>()
    val successfulRegistered: LiveData<Boolean> get() = _successfulRegistered

    init {
        initializeVariable()
    }

    private fun initializeVariable() {
        firstName.postValue("")
        lastName.postValue("")
        email.postValue("")
        password.postValue("")
        confirmPassword.postValue("")
    }

    fun onClickSignUp() {
        try {
            isValidForm {
                storeUser(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _successfulRegistered.postValue(false)
        }
    }

    private fun isValidForm(callback: (data: User) -> Unit) {
        val firstNameStr = firstName.value
        val lastNameStr = lastName.value
        val emailStr = email.value
        val passwordStr = password.value
        val confirmPasswordStr = confirmPassword.value

        val isValid = !firstNameStr.isNullOrEmpty() &&
                !lastNameStr.isNullOrEmpty() &&
                !emailStr.isNullOrEmpty() &&
                !passwordStr.isNullOrEmpty() &&
                !confirmPasswordStr.isNullOrEmpty()
                && confirmPasswordStr == passwordStr

        if (isValid) {
            val user = User(
                firstname = firstNameStr,
                lastname = lastNameStr,
                email = emailStr,
                username = emailStr,
                password = passwordStr,
                admin = false)
            callback(user)
        }
        else throw Exception("Verifique os campos informados.")
    }

    private fun storeUser(data: User) {
        repo.storeUser(data)
    }

    override fun onResponse(
        call: Call<CreateUserResponse>,
        response: Response<CreateUserResponse>
    ) {
        if (response.isSuccessful) {
            Log.i("SignUpViewModel", response.body().toString())
            _successfulRegistered.postValue(true)
            return
        }
        _successfulRegistered.postValue(false)
    }

    override fun onFailure(call: Call<CreateUserResponse>, t: Throwable) {
        Log.e("SignUpViewModel", "onFailure: ${t.message}")
        _successfulRegistered.postValue(false)
    }
}