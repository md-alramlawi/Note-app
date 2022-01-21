package com.ramlawi.notes.feature_notes.domain.use_case

import com.google.common.truth.Truth.assertThat
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

class AddNoteTest{

    private val TAG = "add_note_test"
    private lateinit var addNote: AddNote
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setup(){

        fakeNoteRepository = FakeNoteRepository()
        addNote = AddNote(fakeNoteRepository)


    }

    @Test(expected = InvalidNoteException::class)
    fun `insert no title, throw exception`() = runBlocking{
        addNote(
            Note(
                title = "",
                content = "content",
                color = 1,
                timestamp = 1
            )
        )
    }

    @Test(expected = InvalidNoteException::class)
    fun `insert no content, throw exception`() = runBlocking{
        addNote(
            Note(
                title = "title",
                content = "",
                color = 1,
                timestamp = 1
            )
        )
    }

    @Test
    fun `insert note, success`(): Unit = runBlocking{
        val note = Note(
            title = "title",
            content = "content",
            color = 1,
            timestamp = 1
        )
        addNote(note)

        assertThat(fakeNoteRepository.getNotes().first()).contains(note)
    }
}
