package com.example.happyplaces.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import java.io.IOException
import java.util.*

class GetAddressFromLatLng(context: Context, private val lat: Double, private val long: Double) {
    private val geocoder: Geocoder = Geocoder(context, Locale.getDefault())
//    private lateinit var mAddressListener: AddressListener

    suspend fun getAddress():String{
        try{
            val addressList: List<Address>? = geocoder.getFromLocation(lat, long, 1)

            if(addressList != null && addressList.isNotEmpty()){
                val address: Address = addressList[0]
                val sb = StringBuilder()
                for(i in 0..address.maxAddressLineIndex){
                    sb.append(address.getAddressLine(i)).append(", ")
                }
                sb.deleteCharAt(sb.length - 1)
                return sb.toString()
            }
        }catch(e: IOException){
            Log.e("GetAddressFromLatLng", "Unable to connect to Geocoder")
        }

        return ""
    }

//    fun getAddress(){
//        execute()
//    }
//
//    fun setAddressListener(addressListener: AddressListener){
//        mAddressListener = addressListener
//    }
//
//    interface AddressListener{
//        fun onAddressFound(address: String?)
//        fun onError()
//    }

}