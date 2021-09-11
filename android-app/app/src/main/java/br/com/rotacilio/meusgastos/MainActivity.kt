package br.com.rotacilio.meusgastos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.rotacilio.meusgastos.views.users.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel.apply {
            data.observe(this@MainActivity) {
                Log.i("MainActivity", "users: ${it.size}")
            }
            loadingState.observe(this@MainActivity) {
                Log.i("MainActivity", "LoadingState: $it")
            }
        }
    }
}