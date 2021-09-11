package br.com.rotacilio.meusgastos.di

import br.com.rotacilio.meusgastos.BuildConfig
import br.com.rotacilio.meusgastos.api.GithubApi
import br.com.rotacilio.meusgastos.api.UsersApi
import br.com.rotacilio.meusgastos.repositories.UserRepository
import br.com.rotacilio.meusgastos.views.login.LoginViewModel
import br.com.rotacilio.meusgastos.views.signup.SignUpViewModel
import br.com.rotacilio.meusgastos.views.users.UserViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
    viewModel { LoginViewModel() }
    viewModel { SignUpViewModel(get()) }
}

val repositoryModule = module {
    single { UserRepository(get()) }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }
    fun provideUsersApi(retrofit: Retrofit): UsersApi {
        return retrofit.create(UsersApi::class.java)
    }

    single { provideUseApi(get()) }
    single { provideUsersApi(get()) }
}

val retrofitModule = module {
    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        return okHttpClientBuilder.build();
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpLoggingInterceptor() }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
}