package io.redandroid.gameofthrones

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * The main entry point of the app.
 */
@HiltAndroidApp
class GameOfThronesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}