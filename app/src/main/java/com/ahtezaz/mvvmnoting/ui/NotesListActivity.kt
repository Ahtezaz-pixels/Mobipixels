package com.ahtezaz.mvvmnoting.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahtezaz.mvvmnoting.adapter.NoteRecycler
import com.ahtezaz.mvvmnoting.databinding.ActivityNotesListBinding
import com.ahtezaz.mvvmnoting.db.NoteDatabase
import com.ahtezaz.mvvmnoting.repository.NoteRepository
import com.ahtezaz.mvvmnoting.ui.note_viewmodel.NoteViewModel
import com.ahtezaz.mvvmnoting.ui.note_viewmodel.NoteViewModelProviderFactory

class NotesListActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotesListBinding
    lateinit var viewModel: NoteViewModel
    lateinit var noteAdapter: NoteRecycler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val noteRepository = NoteRepository(NoteDatabase(this))
        val noteViewModelProvider = NoteViewModelProviderFactory(noteRepository)
        viewModel = ViewModelProvider(this, noteViewModelProvider)[NoteViewModel::class.java]
        /**
         * register recycler view
         */

        noteAdapter = NoteRecycler(listOf(), viewModel)
        binding.rvNotesItem.layoutManager = LinearLayoutManager(this)
        binding.rvNotesItem.adapter = noteAdapter
        registerAddNoteClickListener()
        viewModel.getListOfNote().observe(this, Observer {
            if (!it.isEmpty()) {
                noteAdapter.noteItems = it
                noteAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun registerAddNoteClickListener() {
        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }
    }
}