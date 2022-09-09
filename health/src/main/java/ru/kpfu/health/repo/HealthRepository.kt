package ru.kpfu.health.repo

import io.reactivex.subjects.PublishSubject
import java.io.File

class HealthRepository {
    val loggerSubject = PublishSubject.create<String>()
}