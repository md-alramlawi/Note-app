package com.ramlawi.notes.feature_notes.domain.use_case

import com.ramlawi.notes.feature_notes.domain.model.Note
import com.ramlawi.notes.feature_notes.domain.repository.NoteRepository

/**
 * Created by Mohammed Alramlawi on 1/16/2022.
 */

class DeleteNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}