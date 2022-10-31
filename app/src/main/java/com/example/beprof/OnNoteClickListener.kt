package com.example.beprof

import com.example.beprof.models.Note

interface OnNoteClickListener {
    fun onClick(itemNote:Note)
    fun onLongClick(note:Note)
}
