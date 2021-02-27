package com.swiftly.codingexersicejavi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProviders
import com.swiftly.codingexersicejavi.R
import com.swiftly.codingexersicejavi.framework.ManagerSpecialsViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mainHandler: Handler
    private lateinit var viewModel: ManagerSpecialsViewModel
    private val REFRESH_RATE: Long = 10000
    /**
     * For simplicty will be running a loop during the main activity lifecycle when the user is
     * active on the screen.
     * However a recommended solution for receiving accurate updates and saving network bandwidth
     * would be to use a background push notification service to trigger the network fetch.
     */
    private val updateSpecials = object : Runnable {
        override fun run() {
            fetchSpecials()
            mainHandler.postDelayed(this, REFRESH_RATE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainHandler = Handler(Looper.getMainLooper())

        //viewmodel is shared between main activity and manager specials fragment
        viewModel = ViewModelProviders.of(this).get(ManagerSpecialsViewModel::class.java)

    }


    override fun onResume() {
        super.onResume()
        //start polling network again
        mainHandler.post(updateSpecials)
    }

    override fun onPause(){
        super.onPause()
        //stop polling network since user isn't seeing view
        mainHandler.removeCallbacks(updateSpecials)
    }

    private fun fetchSpecials(){
        viewModel.getSpecials()
    }

}