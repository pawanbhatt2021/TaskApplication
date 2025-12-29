package com.example.taskapplication.data.local

import android.content.Context
import com.example.taskapplication.R
import com.example.taskapplication.presentation.home.model.AcServices
import com.example.taskapplication.presentation.home.model.TopServices

// because for now data temp local
object DataProvider {

    fun getTopServicesDataList(context: Context): ArrayList<TopServices>{
        val list = arrayListOf<TopServices>()
        with(list) {
            add(TopServices(R.drawable.top_service_img1,context.getString(R.string.ac_services)))
            add(TopServices(R.drawable.top_service_img2,context.getString(R.string.electrical_services)))
            add(TopServices(R.drawable.top_service_img3,context.getString(R.string.plumbing_services)))
        }
        return list
    }

    fun getAcServicesDataList(context: Context): ArrayList<AcServices>{
        val list = arrayListOf<AcServices>()
        with(list) {
            add(AcServices(R.drawable.ac_service_img1,context.getString(R.string.ac_services_),"4.0","100"))
            add(AcServices(R.drawable.ac_service_img2,context.getString(R.string.ac_water_leak_fix),"4.2","120"))
            add(AcServices(R.drawable.ac_service_img3,context.getString(R.string.duct_and_filter_cleaning),"3.8","40"))
            add(AcServices(R.drawable.ac_service_img4,context.getString(R.string.ac_repair_and_installation),"4.8","400"))
        }
        return list
    }
}