package com.example.practicecompose.event

import com.example.practicecompose.data.room.entity.NotesEntity
import com.example.practicecompose.data.room.entity.states.NotesState
import java.util.Date

sealed interface NoteEvents {

    object SaveNotes: NoteEvents
    //For Adding Notes
    object ShowDialog:NoteEvents
    object HideDialog:NoteEvents

    //New Assignment
    //For Deleting Notes
    object DeleteNotes: NoteEvents
    object ShowDeleteDialog:NoteEvents
    object HideDeleteDialog:NoteEvents

    data class SetTitle(val title : String): NoteEvents
    data class SetNote(val note : String): NoteEvents
    data class SetSchedule(val schedules : String): NoteEvents
    /*Removed since it is recommended to delete using query to be more specific and safe deletion

    data class DeleteNote(val noteContent : NotesEntity): NoteEvents

    */
    data class SortNotes(val sortNotes :Sorter): NoteEvents



}