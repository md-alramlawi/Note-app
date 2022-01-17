package com.ramlawi.notes.feature_notes.domain.use_case

import com.ramlawi.notes.feature_notes.domain.model.Note
import com.ramlawi.notes.feature_notes.domain.repository.NoteRepository

/**
 * Created by Mohammed Alramlawi on 1/17/2022.
 */

class GetNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note?{
        return repository.getNoteById(id)
    }
}