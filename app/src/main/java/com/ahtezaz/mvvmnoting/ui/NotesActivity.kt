package com.ahtezaz.mvvmnoting.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.ahtezaz.mvvmnoting.R
import com.ahtezaz.mvvmnoting.databinding.ActivityNotesBinding
import com.ahtezaz.mvvmnoting.db.NoteDatabase
import com.ahtezaz.mvvmnoting.db.model.Note
import com.ahtezaz.mvvmnoting.repository.NoteRepository
import com.ahtezaz.mvvmnoting.ui.note_viewmodel.NoteViewModel
import com.ahtezaz.mvvmnoting.ui.note_viewmodel.NoteViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class NotesActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotesBinding
    lateinit var viewModel: NoteViewModel
    private val TAG = "TAG"
    private var convertToBitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val noteRepository = NoteRepository(NoteDatabase(this))
        val noteViewModelProvider = NoteViewModelProviderFactory(noteRepository)
        viewModel = ViewModelProvider(this, noteViewModelProvider)[NoteViewModel::class.java]
        /**
         * open gallery to upload image
         */
        val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                convertToBitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                binding.imUpload.setImageBitmap(convertToBitmap)
                Log.d(TAG, "onCreate: $convertToBitmap")
                binding.tvInsert.text = "Successful"
                binding.tvInsert.setTextColor(ContextCompat.getColor(this ,R.color.bg_green))
            }else{
                binding.tvInsert.text = "Insert Picture"

            }

        }
        /**
         * on click listener for image upload
         */
        binding.imUpload.setOnClickListener {
            getImage.launch("image/*")
        }

        /**
         * on click listener for save note
         */
        binding.btnSaveNote.setOnClickListener {
            if (isNoteValid()) {
                val note = Note(binding.tvTitle.text.toString(),
                    binding.tvLocation.text.toString(),
                    binding.tvDesc.text.toString(),
                    convertToBitmap, null)
                viewModel.insertNote(note)
            } else {
                showSnackbar("Invalid Note Fields")
            }
        }
    }


    private fun insertValidNote() {

    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.tvTitle, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun isNoteValid(): Boolean {
        if (binding.tvTitle.text.isNullOrEmpty()) {
            setErrorMessage(binding.layoutNoteTitle, "Title Is Required")
            return false
        }

        if (binding.tvLocation.text.isNullOrEmpty()) {
            setErrorMessage(binding.layoutNoteLocation, "Location Is Required")
            return false
        }
        if (binding.tvDesc.text.isNullOrEmpty()) {
            setErrorMessage(binding.layoutNoteDesc, "Description Is Required")
            return false
        }
        if (convertToBitmap == null) {
            showSnackbar("Insert Image")
        }

        return true
    }

    private fun setErrorMessage(layoutNoteTitle: TextInputLayout, message: String) {
        layoutNoteTitle.helperText = message
    }
}