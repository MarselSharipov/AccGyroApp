package ru.unlucky.accgyroapp.data.network.repositories

import io.reactivex.Single
import ru.unlucky.accgyroapp.data.network.NetworkApi

class ApiRepository(private val retrofit: NetworkApi) {

    fun sendData(data: ByteArray): Single<Any> = retrofit.sendData(data)

}