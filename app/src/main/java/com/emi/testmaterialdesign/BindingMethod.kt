package com.emi.testmaterialdesign

import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.media.MediaMetadataRetriever
import androidx.annotation.DrawableRes
import androidx.core.view.ViewCompat
import androidx.palette.graphics.Palette
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.activity_detail.view.placeNameHolder


object BindingMethod {

    @BindingAdapter("bind:srcCompat")
    @JvmStatic
    fun srcCompat(view : ImageView, @DrawableRes drawableId: Int){
        view.setImageResource(drawableId)
    }

}

