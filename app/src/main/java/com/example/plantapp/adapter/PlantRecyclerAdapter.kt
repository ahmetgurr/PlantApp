package com.example.plantapp.adapter

import android.content.Context
import com.example.plantapp.model.Plant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantapp.R
/*
class PlantRecyclerAdapter(private val context: Context, private val plantList: List<Plant>) :
    RecyclerView.Adapter<PlantRecyclerAdapter.PlantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.plant_list_item, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val currentPlant = plantList[position]

        holder.textViewPlantName.text = currentPlant.plantName
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewPlantName: TextView = itemView.findViewById(R.id.textViewPlantName)
        val imageViewPlant: ImageView = itemView.findViewById(R.id.imageViewPlant)
    }
}

 */

// PlantRecyclerAdapter
class PlantRecyclerAdapter(private val context: Context, private val plantList: List<Plant>) :
    RecyclerView.Adapter<PlantRecyclerAdapter.PlantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.plant_list_item, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val currentPlant = plantList[position]

        holder.textViewPlantName.text = currentPlant.plantName

        // Resmi yüklemek için kendi yönteminizi kullanabilirsiniz
        // Burada örnek olarak Glide veya benzeri kütüphaneleri kullanabilirsiniz
        // Örneğin:
        // Glide.with(context).load(currentPlant.imageUrl).into(holder.imageViewPlant)
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewPlantName: TextView = itemView.findViewById(R.id.textViewPlantName)
        val imageViewPlant: ImageView = itemView.findViewById(R.id.imageViewPlant)
    }
}
