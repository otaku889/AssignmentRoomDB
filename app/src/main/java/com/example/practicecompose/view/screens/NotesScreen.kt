package com.example.practicecompose.view.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicecompose.data.room.entity.NotesEntity
import com.example.practicecompose.data.room.entity.states.NotesState
import com.example.practicecompose.event.NoteEvents
import com.example.practicecompose.view.dialogs.AddNotesDialog
import com.example.practicecompose.view.dialogs.DeleteNotesDialog
import com.example.practicecompose.viewmodel.NotesViewModel



@Composable
fun NotesScreen(
    state: NotesState,
    onEvent: (NoteEvents) -> Unit,
) {
    Scaffold(floatingActionButton = {


        FloatingActionButton(onClick = {
            onEvent(NoteEvents.ShowDialog)
        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Notes", tint = Color.Black)
        }
        IconButton(onClick = {
            onEvent(NoteEvents.ShowDeleteDialog)
        },
            modifier = Modifier.width(120.dp)
                .height(120.dp)
                .padding(5.dp)) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete contact",
                modifier = Modifier.width(120.dp)
                    .height(120.dp)
                    .padding(bottom = 15.dp))
        }
    }) { padding ->

        if (state.isAddingNotes) {
            AddNotesDialog(state = state, event = onEvent)
        }
        if (state.isDeletingNotes) {
            DeleteNotesDialog(state = state, event = onEvent)
        }

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.noteContent) { notes ->

                    when(notes){
                        //Para pag gusto ko display lahat
                           is NotesEntity ->{
                           Row(modifier = Modifier.fillMaxWidth()) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${ notes.titles }",
                                        fontSize = 20.sp,
                                        textDecoration = TextDecoration.Underline,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "${notes.notes}",
                                        fontSize = 12.sp
                                    )
                                    Text(
                                        text = "${notes.sched}",
                                        fontSize = 12.sp
                                    )

                                    HorizontalDivider( modifier = Modifier
                                        .background(Color.Black))
                                }

                           }
                         }

                        //For Specific data
                        else->{
                            Row(modifier = Modifier.fillMaxWidth()) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${ notes }",
                                        fontSize = 20.sp,
                                        textDecoration = TextDecoration.Underline,
                                        fontWeight = FontWeight.Bold
                                    )
                                    HorizontalDivider( modifier = Modifier
                                        .background(Color.Black))
                                }

                            }
                        }

                    }

            }

        }

    }

}