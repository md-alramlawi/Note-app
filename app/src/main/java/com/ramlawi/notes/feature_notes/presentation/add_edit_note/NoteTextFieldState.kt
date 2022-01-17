package com.ramlawi.notes.feature_notes.presentation.add_edit_note

/**
 * Created by Mohammed Alramlawi on 1/17/2022.
 */

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
