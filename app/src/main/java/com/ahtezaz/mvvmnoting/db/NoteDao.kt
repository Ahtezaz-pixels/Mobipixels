package com.ahtezaz.mvvmnoting.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ahtezaz.mvvmnoting.db.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note")
    fun getNotes(): LiveData<List<Note>>
}