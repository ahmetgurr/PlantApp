package com.example.plantapp.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantapp.R
import com.example.plantapp.adapter.PlantRecyclerAdapter
import com.example.plantapp.databinding.FragmentDashboardBinding
import com.example.plantapp.databinding.FragmentNotificationsBinding
import com.example.plantapp.model.Plant
import com.google.firebase.firestore.FirebaseFirestore

class DashboardFragment : Fragment() {

    private lateinit var db: FirebaseFirestore
    private lateinit var plantList: MutableList<Plant>
    private lateinit var recyclerView: RecyclerView
    private lateinit var plantRecyclerAdapter: PlantRecyclerAdapter
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Firebase Firestore referansını başlat
        db = FirebaseFirestore.getInstance()

        // Bitki listesini tutacak boş bir liste oluştur
        plantList = mutableListOf()

        // RecyclerView ve adapter'ı başlat
        recyclerView = view.findViewById(R.id.recyclerView)
        plantRecyclerAdapter = PlantRecyclerAdapter(requireContext(), plantList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = plantRecyclerAdapter

        // Firestore'dan bitki listesini al ve RecyclerView'a ayarla
        loadPlantListFromFirestore()
    }

    private fun loadPlantListFromFirestore() {
        // Firestore koleksiyon referansını belirle (örneğin, "Plants" koleksiyonu)
        val plantsCollection = db.collection("Plants")

        // Firestore'dan tüm belgeleri al
        plantsCollection.get()
            .addOnSuccessListener { result ->
                // Belge varsa işlemleri yap
                for (document in result) {
                    val plant = document.toObject(Plant::class.java)
                    plantList.add(plant)
                }

                // RecyclerView'a güncellenmiş listeyi ayarla
                plantRecyclerAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                // Belge alırken bir hata olursa buraya gelir
                Log.w("Firestore", "Error getting documents.", exception)
            }
    }
}