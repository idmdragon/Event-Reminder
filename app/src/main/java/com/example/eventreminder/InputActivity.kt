package com.example.eventreminder

import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlin.math.log

class InputActivity : AppCompatActivity() {
    lateinit var btnEventDate : LinearLayout
    lateinit var btnEventTime : LinearLayout
    lateinit var tvPickEventDate : TextView
    lateinit var tvPickEventTime : TextView
    lateinit var etInputTanggal : TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        btnEventDate = findViewById(R.id.btnEventDate)
        tvPickEventDate = findViewById(R.id.tvPickEventDate)
        tvPickEventTime = findViewById(R.id.tvPickEventTime)
        btnEventTime = findViewById(R.id.btnEventTime)
        etInputTanggal = findViewById(R.id.etInputTanggal)
        supportActionBar?.setTitle("Create Reminder")

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)



        supportActionBar?.elevation = 0f

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#ffffff")))


        val builder = MaterialDatePicker.Builder.datePicker()

        val picker = builder.build()

        val dateBuilder = MaterialTimePicker.Builder()
        val materialTimePicker = dateBuilder.setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

        btnEventDate.setOnClickListener {
            picker.show(supportFragmentManager, picker.toString())
            picker.addOnPositiveButtonClickListener {
                tvPickEventDate.text = picker.headerText
            }
        }

        btnEventTime.setOnClickListener {
            materialTimePicker.show(supportFragmentManager, "fragment_tag")
            materialTimePicker.addOnPositiveButtonClickListener {
                val time = "${materialTimePicker.hour}.${materialTimePicker.minute}"
                tvPickEventTime.text = time

                etInputTanggal.setText(time)
                Log.d("Debug", time)
            }

        }
    }
}