package com.ramlawi.notes.feature_notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ramlawi.notes.ui.theme.*
import java.lang.Exception

/**
 * Created by Mohammed Alramlawi on 1/16/2022.
 */

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
){
    companion object{
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}


class InvalidNoteException(message: String): Exception(message)