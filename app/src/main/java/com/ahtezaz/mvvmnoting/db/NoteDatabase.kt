package com.ahtezaz.mvvmnoting.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahtezaz.mvvmnoting.db.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"

        @Volatile
        private var instance: NoteDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java,
                DATABASE_NAME).build()
    }
}
