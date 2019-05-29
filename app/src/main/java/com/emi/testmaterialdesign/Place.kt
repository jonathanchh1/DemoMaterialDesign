package com.emi.testmaterialdesign

import android.content.Context
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place(val name: String, val icon: Int) : Parcelable {

   /*
    fun getImageResourceId(context: Context): Int {
        return context.resources.getIdentifier(this.imageName, "drawable", context.packageName)
    }

    */
}