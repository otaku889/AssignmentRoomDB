package com.example.practicecompose.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.practicecompose.data.room.dao.NotesDao
import com.example.practicecompose.data.room.database.NotesDB
import com.example.practicecompose.data.room.entity.NotesEntity
import com.example.practicecompose.data.room.entity.states.NotesState
import com.example.practicecompose.event.NoteEvents
import com.example.practicecompose.event.Sorter

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch




class NotesViewModel( private val dao : NotesDao,
) : ViewModel() {



    private val _state = MutableStateFlow(NotesState())
    private val _sortType = MutableStateFlow(Sorter.Whole)
    private val _contacts = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                Sorter.Title -> dao.getAllTitles()
                Sorter.Notes -> dao.getAllNotesbyNote()
                Sorter.Whole -> dao.getAllNotes()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _sortType, _contacts) { state, sortType, contacts ->
        Log.i("Yuki","HERE $contacts")
        state.copy(
            noteContent = contacts,
            sorter=sortType,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NotesState())
    fun onEvent(event: NoteEvents){

        when(event){

            /*is NoteEvents.DeleteNote -> {
                val title = _state.value.title
                viewModelScope.launch { dao.delete(title) } }*/

            //Add notes
            NoteEvents.HideDialog -> { _state.update { it.copy(isAddingNotes = false) } }
            NoteEvents.ShowDialog -> { _state.update { it.copy(isAddingNotes = true) } }

            //Delete Notes
            NoteEvents.HideDeleteDialog -> _state.update { it.copy(isDeletingNotes = false) }
            NoteEvents.ShowDeleteDialog -> _state.update { it.copy(isDeletingNotes = true) }

            //This event is for saving the notes with complete details
            NoteEvents.SaveNotes -> {
                val title = _state.value.title
                val notes = _state.value.notes
                val schedule = _state.value.schedule
                val description = _state.value.description

                //If one component is blank do nothing para kunwari lahat required
                if(title.isBlank()|| notes.isBlank() || schedule.isBlank()){return}

                val note = NotesEntity(
                    titles = title,
                    notes = notes,
                    sched = schedule,
                    description=description,
                )
                viewModelScope.launch {
                    dao.insertAll(note)
                    Log.i("Yuki","Notes are inserted")
                }
                _state.update { it.copy(
                    isAddingNotes = false,
                    title = "",
                    notes ="",
                    schedule = "",
                    description="",
                ) }

            }

            NoteEvents.DeleteNotes -> {
                val title = _state.value.title
                viewModelScope.launch { dao.delete(title)}

                _state.update { it.copy(
                    isDeletingNotes = false,
                    title = "",
                    notes ="",
                    schedule = "",
                ) }

            }
            is NoteEvents.SetNote -> { _state.update { it.copy(notes = event.note) } }

            is NoteEvents.SetSchedule -> { _state.update { it.copy(schedule = event.schedules) } }

            is NoteEvents.SetTitle -> { _state.update { it.copy(title = event.title) } }



            is NoteEvents.SortNotes -> {
                _sortType.value=event.sortNotes
            }


        }
    }
}