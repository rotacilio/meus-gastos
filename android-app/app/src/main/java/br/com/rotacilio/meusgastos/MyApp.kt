package br.com.rotacilio.meusgastos

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import br.com.rotacilio.meusgastos.di.apiModule
import br.com.rotacilio.meusgastos.di.repositoryModule
import br.com.rotacilio.meusgastos.di.retrofitModule
import br.com.rotacilio.meusgastos.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApp)
            modules(listOf(repositoryModule, viewModelModule, retrofitModule, apiModule))
        }
    }
}