package com.emi.testmaterialdesign

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewModelFactory constructor(private var place : Place, private var context: Context) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MaterialViewModel::class.java))
            return MaterialViewModel(place, context) as T

        throw IllegalArgumentException("its not viewmodel class")
    }
}