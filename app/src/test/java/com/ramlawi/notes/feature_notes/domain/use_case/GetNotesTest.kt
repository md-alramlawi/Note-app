package com.ramlawi.notes.feature_notes.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.ramlawi.notes.feature_notes.data.repository.FakeNoteRepository
import com.ramlawi.notes.feature_notes.domain.model.Note
import com.ramlawi.notes.feature_notes.domain.util.NoteOrder
import com.ramlawi.notes.feature_notes.domain.util.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Created by Mohammed Alramlawi on 1/21/2022.
 */

class GetNotesTest{

    private lateinit var getNotes: GetNotes
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setup(){

        fakeNoteRepository = FakeNoteRepository()
        getNotes = GetNotes(fakeNoteRepository)

        val notesToInsert = mutableListOf<Note>()

        ('a'..'z').forEachIndexed{index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    content = c.toString(),
                    timestamp = index.toLong(),
                    color = index
                )
            )
        }

        notesToInsert.shuffle()

        runBlocking {
            notesToInsert.forEach { note ->
                fakeNoteRepository.insertNote(note)
            }
        }

    }

    @Test
    fun `Order notes by title ascending, correct order`() = runBlocking{
        // because that method returns Flow and not List we should add .first() to get the first emission only
        val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()

        for(i in 0..notes.size -2){
            assertThat(notes[i].title).isLessThan(notes[i+1].title)
        }
    }

    @Test
    fun `Order notes by title descending, correct order`() = runBlocking{
        // because that method returns Flow and not List we should add .first() to get the first emission only
        val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()

        for(i in 0..notes.size -2){
            assertThat(notes[i].title).isGreaterThan(notes[i+1].title)
        }
    }

    @Test
    fun `Order notes by color ascending, correct order`() = runBlocking{
        // because that method returns Flow and not List we should add .first() to get the first emission only
        val notes = getNotes(NoteOrder.Color(OrderType.Ascending)).first()

        for(i in 0..notes.size -2){
            assertThat(notes[i].color).isLessThan(notes[i+1].color)
        }
    }

    @Test
    fun `Order notes by color descending, correct order`() = runBlocking{
        // because that method returns Flow and not List we should add .first() to get the first emission only
        val notes = getNotes(NoteOrder.Color(OrderType.Descending)).first()

        for(i in 0..notes.size -2){
            assertThat(notes[i].color).isGreaterThan(notes[i+1].color)
        }
    }

    @Test
    fun `Order notes by date ascending, correct order`() = runBlocking{
        // because that method returns Flow and not List we should add .first() to get the first emission only
        val notes = getNotes(NoteOrder.Date(OrderType.Ascending)).first()

        for(i in 0..notes.size -2){
            assertThat(notes[i].timestamp).isLessThan(notes[i+1].timestamp)
        }
    }

    @Test
    fun `Order notes by date descending, correct order`() = runBlocking{
        // because that method returns Flow and not List we should add .first() to get the first emission only
        val notes = getNotes(NoteOrder.Date(OrderType.Descending)).first()

        for(i in 0..notes.size -2){
            assertThat(notes[i].timestamp).isGreaterThan(notes[i+1].timestamp)
        }
    }


}