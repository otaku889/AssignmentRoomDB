package com.example.practicecompose.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.practicecompose.ui.theme.PracticecomposeTheme
import com.example.practicecompose.view.screens.NotesScreen
import com.example.practicecompose.viewmodel.NotesViewModel
import com.example.practicecompose.viewmodel.factories.NotesViewModelFactory

class NotesActivity : ComponentActivity(){

    private val vm: NotesViewModel by viewModels {
        NotesViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            PracticecomposeTheme {
                val state by vm.state.collectAsState()
                NotesScreen(state = state, onEvent = vm::onEvent)
            }
        }
    }
}
