package com.ahtezaz.mvvmnoting.ui.note_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahtezaz.mvvmnoting.repository.NoteRepository
import kotlinx.coroutines.launch


class NoteViewModel(private val repository: NoteRepository) : ViewModel() {


    fun insertNote(note: com.ahtezaz.mvvmnoting.db.model.Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun deleteNote(note: com.ahtezaz.mvvmnoting.db.model.Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun getListOfNote() = repository.getListOfNotes()
}