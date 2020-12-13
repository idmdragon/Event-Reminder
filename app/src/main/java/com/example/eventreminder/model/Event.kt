package com.example.eventreminder.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "event")
@Parcelize
data class Event (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id") var id: Int = 0,
        @ColumnInfo(name = "EventName") var eventName : String = "",
        @ColumnInfo(name = "EventDate") var eventDate : String = "",
        @ColumnInfo(name = "EventTime") var eventTime : String = "",
        @ColumnInfo(name = "ReminderDate") var reminderDate : String = "",
        @ColumnInfo(name = "ReminderTime") var reminderTime : String = "",
        @ColumnInfo(name = "AdditionalNote") var keterangan : String = ""
) : Parcelable