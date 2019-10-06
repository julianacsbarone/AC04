package com.jonnypaes.meuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

open class DebugActivity : AppCompatActivity() {

    private val TAG = "LMSApp"
    private val className: String
        get(){
            val s =  javaClass.name
            //br.com.fernandosouza.lmsapp.debugactivity
            return s.substring(s.lastIndexOf("."))
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_debug)
        Log.d(TAG, "$className.onCreate()")
    }

        override fun onStart() {
            super.onStart()
            Log.d(TAG,"$className.onStart()")
        }

    override fun onStop() {
        super.onStart()
        Log.d(TAG,"$className.onStart()")
    }

    override fun onPause() {
        super.onStart()
        Log.d(TAG,"$className.onStart()")
    }

}
