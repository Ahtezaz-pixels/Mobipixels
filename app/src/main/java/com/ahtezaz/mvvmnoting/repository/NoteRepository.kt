package com.ahtezaz.mvvmnoting.repository

import com.ahtezaz.mvvmnoting.db.NoteDatabase
import com.ahtezaz.mvvmnoting.db.model.Note

class NoteRepository(var noteDatabase: NoteDatabase) {

    suspend fun insertNote(note: Note) = noteDatabase.getNoteDao().insertNote(note)

    suspend fun deleteNote(note: Note) = noteDatabase.getNoteDao().deleteNote(note)

    fun getListOfNotes() = noteDatabase.getNoteDao().getNotes()
}