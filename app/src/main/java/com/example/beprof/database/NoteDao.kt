package com.example.beprof.database



import androidx.room.*
import com.example.beprof.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao  {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes:Note)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)
    @Delete
    suspend fun delete(note:Note)
    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<Note>>
    @Query("DELETE FROM notes")
    fun deleteAll()

}