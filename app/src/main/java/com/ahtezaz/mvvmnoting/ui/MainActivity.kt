package com.ahtezaz.mvvmnoting.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ahtezaz.mvvmnoting.R
import com.ahtezaz.mvvmnoting.databinding.ActivityMainBinding
import com.ahtezaz.mvvmnoting.databinding.ActivityNotesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val TAG = "TAG"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: Call`")
        loadSplashScreen()
    }

    private fun loadSplashScreen() = CoroutineScope(Dispatchers.Main).launch {
        Log.d(TAG, "loadSplashScreen: In Notes")
        delay(50)
        Log.d(TAG, "loadSplashScreen: In Notes 4 Seconds")
        startActivity(Intent(this@MainActivity, NotesListActivity::class.java))
    }
}