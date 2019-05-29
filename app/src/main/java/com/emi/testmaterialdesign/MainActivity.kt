package com.emi.testmaterialdesign

import android.app.Activity
import android.app.ActivityOptions.makeSceneTransitionAnimation
import android.content.Context
import android.content.Intent
import com.emi.testmaterialdesign.databinding.ActivityMainBinding

import android.os.Bundle
import android.util.Pair.create
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private  lateinit var menu: Menu
    private  lateinit var staggeredLayoutManager: StaggeredGridLayoutManager
    private  lateinit var adapter: TravelListAdapter
    private var isListView: Boolean = false
    private lateinit var factory : ViewModelFactory
    private lateinit var viewModel: MaterialViewModel

    private val dataMap = mapOf("Bora Bora" to R.drawable.borabora, "Canada" to R.drawable.canada, "Dubai" to R.drawable.dubai, "Hong Kong" to R.drawable.hongkong, "Iceland"
    to R.drawable.iceland, "India" to R.drawable.iceland, "Kenya" to R.drawable.kenya, "London" to R.drawable.london, "Switzerland" to R.drawable.switzerland,
        "Sydney" to R.drawable.sydney)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dataSource = ArrayList<Place>()
        for((k, v) in dataMap){
            val profileData = Place(k, v)
            factory = ViewModelFactory(profileData, this)
            dataSource.add(profileData)

        }
        viewModel = ViewModelProviders.of(this, factory).get(MaterialViewModel::class.java)
        binding.viewModel = viewModel
        isListView = true
        staggeredLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        adapter = TravelListAdapter(dataSource)
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = staggeredLayoutManager
        setUpActionBar()


    }






    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if(id == R.id.action_toggle){
            toggle()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toggle() {
        if (isListView) {
            showGridView()
        } else {
            showListView()
        }
    }

    private fun setUpActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.elevation = 7.0f
    }

    private fun showListView() {
        staggeredLayoutManager.spanCount = 1
        val item = menu.findItem(R.id.action_toggle)
        item.setIcon(R.drawable.ic_action_grid)
        item.title = getString(R.string.show_as_grid)
        isListView = true
    }

    private fun showGridView() {
        staggeredLayoutManager.spanCount = 2
        val item = menu.findItem(R.id.action_toggle)
        item.setIcon(R.drawable.ic_action_list)
        item.title = getString(R.string.show_as_list)
        isListView = false

    }




}
