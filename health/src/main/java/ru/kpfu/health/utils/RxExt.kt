package ru.kpfu.health.utils

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.observeOnMainThread(): Single<T> = this.observeOn(AndroidSchedulers.mainThread())
fun <T> Single<T>.observeOnComputation(): Single<T> = this.observeOn(Schedulers.computation())
fun <T> Single<T>.subscribeOnIo(): Single<T> = this.subscribeOn(Schedulers.io())

fun <T> Maybe<T>.observeOnMainThread(): Maybe<T> = this.observeOn(AndroidSchedulers.mainThread())
fun <T> Maybe<T>.subscribeOnIo(): Maybe<T> = this.subscribeOn(Schedulers.io())

fun <T> Observable<T>.observeOnMainThread(): Observable<T> = this.observeOn(AndroidSchedulers.mainThread())
fun <T> Observable<T>.observeOnIo(): Observable<T> = this.observeOn(Schedulers.io())
fun <T> Observable<T>.subscribeOnIo(): Observable<T> = this.subscribeOn(Schedulers.io())

fun <T> Flowable<T>.observeOnMainThread(): Flowable<T> = this.observeOn(AndroidSchedulers.mainThread())
fun <T> Flowable<T>.observeOnIo(): Flowable<T> = this.observeOn(Schedulers.io())
fun <T> Flowable<T>.subscribeOnIo(): Flowable<T> = this.subscribeOn(Schedulers.io())

fun Completable.observeOnMainThread(): Completable = this.observeOn(AndroidSchedulers.mainThread())
fun Completable.subscribeOnIo(): Completable = this.subscribeOn(Schedulers.io())
fun Completable.subscribeOnMainThread(): Completable = this.subscribeOn(AndroidSchedulers.mainThread())
fun Completable.subscribeOnComputation(): Completable = this.subscribeOn(Schedulers.computation())

fun Disposable.putIn(disposables: CompositeDisposable) {
    disposables.add(this)
}