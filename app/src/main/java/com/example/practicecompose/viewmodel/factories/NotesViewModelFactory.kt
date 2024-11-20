package com.example.practicecompose.viewmodel.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.practicecompose.data.room.database.NotesDB
import com.example.practicecompose.viewmodel.NotesViewModel

class NotesViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE NotesEntity ADD COLUMN description TEXT")
            database.execSQL("ALTER TABLE NotesEntity RENAME COLUMN schedule TO schedule2")
        }
    }
    private  val db by lazy{
        Room.databaseBuilder(
            context,
            NotesDB::class.java,
            "notes.db"
        ).addMigrations(MIGRATION_1_2).build()
    }
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(db.dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}