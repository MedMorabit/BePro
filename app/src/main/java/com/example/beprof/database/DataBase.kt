package com.example.beprof.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.beprof.models.Note

@Database(entities=[Note::class], version = 7)
abstract class DataBase : RoomDatabase() {
    abstract fun daoNotes():NoteDao
}