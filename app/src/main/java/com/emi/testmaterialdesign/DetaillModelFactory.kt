package com.emi.testmaterialdesign

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DetaillModelFactory constructor(private var context : Context, private var place : Place) : ViewModelProvider.Factory{


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java))
            return DetailViewModel(context, place) as T

        throw  IllegalArgumentException("its not detailmodelclass")
    }
}