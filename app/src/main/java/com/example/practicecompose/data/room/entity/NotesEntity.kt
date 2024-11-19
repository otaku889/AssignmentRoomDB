package com.example.practicecompose.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotesEntity(
//Insert A list of notes
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "title") val titles: String?,
    @ColumnInfo(name = "note") val notes: String?,
    @ColumnInfo(name = "schedule2") val sched: String?,
    @PrimaryKey(autoGenerate = true)
    val nID: Int = 0,
)

/**
 * 1. Create update functionality."Done"
 * 2. Delete using query by title. "Done"
 * 3. Update schedule to schedule2. (Upgrade database) "Done"
 * 4. Add additional column. "Done"
 * 5. query title and note only."Done"
 * 6. Insert a list of notes.
 */