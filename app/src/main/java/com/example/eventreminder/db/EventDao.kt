package com.example.eventreminder.db

import com.example.eventreminder.model.Event
import androidx.room.*


@Dao
interface EventDao {

    @Insert
    fun insert(event: Event)

    @Update
    fun update(event: Event)

    @Delete
    fun delete(event: Event)

    @Query("SELECT * FROM event")
    fun getAll() : List<Event>

    @Query("SELECT * FROM event WHERE id = :id")
    fun getById(id: Int) : List<Event>
}