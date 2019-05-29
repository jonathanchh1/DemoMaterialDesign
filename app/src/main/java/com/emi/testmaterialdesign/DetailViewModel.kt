package com.emi.testmaterialdesign



import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.graphics.drawable.Animatable
import android.view.View
import android.view.ViewAnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_detail.view.*

class DetailViewModel constructor(private var context: Context, private var place : Place) : ViewModel(){


    fun name() = place.name
    fun icon() = place.icon
    private val MutableEditTextVisible = MutableLiveData<Boolean>(false)
    private var inputManager: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    private val MutableText = MutableLiveData<String>()
    private var visible : Boolean = false

    val todoText : LiveData<String>
    get() = MutableText
    val isEditTextVisible : LiveData<Boolean>
    get() =  MutableEditTextVisible

    fun onClick(v: View) {
        when (v.id) {
            R.id.textButton ->
                if (!visible) {
                    MutableEditTextVisible.postValue(true)
                    revealEditText(v.rootView.revealView)
                    v.rootView.todoText.requestFocus()
                    inputManager.showSoftInput(v.rootView.todoText, InputMethodManager.SHOW_IMPLICIT)
                    v.rootView.textButton.setImageResource(R.drawable.icn_morph)
                    val animatable = v.textButton.drawable as Animatable
                    animatable.start()
                } else {
                    MutableEditTextVisible.postValue(false)
                    MutableText.postValue(v.rootView.todoText.text.toString())
                    inputManager.hideSoftInputFromWindow(v.rootView.todoText.windowToken, 0)
                    hideEditText(v.rootView.revealView)
                    v.textButton.setImageResource(R.drawable.icn_morph_reverse)
                    val animatable = v.textButton.drawable as Animatable
                    animatable.start()
                }
        }
    }


    private fun revealEditText(view: LinearLayout) {
        val cx = view.right - 30
        val cy = view.bottom - 60
        val finalRadius = Math.max(view.width, view.height)
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius.toFloat())
        view.visibility = View.VISIBLE
        visible = true
        MutableEditTextVisible.postValue(true)
        anim.start()
    }

    private fun hideEditText(view: LinearLayout) {
        val cx = view.right - 30
        val cy = view.bottom - 60
        val initialRadius = view.width
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius.toFloat(), 0f)
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                view.visibility = View.INVISIBLE
            }
        })
        visible = false
        MutableEditTextVisible.postValue(false)
        anim.start()
    }


}

