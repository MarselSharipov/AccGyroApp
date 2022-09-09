package ru.unlucky.accgyroapp.ui

import io.reactivex.disposables.CompositeDisposable
import moxy.MvpPresenter
import moxy.MvpView

abstract class BasePresenter<V: MvpView>: MvpPresenter<V>() {

    protected val disposables = CompositeDisposable()

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

}