package com.ahtezaz.mvvmnoting.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahtezaz.mvvmnoting.databinding.ActivityNoteDetailScreenBinding

class NoteDetailScreen : AppCompatActivity() {
    lateinit var binding: ActivityNoteDetailScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}