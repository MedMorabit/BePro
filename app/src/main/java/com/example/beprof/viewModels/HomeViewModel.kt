package com.example.beprof.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.beprof.database.NoteDao
import com.example.beprof.models.Note
import com.example.beprof.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteDao:NoteDao
) :ViewModel(){
 val note=noteDao.getNotes().asLiveData()
    fun insertNote(note:Note){
        viewModelScope.launch {
            noteDao.insert(note)
        }
    }
    fun deleteNote(note:Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.delete(note)
        }
    }
    fun updateNote(note: Note)=viewModelScope.launch(Dispatchers.IO){
        noteDao.update(note)
    }
}