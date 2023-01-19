package com.rizkysiregar.skdrapp.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rizkysiregar.skdrapp.R
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.databinding.ListItemTambahDataBinding


class SkdrAdapter: RecyclerView.Adapter<SkdrAdapter.ListViewHolder>() {
    private var listData = ArrayList<Skdr>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Skdr>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_tambah_data, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ListItemTambahDataBinding.bind(itemView)
        lateinit var getSkdr: Skdr
        fun bind(data: Skdr) {
            getSkdr = data
            with(binding){
                tvListAddNamaDesa.text = data.namaDesa
                tvAddNamaPenyakit.text = data.namaPenyakit
                tvPeriodeMinggu.text = data.periodeMinggu.toString()
                tvJumlahPenderita.text = "${data.jumlahPenderita} Orang"
                tvKode.text = data.kodePenyakit
            }
        }
    }

}