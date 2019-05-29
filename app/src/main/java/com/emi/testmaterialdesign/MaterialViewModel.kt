package com.emi.testmaterialdesign

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.row_places.view.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.android.synthetic.main.toolbar.view.*
import java.util.*

class MaterialViewModel constructor(var place : Place, val context: Context) : ViewModel(){




    fun name() = place.name
    fun icon() = place.icon



    fun onClick(view : View){
        val intent = Intent(view.context, DetailActivity::class.java)
        intent.putExtra("place", place)
        transition(intent, view)
    }


    private fun transition(intent: Intent, view : View){
         val placeImage = view.rootView.findViewById<ImageView>(R.id.placeImage)
         val placeNameHolder = view.findViewById<LinearLayout>(R.id.placeNameHolder)
         val navigationBar = view.rootView.findViewById<View>(android.R.id.navigationBarBackground)
         val statusBar = view.rootView.findViewById<View>(android.R.id.statusBarBackground)

         val imagePair = android.util.Pair.create(placeImage as View, "tImage")
         val holderPair = android.util.Pair.create(placeNameHolder as View, "tNameHolder")
         val navPair = android.util.Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME)
         val statusPair = android.util.Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME)
         val toolbarPair = android.util.Pair.create(view.rootView.toolbar as View, "tActionBar")
         val pairs = mutableListOf(imagePair, holderPair, statusPair, toolbarPair)
         if (navigationBar != null && navPair != null) {
             pairs += navPair
         }
         val options = ActivityOptions.makeSceneTransitionAnimation(view.context as Activity, imagePair, holderPair)
         ActivityOptions.makeSceneTransitionAnimation(view.context as Activity?, *pairs.toTypedArray())
         ActivityCompat.startActivity(view.context as Activity, intent, options.toBundle())
     }

    }