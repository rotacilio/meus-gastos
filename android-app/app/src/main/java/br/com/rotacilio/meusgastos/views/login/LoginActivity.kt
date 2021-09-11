package br.com.rotacilio.meusgastos.views.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.rotacilio.meusgastos.BuildConfig
import br.com.rotacilio.meusgastos.R
import br.com.rotacilio.meusgastos.session.impl.SessionManagerUtil

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}