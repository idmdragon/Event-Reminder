package com.example.eventreminder

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_name.*
import kotlinx.android.synthetic.main.item_name.view.*

class EventAdapter(var listEvent : List<Event>, val activity: Activity):RecyclerView.Adapter<EventAdapter.EventViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_name, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(listEvent[position])
    }

    override fun getItemCount()= listEvent.size

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvEventName : TextView = itemView.findViewById(R.id.tvEventName)
        val tvKeterangan : TextView = itemView.findViewById(R.id.tvKeterangan)
        val tvTanggal : TextView = itemView.findViewById(R.id.tvTanggal)
        val tvWaktu : TextView = itemView.findViewById(R.id.tvWaktu)
        fun bind(event: Event) {
            with(itemView){
                tvEventName.text = event.eventName
                tvKeterangan.text = event.keterangan
                tvTanggal.text =  event.eventDate
                tvWaktu.text = event.eventTime
                linearRootAtas.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(view: View, position: Int) {
                        val intent = Intent(activity, InputActivity::class.java)
                        intent.putExtra(InputActivity.EXTRA_POSITION, position)
                        intent.putExtra(InputActivity.EXTRA_NOTE, event)
                        activity.startActivityForResult(intent, InputActivity.REQUEST_UPDATE)
                    }
                }))
            }
        }
    }
}

