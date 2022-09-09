package ru.kpfu.health.body_position

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import ru.kpfu.health.repo.HealthRepository
import ru.kpfu.health.utils.observeOnIo
import ru.kpfu.health.utils.subscribeOnIo
import java.io.File
import java.util.concurrent.TimeUnit

class HumanActivityLogger(private val context: Context,
                          private val repository: HealthRepository): SensorEventListener {

    private var mSensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private var list = arrayListOf<String>()

    private lateinit var sensorsSubscription: Disposable

    fun onStart() {
        registerListeners()
        sensorsSubscription = createSensorsTimer()
    }

    fun onDestroy() {
        mSensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//        doNothing
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val sensor = event!!.sensor
        when (sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                list.add("A," + System.currentTimeMillis().toString() + "," + event.values.joinToString(","))
            }
            Sensor.TYPE_LINEAR_ACCELERATION -> {
                list.add("L," + System.currentTimeMillis().toString() + "," + event.values.joinToString(","))

            }
            Sensor.TYPE_GYROSCOPE -> {
                list.add("G," + System.currentTimeMillis().toString() + "," + event.values.joinToString(","))
            }
        }
    }

    private fun registerListeners() {
        mSensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST)
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_GAME)
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_FASTEST)
    }

    private fun createSensorsTimer(): Disposable {
        return Observable.interval(1, 1, TimeUnit.MILLISECONDS)
                .subscribeOnIo()
                .observeOnIo()
                .subscribe(this::onSensorsInterval) {}
    }

    private fun onSensorsInterval(result: Long) {
        repository.loggerSubject.onNext(list.joinToString(","))
        list.clear()
    }

}