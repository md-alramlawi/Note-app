package com.ramlawi.notes.feature_notes.presentation.notes

import com.ramlawi.notes.feature_notes.domain.model.Note
import com.ramlawi.notes.feature_notes.domain.util.NoteOrder

/**
 * Created by Mohammed Alramlawi on 1/16/2022.
 */

sealed class NotesEvent{
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    object ToggleOrderSection: NotesEvent()
}
