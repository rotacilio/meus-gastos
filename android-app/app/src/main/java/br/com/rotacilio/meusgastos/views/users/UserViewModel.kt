package br.com.rotacilio.meusgastos.views.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rotacilio.meusgastos.helpers.LoadingState
import br.com.rotacilio.meusgastos.models.GithubUser
import br.com.rotacilio.meusgastos.repositories.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val repo: UserRepository) : ViewModel(), Callback<List<GithubUser>> {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> get() = _loadingState

    private val _data = MutableLiveData<List<GithubUser>>()
    val data: LiveData<List<GithubUser>> get() = _data

    init {
        fetchData()
    }

    private fun fetchData() {
        _loadingState.postValue(LoadingState.LOADING)
//        repo.getAllUsers().enqueue(this)
    }

    override fun onResponse(call: Call<List<GithubUser>>, response: Response<List<GithubUser>>) {
        if (response.isSuccessful) {
            _data.postValue(response.body())
            _loadingState.postValue(LoadingState.LOADED)
        }
    }

    override fun onFailure(call: Call<List<GithubUser>>, t: Throwable) {
        _loadingState.postValue(LoadingState.error(t.message))
    }
}