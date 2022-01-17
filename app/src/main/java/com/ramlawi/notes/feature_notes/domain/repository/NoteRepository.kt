package com.ramlawi.notes.feature_notes.domain.repository

import com.ramlawi.notes.feature_notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammed Alramlawi on 1/16/2022.
 */

interface NoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}