package com.example.eventreminder

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventreminder.db.EventRoomDatabase
import com.example.eventreminder.model.Event
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_name.*
import kotlinx.android.synthetic.main.item_name.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val listDummyEvent = ArrayList<Event>()
//        listDummyEvent.add(Event(0, "POROS Webinar",
//            "Dec 18", "8.30",
//            "Dec 17", "8.30",
//            "Poros Webinar Heking SIAM"))
//        listDummyEvent.add(Event(1, "RAION Webinar",
//            "Dec 19", "12.30",
//            "Dec 16", "8.30",
//            "Raion Webinar Ttg Gim"))
//        listDummyEvent.add(Event(2, "BCC Webinar",
//            "Dec 20", "18.30",
//            "Dec 14", "18.30",
//            "BCC Webinar Gojek"))
//        listDummyEvent.add(Event(3, "BEM Sidang",
//            "Dec 25", "8.00",
//            "Dec 21", "8.00",
//            "BEM Sidang Pertanggungjawaban"))
//        listDummyEvent.add(Event(4, "Rapat",
//            "Dec 26", "19.00",
//            "Dec 21", "19.00",
//            "Rapat Terakhir"))
        rc_card.setHasFixedSize(true)



        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#ffffff")))
        getNotesData()

        eventAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, InputActivity::class.java)
            startActivityForResult(intent, InputActivity.REQUEST_ADD)
        }
    }

    private fun getNotesData(){
        val database = EventRoomDatabase.getDatabase(applicationContext)
        val dao = database.getEventDao()
        val listItems = arrayListOf<Event>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
        if (listItems.isNotEmpty()){
            tv_Kosong.visibility = View.GONE
        }
        else{
            tv_Kosong.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView(listItems: ArrayList<Event>){
        rc_card.layoutManager = LinearLayoutManager(this)
        val adapter = EventAdapter(listItems, this, this)
        adapter.notifyDataSetChanged()
        rc_card.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        getNotesData()
    }
}