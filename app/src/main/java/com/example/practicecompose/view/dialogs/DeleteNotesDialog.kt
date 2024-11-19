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

//New Assignment Delete by title
@Composable
fun DeleteNotesDialog(
    state: NotesState,
    event: (NoteEvents) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { event(NoteEvents.HideDeleteDialog) },
        confirmButton = {
            Button(onClick = {
                event(NoteEvents.DeleteNotes)
            }) { Text(text = "Delete") }
        },
        title = { Text(text = "Delete Notes by Title") },
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
            }
        }
    )
}
