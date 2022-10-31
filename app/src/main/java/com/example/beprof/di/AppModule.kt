package com.example.beprof.di

import android.app.Application
import androidx.room.Room
import com.example.beprof.database.DataBase
import com.example.beprof.database.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun getDataBase(application: Application): DataBase {
        return Room.databaseBuilder(
            application, DataBase::class.java,
            "notesDb"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    fun getNoteDao(db:DataBase):NoteDao{
        return db.daoNotes()

    }

}