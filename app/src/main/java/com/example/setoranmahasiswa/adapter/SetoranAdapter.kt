package com.example.setoranmahasiswa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.setoranmahasiswa.R
import com.example.setoranmahasiswa.model.Setoran

class SetoranAdapter(private val list: List<Setoran>) :
    RecyclerView.Adapter<SetoranAdapter.SetoranViewHolder>() {

    class SetoranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tanggal: TextView = itemView.findViewById(R.id.tvTanggal)
        val jumlah: TextView = itemView.findViewById(R.id.tvJumlah)
        val keterangan: TextView = itemView.findViewById(R.id.tvKeterangan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetoranViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_setoran, parent, false)
        return SetoranViewHolder(view)
    }

    override fun onBindViewHolder(holder: SetoranViewHolder, position: Int) {
        val item = list[position]
        holder.tanggal.text = "Tanggal: ${item.tanggal}"
        holder.jumlah.text = "Jumlah: ${item.jumlah}"
        holder.keterangan.text = "Keterangan: ${item.keterangan}"
    }

    override fun getItemCount(): Int = list.size
}