package com.ahtezaz.mvvmnoting.db.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    val title: String,
    val location: String,
    val descriptor: String,
    val image: Bitmap?,
    val audioFilePath: String?,

    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0
}
