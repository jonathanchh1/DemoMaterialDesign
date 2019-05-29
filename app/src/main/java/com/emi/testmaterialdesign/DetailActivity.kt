package com.emi.testmaterialdesign

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Animatable
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.palette.graphics.Palette
import androidx.transition.Slide
import com.emi.testmaterialdesign.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.placeNameHolder
import kotlinx.android.synthetic.main.row_places.*
import java.util.ArrayList

class DetailActivity : AppCompatActivity() {

    private lateinit var todoList: ArrayList<String>
    private lateinit var toDoAdapter: ArrayAdapter<*>

    private var isEditTextVisible: Boolean = false
    private var defaultColor: Int = 0
    private lateinit var places : Place
    lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        places = intent.getParcelableExtra("place") as Place
        val factory = DetaillModelFactory(this, places)
        detailViewModel = ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
        binding.detailViewModel = detailViewModel
        binding.lifecycleOwner = this
        getPhoto()
        windowTransition()
        setupValues()
        setUpAdapter()
    }


    private fun setupValues() {
        defaultColor = ContextCompat.getColor(this, R.color.primary_dark)
        revealView.visibility = View.INVISIBLE
        detailViewModel.isEditTextVisible.observe(this, Observer {
            it?.let {
            isEditTextVisible = it
        }
           })
    }

    private fun setUpAdapter() {
        todoList = ArrayList()
        detailViewModel.todoText.observe(this, Observer {
            text ->
            text?.let {
                todoList.add(text)
                toDoAdapter.notifyDataSetChanged()
            }
        })
        toDoAdapter = ArrayAdapter(this, R.layout.row_todo, todoList)
        activitiesList.adapter = toDoAdapter
    }



    private fun windowTransition() {
        window.enterTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition) {
                textButton.animate().alpha(1.0f)
                window.enterTransition.removeListener(this)
            }
            override fun onTransitionResume(transition: Transition) { }
            override fun onTransitionPause(transition: Transition) { }
            override fun onTransitionCancel(transition: Transition) { }
            override fun onTransitionStart(transition: Transition) {
            }
        })
    }

    private fun getPhoto() {
        val photo = BitmapFactory.decodeResource(resources, places.icon)
        colorize(photo)
    }


    private fun colorize(photo: Bitmap) {
        val palette = Palette.from(photo).generate()
        applyPalette(palette)
    }

    private fun applyPalette(palette: Palette) {
        window.setBackgroundDrawable(ColorDrawable(palette.getDarkMutedColor(defaultColor)))
        placeNameHolder.setBackgroundColor(palette.getMutedColor(defaultColor))
        revealView.setBackgroundColor(palette.getLightVibrantColor(defaultColor))
    }


    override fun onBackPressed() {
        val alphaAnimation = AlphaAnimation(1.0f, 0.0f)
        alphaAnimation.duration = 100
        textButton.startAnimation(alphaAnimation)
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
            }

            override fun onAnimationEnd(animation: Animation) {
                textButton.visibility = View.GONE
                finishAfterTransition()
            }

            override fun onAnimationRepeat(animation: Animation) {
            }
        })
    }


}
