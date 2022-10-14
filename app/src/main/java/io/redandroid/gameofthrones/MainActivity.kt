package io.redandroid.gameofthrones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * The MainActivity is the starting point of this app.
 * Depending on the navigation graph, the according content will be displayed here.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}