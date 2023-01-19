package com.rizkysiregar.skdrapp.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.rizkysiregar.skdrapp.R
import com.rizkysiregar.skdrapp.core.di.viewModelModule
import com.rizkysiregar.skdrapp.core.domain.model.DataPenyakit
import com.rizkysiregar.skdrapp.databinding.ListDataBinding
import com.rizkysiregar.skdrapp.setting.SettingViewModel

class DataPenyakitAdapter(): RecyclerView.Adapter<DataPenyakitAdapter.ListViewHolder>() {

    private val listNewData = ArrayList<DataPenyakit>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<DataPenyakit>?){
        if (newData == null) return
        listNewData.clear()
        listNewData.addAll(newData)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_data, parent, false))

    override fun getItemCount() = listNewData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listNewData[position]
        holder.bind(data)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListDataBinding.bind(itemView)
        fun bind(data: DataPenyakit){
            with(binding){
                kode.text = data.kodePenyakit
                penyakit.text = data.namaPenyakit

            }
        }
    }

}