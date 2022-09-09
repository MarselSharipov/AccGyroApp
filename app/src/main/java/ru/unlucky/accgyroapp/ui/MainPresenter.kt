package ru.unlucky.accgyroapp.ui

import ru.kpfu.health.body_position.HumanActivityLogger
import ru.kpfu.health.repo.HealthRepository
import ru.unlucky.accgyroapp.data.network.repositories.ApiRepository
import ru.unlucky.accgyroapp.utils.observeOnMainThread
import ru.unlucky.accgyroapp.utils.putIn

class MainPresenter(private val humanActivityLogger: HumanActivityLogger,
                    private val healthRepository: HealthRepository,
                    private val apiRepository: ApiRepository): BasePresenter<IMainActivity>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        humanActivityLogger.onStart()

        healthRepository.loggerSubject
            .observeOnMainThread()
            .subscribe(this::sendData)
            .putIn(disposables)
    }


    override fun onDestroy() {
        humanActivityLogger.onDestroy()
        super.onDestroy()
    }

    private fun sendData(data: String) {
        apiRepository.sendData(data.toByteArray())
            .observeOnMainThread()
            .subscribe()
            .putIn(disposables)
    }

}