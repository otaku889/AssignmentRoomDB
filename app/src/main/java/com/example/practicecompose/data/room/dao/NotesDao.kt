package com.example.practicecompose.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.practicecompose.data.room.entity.NotesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: NotesEntity)

    //Delete by title
    @Query("DELETE FROM NotesEntity WHERE title = :title")
    suspend fun delete(title: String)


    //Get all from database for display sorted by title
    @Query("SELECT * FROM NotesEntity ORDER BY title ASC")
       fun getAllNotes(): Flow<List<NotesEntity>>

    @Query("SELECT title FROM NotesEntity")
    fun getAllTitles(): Flow<List<String>>

    //Get all notes database for display
    @Query("SELECT note FROM NotesEntity")
    fun getAllNotesbyNote(): Flow<List<String>>

}