package com.example.practicecompose.data.room.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicecompose.data.room.dao.NotesDao

import com.example.practicecompose.data.room.entity.NotesEntity


@Database(
    entities = [NotesEntity::class],
    version = 2,
    exportSchema = true,
)

abstract class NotesDB: RoomDatabase() {
    abstract val dao : NotesDao
}
