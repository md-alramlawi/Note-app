package com.ramlawi.notes.feature_notes.data.repository

import com.ramlawi.notes.feature_notes.domain.model.Note
import com.ramlawi.notes.feature_notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Mohammed Alramlawi on 1/21/2022.
 */

class FakeNoteRepository: NoteRepository {

    private val notes = mutableListOf<Note>()
    override fun getNotes(): Flow<List<Note>> {
        return flow { emit(notes) }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return notes.find { it.id == id }
    }

    override suspend fun insertNote(note: Note) {
        notes.add(note)
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }
}