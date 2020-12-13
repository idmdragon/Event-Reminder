package com.example.eventreminder

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_name.view.*

class EventAdapter(var listEvent : MutableList<Event>, val activity: Activity):RecyclerView.Adapter<EventAdapter.EventViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_name, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(listEvent[position])

    }

    override fun getItemCount()= listEvent.size

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(event: Event) {
            with(itemView) {
                tvEventName.text = event.eventName
                tvKeterangan.text = event.keterangan
                tvTanggal.text = event.eventDate
                tvWaktu.text = event.eventTime


                if (event.id!! % 3 == 0) {
                    linearRootAtas.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.list_color1
                        )
                    )
                } else if (event.id!! % 3 == 2){
                    linearRootAtas.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.list_color2
                        )
                    )
            }else{
                linearRootAtas.setBackgroundColor(
                    ContextCompat.getColor(
                        context,R.color.list_color3)
                )
            }

                btn_edit.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(view: View, position: Int) {
                        val intent = Intent(activity, InputActivity::class.java)
                        intent.putExtra(InputActivity.EXTRA_POSITION, position)
                        intent.putExtra(InputActivity.EXTRA_NOTE, event)
                        activity.startActivityForResult(intent, InputActivity.REQUEST_UPDATE)
                    }
                }))

                btn_delete.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(view: View, position: Int) {
                        showAlertDialog(position)
                    }
                }))

                toggleSubpractices.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback{

                    override fun onItemClicked(view: View, position: Int) {

                        if(isiLinearLayout.visibility == View.VISIBLE){
                            isiLinearLayout.visibility = View.GONE
                            toggleSubpractices.setImageResource(R.drawable.arrow_right)

                        }else{
                            isiLinearLayout.visibility = View.VISIBLE
                            toggleSubpractices.setImageResource(R.drawable.arrow_down)
                        }

                    }
                }))
            }
            fun changeIcon(imageView: ImageView, int: Int) {
                imageView.setImageResource(int)
            }
        }
    }
    private fun removeItem(position: Int){
        listEvent.removeAt(position)
        notifyDataSetChanged()
    }
    private fun showAlertDialog(pos: Int) {
        val dialogTitle: String
        val dialogMessage: String

        dialogMessage = "Apakah anda yakin ingin menghapus event ini?"
        dialogTitle = "Hapus Event"

        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(dialogTitle)
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya") { dialog, id ->
                    val result = removeItem(pos)
                    Toast.makeText(activity, "Berhasil menghapus data", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Tidak") { dialog, id -> dialog.cancel() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}

