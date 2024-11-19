package com.example.practicecompose.data.room.entity.states

import com.example.practicecompose.data.room.entity.NotesEntity
import com.example.practicecompose.event.Sorter
import java.util.Date

data class NotesState (
    val noteContent : List<Any> = emptyList(),
    val title : String= "",
    val notes : String= "",
    val schedule : String= "",
    val description : String= "",
    val isAddingNotes : Boolean= false,
    val isDeletingNotes : Boolean= false,
    val sorter: Sorter = Sorter.Notes

)