package com.example.eventreminder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : AppCompatActivity(), View.OnClickListener {
    private var isEdit = false
    private var event: Event? = null
    private var position: Int = 0
    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10

        private const val DATE_PICKER_TAG = "DatePicker"
        private const val TIME_PICKER_ONCE_TAG = "TimePickerOnce"
        private const val TIME_PICKER_REPEAT_TAG = "TimePickerRepeat"
        const val ALERT_DIALOG_DELETE = 20
    }


    private lateinit var alarmReceiver: AlarmReceiver

    lateinit var etInputTanggal : TextInputEditText

    var dateMonthList = arrayOf("Jan", "Feb", "Mar",
            "Apr", "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)


        etInputTanggal = findViewById(R.id.etInputTanggalEvent)
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
            actionBarTitle = "Edit Reminder"
            btnTitle = "Update"

            event?.let {
                etInputEventName.setText(it.eventName)
                etInputTanggalEvent.setText(it.eventDate)
                etInputWaktuEvent.setText(it.eventTime)
                etInputTanggalReminder.setText(it.reminderDate)
                etInputWaktuReminder.setText(it.reminderTime)
                etAdditionalNote.setText(it.keterangan)
            }
        } else {
            actionBarTitle = "Create Reminder"
            btnTitle = "Save"
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        btn_save.text = btnTitle
        btn_save.setOnClickListener(this)


        //Event Date
        val builderEventDate = MaterialDatePicker.Builder.datePicker()
        val pickerEventDate = builderEventDate.build()

        //Event Time
        val pickerEventTimeBuilder = MaterialTimePicker.Builder()
        val EventTimePicker = pickerEventTimeBuilder.setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

        //Reminder Date
        val builderReminderDate = MaterialDatePicker.Builder.datePicker()
        val pickerReminderDate = builderReminderDate.build()

        //Reminder Time
        val pickerReminderTimeBuilder = MaterialTimePicker.Builder()
        val EventReminderPicker = pickerReminderTimeBuilder.setTimeFormat(TimeFormat.CLOCK_24H)
                .build()


        etInputTanggalEvent.setOnClickListener {
            pickerEventDate.show(supportFragmentManager, pickerEventDate.toString())
            pickerEventDate.addOnPositiveButtonClickListener {
                val inputTanggal = pickerEventDate.headerText.substring(0, 5)
                etInputTanggalEvent.setText(inputTanggal)

            }

        }

        etInputWaktuEvent.setOnClickListener {
            EventTimePicker.show(supportFragmentManager, "fragment_tag")
            EventTimePicker.addOnPositiveButtonClickListener {
                if (EventTimePicker.hour in 1..9){
                    val jam = "0$EventTimePicker"
                    val time = "${EventTimePicker.hour}:${EventTimePicker.minute}"
                    etInputWaktuEvent.setText(time)
                }else{
                    val time = "${EventTimePicker.hour}:${EventTimePicker.minute}"
                    etInputWaktuEvent.setText(time)
                }
            }
        }

        var dateRawFormat = ""
        var dateFormatted : MutableList<String> = arrayListOf()

        etInputTanggalReminder.setOnClickListener {
            pickerReminderDate.show(supportFragmentManager, pickerReminderDate.toString())
            pickerReminderDate.addOnPositiveButtonClickListener {
                etInputTanggalReminder.setText(pickerReminderDate.headerText)
                dateRawFormat = pickerReminderDate.headerText
                dateFormatted = dateRawFormat.split(" ") as MutableList<String>
                dateFormatted.set(0, dateMonthList.indexOf(dateFormatted[0]).toString())
                dateFormatted.set(1, dateFormatted[1].replace(",", ""))
                dateFormatted.set(2, dateFormatted[2])

            }
//            etInputTanggalReminder.setText("2020-12-13")
        }




        etInputWaktuReminder.setOnClickListener {
            EventReminderPicker.show(supportFragmentManager, "fragment_tag")
            EventReminderPicker.addOnPositiveButtonClickListener {
                val time = "${EventReminderPicker.hour}.${EventReminderPicker.minute}"
                etInputWaktuReminder.setText(time)
            }
        }

        alarmReceiver = AlarmReceiver()




        btn_save.setOnClickListener {
//            Toast.makeText(applicationContext, etInputTanggalReminder.text.toString(),Toast.LENGTH_LONG).show()
            val date = dateFormatted.toString()
            val time = "21:04"
            val message = "2020-12-13"



            alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME,
                    date,
                    time,
                    message)
//                etInputTanggalReminder.text.toString(),
//                etInputWaktuReminder.text.toString(),
        }




    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    override fun onClick(p0: View?) {
        if (position == 0) {
            Toast.makeText(this, "Halo", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "U Clicked Me!", Toast.LENGTH_LONG).show()
        }
    }
}