package com.rizkysiregar.skdrapp.recapitulation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizkysiregar.skdrapp.R
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.ui.SkdrAdapter
import com.rizkysiregar.skdrapp.databinding.FragmentRecapitulationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URLEncoder


class RecapitulationFragment : Fragment() {

    // binding delegate
    private var _binding: FragmentRecapitulationBinding? = null
    private val binding get() = _binding!!
    private val recapitulationViewModel: RecapitulationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecapitulationBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set spinner minggu
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.minngu_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spMingguRecap.adapter = it
        }

        // set spinner no_hp
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.nomor_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spNumberPhone.adapter = it
        }

        // spinner tahun
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.year_array,
            android.R.layout.simple_spinner_dropdown_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spTahun.adapter = it
        }

        // Adapter RecyclerView
        val skdrAdapter = SkdrAdapter()
        with(binding.recyclerView){
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = skdrAdapter
        }

        // get data by periodic (weekly)
        recapitulationViewModel.setSkdrPeriodic(1)
        binding.btnCari.setOnClickListener {
            val periodic = binding.spMingguRecap.selectedItem.toString().toInt()
            recapitulationViewModel.setSkdrPeriodic(periodic)
        }

        val sbWA = StringBuilder()
        val sbSMS = StringBuilder()

        recapitulationViewModel.skdr.observe(requireActivity()) {
            skdrAdapter.setData(it)

            if (it.isNullOrEmpty()){
                binding.tvDataEmpty.visibility = View.VISIBLE
            }else{
                binding.tvDataEmpty.visibility = View.GONE
            }

            sumSameData(it).also { result ->
                sbWA.clear()
                sbSMS.clear()
                sbWA.append("SKDR ${binding.spMingguRecap.selectedItem}#${binding.spTahun.selectedItem}")
                sbSMS.append("MANUAL#${binding.spMingguRecap.selectedItem}")
                for (skdr in result){
                    sbWA.append("#${skdr.kodePenyakit}${skdr.jumlahPenderita}")
                    sbSMS.append("#${skdr.kodePenyakit}${skdr.jumlahPenderita}")
                }
            }
            binding.tvFormatLaporan.text = sbWA
        }
        binding.btnWa.setOnClickListener {
            if (binding.edtTotalKunjungan.text.isEmpty()){
                Toast.makeText(requireActivity(),"Ups..Total Kunjungan masih kosong",Toast.LENGTH_SHORT).show()
            }else{
                val number = binding.spNumberPhone.selectedItem.toString()
                sbWA.append("#X${binding.edtTotalKunjungan.text}")
                sendWA(sbWA.toString(),number)
            }
        }
        binding.btnSms.setOnClickListener {
            if (binding.edtTotalKunjungan.text.isEmpty()){
                Toast.makeText(requireActivity(),"Ups..Total Kunjungan masih kosong",Toast.LENGTH_SHORT).show()
            }else{
                val number = binding.spNumberPhone.selectedItem.toString()
                sbSMS.append("#X${binding.edtTotalKunjungan.text}")
                sendSms(sbSMS.toString(),number)
            }
        }
    }

    private fun sendWA(sb: String, number: String){
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(
                        "https://api.whatsapp.com/send?phone=+$number&text="+URLEncoder.encode(sb, "UTF-8")
                    )
                )
            )
        }catch(e: Exception){
            Toast.makeText(requireContext(), "Aplikasi WhatsApp Belum Terinstall", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendSms(message: String, phone: String){
        val uri = Uri.parse("smsto:+$phone")
        val intent = Intent(Intent.ACTION_SENDTO, uri)

        with(intent){
            putExtra("address", "+$phone")
            putExtra("sms_body", message)
        }
        try {
            startActivity(intent)
        }catch(e: Exception){
            Toast.makeText(requireContext(), "Aplikasi SMS Belum Terinstal",Toast.LENGTH_SHORT).show()
        }
    }

    private fun sumSameData(data : List<Skdr>): List<Skdr> {
        val sum = (data)
            .groupBy { it.kodePenyakit }
            .values
            .map {
                it.reduce{
                    acc, item -> Skdr(0,item.namaDesa,item.periodeMinggu,item.namaPenyakit,item.kodePenyakit,acc.jumlahPenderita + item.jumlahPenderita)
                }
            }
        return sum
    }
}