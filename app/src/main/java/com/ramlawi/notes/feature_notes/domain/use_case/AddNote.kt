package com.ramlawi.notes.feature_notes.domain.use_case

import com.ramlawi.notes.feature_notes.domain.model.InvalidNoteException
import com.ramlawi.notes.feature_notes.domain.model.Note
import com.ramlawi.notes.feature_notes.domain.repository.NoteRepository

/**
 * Created by Mohammed Alramlawi on 1/16/2022.
 */

class AddNote(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)

    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("The title of the note can't be empty.")
        }

        if(note.content.isBlank()){
            throw InvalidNoteException("The content of the note can't be empty.")
        }

        repository.insertNote(note)
    }
}