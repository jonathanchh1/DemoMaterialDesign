package com.emi.testmaterialdesign

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.emi.testmaterialdesign.databinding.RowPlacesBinding

class TravelListAdapter(private var placeList : List<Place>): RecyclerView.Adapter<TravelListAdapter.TravelBindingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelBindingViewHolder {
        val binding : RowPlacesBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.row_places, parent, false)
        return TravelBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TravelBindingViewHolder, position: Int) {
        return holder.bind(placeList.get(position))
    }


    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


    override fun getItemCount(): Int {
        return placeList.size
    }


    inner class TravelBindingViewHolder(val binding : RowPlacesBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(place : Place){
            if(binding.viewModel == null){
                binding.viewModel = MaterialViewModel(place, itemView.context)
            }else{
                binding.viewModel!!.place = place
            }
        }
    }

}
