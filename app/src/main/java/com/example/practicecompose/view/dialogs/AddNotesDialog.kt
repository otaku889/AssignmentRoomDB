package com.example.practicecompose.view.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.practicecompose.data.room.entity.states.NotesState
import com.example.practicecompose.event.NoteEvents
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun AddNotesDialog(
    state: NotesState,
    event: (NoteEvents) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { event(NoteEvents.HideDialog) },
        confirmButton = {
            val sdf = SimpleDateFormat("M/dd/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            Button(onClick = {
                event(NoteEvents.SetSchedule(currentDate))
                event(NoteEvents.SaveNotes)
            }) { Text(text = "Save") }
        },
        title = { Text(text = "Add Notes") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                // Title
                TextField(
                    value = state.title,
                    onValueChange = {
                        event(NoteEvents.SetTitle(it))
                    },
                    placeholder = { Text(text = "Title") }
                )
                // Note
                TextField(
                    value = state.notes,
                    onValueChange = {
                        event(NoteEvents.SetNote(it))
                    },
                    placeholder = { Text(text = "Notes") }
                )
            }
        }
    )
}
