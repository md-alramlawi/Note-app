package com.ramlawi.notes.feature_notes.data.repository

import com.ramlawi.notes.feature_notes.data.data_source.NoteDao
import com.ramlawi.notes.feature_notes.domain.model.Note
import com.ramlawi.notes.feature_notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammed Alramlawi on 1/16/2022.
 */

class NoteRepositoryImpl(
    private val dao: NoteDao
): NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }
}