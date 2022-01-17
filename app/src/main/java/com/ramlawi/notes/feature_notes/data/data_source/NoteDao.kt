package com.ramlawi.notes.feature_notes.data.data_source

import androidx.room.*
import com.ramlawi.notes.feature_notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammed Alramlawi on 1/16/2022.
 */

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}