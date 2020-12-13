package com.example.eventreminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listDummyEvent = ArrayList<Event>()
        listDummyEvent.add(Event(0, "POROS Webinar",
            "Dec 18, 2020", "8.30",
            "Dec 17, 2020", "8.30",
            "Poros Webinar Heking SIAM"))
        listDummyEvent.add(Event(1, "RAION Webinar",
            "Dec 19, 2020", "12.30",
            "Dec 16, 2020", "8.30",
            "Raion Webinar Ttg Gim"))
        listDummyEvent.add(Event(2, "BCC Webinar",
            "Dec 20, 2020", "18.30",
            "Dec 14, 2020", "18.30",
            "BCC Webinar Gojek"))
        listDummyEvent.add(Event(3, "BEM Sidang",
            "Dec 25, 2020", "8.00",
            "Dec 21, 2020", "8.00",
            "BEM Sidang Pertanggungjawaban"))
        listDummyEvent.add(Event(4, "Rapat",
            "Dec 26, 2020", "19.00",
            "Dec 21, 2020", "19.00",
            "Rapat Terakhir"))
        rc_card.setHasFixedSize(true)
        rc_card.layoutManager = LinearLayoutManager(this)
        val adapter = EventAdapter(listDummyEvent, this)
        adapter.notifyDataSetChanged()
        rc_card.adapter = adapter
        eventAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, InputActivity::class.java)
            startActivityForResult(intent, InputActivity.REQUEST_ADD)
        }
    }
}