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

class PlantRecyclerAdapter(private val context: Context, private val plantList: List<Plant>) :
    RecyclerView.Adapter<PlantRecyclerAdapter.PlantViewHolder>() {

    // Tıklama dinleyicisi
    private var onItemClickListener: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.plant_list_item, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val currentPlant = plantList[position]

        holder.textViewPlantName.text = currentPlant.plantName
        // Glide kullanarak resmi yükle
        Glide.with(context)
            .load(currentPlant.imageUrl) // Plant sınıfınıza göre bu alan değişebilir
            .into(holder.imageViewPlant)

        // Tıklama dinleyicisini çağır
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewPlantName: TextView = itemView.findViewById(R.id.textViewPlantName)
        val imageViewPlant: ImageView = itemView.findViewById(R.id.imageViewPlant)

    }
}
