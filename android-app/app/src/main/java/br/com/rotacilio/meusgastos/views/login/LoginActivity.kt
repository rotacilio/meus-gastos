package br.com.rotacilio.meusgastos.views.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.rotacilio.meusgastos.R
import br.com.rotacilio.meusgastos.databinding.ActivityLoginBinding
import br.com.rotacilio.meusgastos.views.signup.SignUpActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        binding.lifecycleOwner = this@LoginActivity
        binding.loginViewModel = viewModel

        configureObservers()
        configureListeners()
    }

    private fun configureListeners() {
        binding.apply {
            btnSignUp.setOnClickListener {
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            }
        }
    }

    private fun configureObservers() {
        viewModel.apply {
            successfulAuthenticated.observe(this@LoginActivity) {
                if (it) {
                    Log.i("Login", "configureObservers: $it")
                }
            }
        }
    }
}