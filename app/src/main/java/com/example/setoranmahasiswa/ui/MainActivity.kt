package com.example.setoranmahasiswa.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setoranmahasiswa.R
import com.example.setoranmahasiswa.adapter.SetoranAdapter
import com.example.setoranmahasiswa.data.RetrofitClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SetoranAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.instance.getSetoran()
                }
                adapter = SetoranAdapter(response)
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                e.printStackTrace()
                // Tambahkan log atau tampilkan toast jika diperlukan
            }
        }
    }
}