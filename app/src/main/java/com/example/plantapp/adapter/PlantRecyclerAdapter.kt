package com.example.plantapp.adapter

import android.content.Context
import com.example.plantapp.model.Plant
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantapp.R
import com.example.plantapp.databinding.PlantListItemBinding
import com.example.plantapp.ui.dashboard.DashboardFragment

class PlantRecyclerAdapter(
    private val context: Context,
    private val plantList: List<Plant>,
    var listener: DashboardFragment
) : RecyclerView.Adapter<PlantRecyclerAdapter.PlantViewHolder>() {

    class PlantViewHolder(val binding: PlantListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    // Tıklama dinleyicisi
    private var onItemClickListener: ((Int) -> Unit)? = null
    private var onDeleteClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }
    fun setOnDeleteClickListener(listener: (Int) -> Unit) {
        onDeleteClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = PlantListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlantViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return plantList.size
    }
    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val currentPlant = plantList[position]

        // Tıklama dinleyicisini çağır
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(position)
        }
        //glide ile resimleri yükle
        holder.binding.textViewPlantName.text = currentPlant.plantName
        Glide.with(context)
            .load(currentPlant.imageUrl)
            .into(holder.binding.imageViewPlant)

        // ImageDelete simgesine tıklanma işlemi
        holder.binding.btnDeletePlant.setOnClickListener {
            //buraya silme işlemi gelecek
        }
        // Silme dinleyicisini çağır
        holder.binding.btnDeletePlant.setOnClickListener {
            onDeleteClickListener?.invoke(position)
        }
    }

}
