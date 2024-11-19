package com.example.practicecompose.view.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.practicecompose.data.room.database.NotesDB
import com.example.practicecompose.data.room.entity.NotesEntity
import com.example.practicecompose.ui.theme.PracticecomposeTheme
import com.example.practicecompose.view.screens.NotesScreen
import com.example.practicecompose.viewmodel.NotesViewModel
import kotlinx.coroutines.flow.Flow

class NotesActivity : ComponentActivity(){

    //This is for the update I used Manual Migration
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE NotesEntity ADD COLUMN description TEXT")
            database.execSQL("ALTER TABLE NotesEntity RENAME COLUMN schedule TO schedule2")
        }
    }
    //Dito need initialize yung DB
    private  val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            NotesDB::class.java,
            "notes.db"
        ).addMigrations(MIGRATION_1_2).build()
    }
    private val vm by viewModels<NotesViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return NotesViewModel(db.dao) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")

                }
            }
        }
    )

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
