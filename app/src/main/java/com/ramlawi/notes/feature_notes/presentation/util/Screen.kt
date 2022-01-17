package com.ramlawi.notes.feature_notes.presentation.util

/**
 * Created by Mohammed Alramlawi on 1/17/2022.
 */

sealed class Screen(val route: String) {
    object NotesScreen: Screen("notes_screen")
    object AddEditNoteScreen: Screen("add_edit_note_screen")
}