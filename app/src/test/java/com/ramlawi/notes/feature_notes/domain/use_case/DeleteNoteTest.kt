package com.ramlawi.notes.feature_notes.domain.use_case

import com.google.common.truth.Truth
import com.ramlawi.notes.feature_notes.data.repository.FakeNoteRepository
import com.ramlawi.notes.feature_notes.domain.model.InvalidNoteException
import com.ramlawi.notes.feature_notes.domain.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by Mohammed Alramlawi on 1/21/2022.
 */

class DeleteNoteTest{
    private val TAG = "add_note_test"
    private lateinit var deleteNote: DeleteNote
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setup(){

        fakeNoteRepository = FakeNoteRepository()
        deleteNote = DeleteNote(fakeNoteRepository)

    }

    @Test
    fun `delete note, correct`() = runBlocking{
        val note = Note(
            title = "title",
            content = "content",
            color = 1,
            timestamp = 1
        )
        fakeNoteRepository.insertNote(note)

        Truth.assertThat(fakeNoteRepository.getNotes().first()).contains(note)

        deleteNote(note)

        Truth.assertThat(fakeNoteRepository.getNotes().first()).doesNotContain(note)
    }
}