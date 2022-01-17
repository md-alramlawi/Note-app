package com.ramlawi.notes.feature_notes.domain.use_case

/**
 * Created by Mohammed Alramlawi on 1/16/2022.
 */

data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote
)
