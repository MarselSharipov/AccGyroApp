package ru.unlucky.accgyroapp.data.network

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NetworkApi {

    @FormUrlEncoded
    @POST("nikita_classification/")
    fun sendData(@Field("data") data: ByteArray): Single<Any>

}