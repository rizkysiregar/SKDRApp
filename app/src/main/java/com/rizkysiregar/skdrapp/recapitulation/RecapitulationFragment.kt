package com.rizkysiregar.skdrapp.recapitulation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizkysiregar.skdrapp.R
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
            var periodic = binding.spMingguRecap.selectedItem.toString().toInt()
            recapitulationViewModel.setSkdrPeriodic(periodic)
        }
        val sbWA = StringBuilder()
        val sbSMS = StringBuilder()

        recapitulationViewModel.skdr.observe(requireActivity()) {
            skdrAdapter.setData(it)
            sbWA.clear()
            sbSMS.clear()
            sbWA.append("SKDR ${binding.spMingguRecap.selectedItem}#2023")
            sbSMS.append("MANUAL#${binding.spMingguRecap.selectedItem}")
            for (skdr in it){
                sbWA.append("#${skdr.kodePenyakit}${skdr.jumlahPenderita}")
                sbSMS.append("#${skdr.kodePenyakit}${skdr.jumlahPenderita}")
            }
            binding.tvFormatLaporan.text = sbWA
        }
        binding.btnWa.setOnClickListener {
            if (binding.edtTotalKunjungan.text.isEmpty()){
                Toast.makeText(requireActivity(),"Ups..Total Kunjungan masih kosong",Toast.LENGTH_SHORT).show()
            }else{
                var number = binding.spNumberPhone.selectedItem.toString()
                sbWA.append("#X${binding.edtTotalKunjungan.text}")
                sendWA(sbWA.toString(),number)
            }
        }
        binding.btnSms.setOnClickListener {
            if (binding.edtTotalKunjungan.text.isEmpty()){
                Toast.makeText(requireActivity(),"Ups..Total Kunjungan masih kosong",Toast.LENGTH_SHORT).show()
            }else{
                var number = binding.spNumberPhone.selectedItem.toString()
                sbSMS.append("#X${binding.edtTotalKunjungan.text}")
//                var number = binding.spNumberPhone.selectedItem.toString()
                sendSms(sbSMS.toString(),number)
            }
        }
    }

    private fun sendWA(sb: String, number: String){

        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                    "https://api.whatsapp.com/send?phone=+$number&text="+URLEncoder.encode(sb, "UTF-8")
                )
            )
        )
//        try {
//            val sendIntent = Intent().apply {
//                action = Intent.ACTION_SEND
//                putExtra(Intent.EXTRA_TEXT, sb)
//                putExtra("jid", "${number}@s.whatsapp.net")
//                type = "text/plain"
//                setPackage("com.whatsapp")
//            }
//            startActivity(sendIntent)
//        }catch (e: Exception){
//            e.printStackTrace()
//            val appPackageName = "com.whatsapp"
//            try {
//                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
//            }catch (e: android.content.ActivityNotFoundException){
//                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
//            }
//        }
    }

    private fun sendSms(message: String, phone: String){
        val uri = Uri.parse("smsto:+$phone")
        val intent = Intent(Intent.ACTION_SENDTO, uri)

        with(intent){
            putExtra("address", "+$phone")
            putExtra("sms_body", message)
        }
        startActivity(intent)
    }


}