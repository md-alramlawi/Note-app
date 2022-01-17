package com.ramlawi.notes.feature_notes.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState
import com.ramlawi.notes.feature_notes.domain.model.Note
import com.ramlawi.notes.feature_notes.domain.util.NoteOrder

/**
 * Created by Mohammed Alramlawi on 1/17/2022.
 */

sealed class AddEditNoteEvent{
    data class EnteredTitle(val value: String): AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNoteEvent()
    data class EnteredContent(val value: String): AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditNoteEvent()
    data class ChangeColor(val color: Int): AddEditNoteEvent()
    object SaveNote: AddEditNoteEvent()

}
