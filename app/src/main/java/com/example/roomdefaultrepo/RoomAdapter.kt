package com.example.roomdefaultrepo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RoomAdapter : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {
    var item = mutableListOf<Memo>()
    class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(memo: Memo){
            val number = itemView.findViewById<TextView>(R.id.number)
            val text = itemView.findViewById<TextView>(R.id.text)
            val subText = itemView.findViewById<TextView>(R.id.subText)
            number.text = memo.id.toString()
            text.text = memo.mainText
            subText.text = memo.subText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(item[position])
    }

    override fun getItemCount(): Int = item.size
}