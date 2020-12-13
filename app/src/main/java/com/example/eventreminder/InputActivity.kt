package com.example.eventreminder

import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_input.*
import kotlin.math.log

class InputActivity : AppCompatActivity(), View.OnClickListener {
    private var isEdit = false
    private var event: Event? = null
    private var position: Int = 0
    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_ADD = 100
        const val REQUEST_UPDATE = 200
    }
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
        supportActionBar?.title = "Create Reminder"

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

        supportActionBar?.elevation = 0f

        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#ffffff")))
        event = intent.getParcelableExtra(EXTRA_NOTE)
        if (event != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            event = Event(0, "", "", "", "", "", "")
        }
        val actionBarTitle: String
        val btnTitle: String

        if (isEdit) {
            actionBarTitle = "Ubah"
            btnTitle = "Update"

            event?.let {
                etInputEventName.setText(it.eventName)
                tvPickEventDate.text = it.eventDate
                tvPickEventTime.text = it.eventTime
                tvReminderDate.text = it.reminderDate
                tvReminderTime.text = it.reminderTime
                etAdditionalNote.setText(it.keterangan)
            }
        } else {
            actionBarTitle = "Tambah"
            btnTitle = "Simpan"
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_save.text = btnTitle
        btn_save.setOnClickListener(this)

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

    override fun onClick(p0: View?) {
        if (position == 0) {
            Toast.makeText(this, "Halo" , Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "U Clicked Me!" , Toast.LENGTH_LONG).show()
        }
    }
}