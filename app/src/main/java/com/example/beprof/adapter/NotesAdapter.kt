package com.example.beprof.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.beprof.OnNoteClickListener
import com.example.beprof.R
import com.example.beprof.models.Note
import kotlinx.android.synthetic.main.note_items.view.*

class NotesAdapter(private val listener:OnNoteClickListener)
    : ListAdapter<Note, NotesAdapter.NoteHolder>(DifferUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.note_items,parent,false)

        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
       val currentItem=getItem(position)
        holder.bind(currentItem)
    }


    inner class NoteHolder(itemView: View) : ViewHolder(itemView){
      fun bind(note:Note){

          itemView.apply {

                  noteText.text=note.note
                  titleNote.text=note.title
                  dateNote.text=note.instanceDate
                  cardNote.setCardBackgroundColor(Color.parseColor(note.color))
                      cardNote.setOnClickListener {
                          listener.onClick(note)
                      }
                      setOnLongClickListener{
                        listener.onLongClick(note)
                          false
                      }

              }
          }
      }
    }

    class DifferUtil():DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
            oldItem.id==newItem.id


        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =oldItem==newItem

    }
