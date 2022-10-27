package io.redandroid.gameofthrones

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.AndroidEntryPoint

/**
 * The MainActivity is the starting point of this app.
 * Depending on the navigation graph, the according content will be displayed here.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.iv_dark_mode).setOnClickListener {
            toggleDarkMode()
        }
    }

    /**
     * Switch between dark and light mode. For brevity, the selection is not persisted and
     * has to be done for every app restart.
     */
    private fun toggleDarkMode() {
        val newMode = when (AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.MODE_NIGHT_NO -> AppCompatDelegate.MODE_NIGHT_YES
            AppCompatDelegate.MODE_NIGHT_UNSPECIFIED -> AppCompatDelegate.MODE_NIGHT_YES
            AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY -> AppCompatDelegate.MODE_NIGHT_YES
            AppCompatDelegate.MODE_NIGHT_YES -> AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> AppCompatDelegate.MODE_NIGHT_NO
            else -> AppCompatDelegate.MODE_NIGHT_YES
        }
        AppCompatDelegate.setDefaultNightMode(newMode)
        delegate.applyDayNight()
    }
}