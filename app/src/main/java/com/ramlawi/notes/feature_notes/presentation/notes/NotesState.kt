package com.ramlawi.notes.feature_notes.presentation.notes

import com.ramlawi.notes.feature_notes.domain.model.Note
import com.ramlawi.notes.feature_notes.domain.util.NoteOrder
import com.ramlawi.notes.feature_notes.domain.util.OrderType

/**
 * Created by Mohammed Alramlawi on 1/16/2022.
 */

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
