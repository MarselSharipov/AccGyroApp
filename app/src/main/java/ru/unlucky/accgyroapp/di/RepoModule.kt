package ru.unlucky.accgyroapp.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.kpfu.health.repo.HealthRepository
import ru.unlucky.accgyroapp.BuildConfig
import ru.unlucky.accgyroapp.data.network.NetworkApi
import ru.unlucky.accgyroapp.data.network.repositories.ApiRepository
import java.util.concurrent.TimeUnit

val repoModule = module {

    // Network
    single<NetworkApi> {
        val httpLoggingLevel = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val okClient = OkHttpClient().newBuilder()
//                .addInterceptor(StuderusTokenInterceptor(get()))
                .addInterceptor(HttpLoggingInterceptor().apply { level = httpLoggingLevel })
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()

        Retrofit.Builder()
                .baseUrl("${BuildConfig.BASE_URL}/")
                .client(okClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkApi::class.java)
    }

    single { ApiRepository(get()) }
}