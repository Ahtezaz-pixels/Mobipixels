package com.ahtezaz.mvvmnoting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ahtezaz.mvvmnoting.databinding.NoteViewerBinding
import com.ahtezaz.mvvmnoting.ui.note_viewmodel.NoteViewModel

class NoteRecycler(
    var noteItems: List<com.ahtezaz.mvvmnoting.db.model.Note>,
    private val viewModel: NoteViewModel,
) :
    RecyclerView.Adapter<NoteRecycler.NoteViewHolder>() {
    val TAG = "TAG"

    inner class NoteViewHolder(val binding: NoteViewerBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteViewerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteItems[position]
        holder.binding.noteTitle.text = note.title
        holder.binding.noteDescription.text = note.descriptor
        holder.binding.noteLocation.text = note.location

    }

    override fun getItemCount(): Int {
        return noteItems.size
    }
}