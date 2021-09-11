package br.com.rotacilio.meusgastos.views.signup

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.rotacilio.meusgastos.R
import br.com.rotacilio.meusgastos.databinding.ActivitySignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : AppCompatActivity() {

    private val signUpViewModel by viewModel<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivitySignUpBinding>(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this@SignUpActivity
        binding.viewModel = signUpViewModel

        configureObservers()
    }

    private fun configureObservers() {
        signUpViewModel.apply {
            successfulRegistered.observe(this@SignUpActivity) { result ->
                if (result) {
                    Log.i("SignUpActivity", "configureObservers: $result")
                } else {
                    Toast.makeText(this@SignUpActivity, "Não foi possível criar este usuário.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}