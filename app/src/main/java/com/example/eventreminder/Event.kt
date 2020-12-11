package com.example.eventreminder

data class Event (
        var id : Int?,
        var eventName : String,
        var eventDate : String,
        var eventTime : String,
        var reminderDate : String,
        var reminderTime : String,
        var keterangan : String
){
}