package com.example.setoranmahasiswa.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.setoranmahasiswa.R
import com.example.setoranmahasiswa.model.Setoran
import com.example.setoranmahasiswa.data.RetrofitClient
import kotlinx.coroutines.launch

class TambahSetoranActivity : AppCompatActivity() {
    private lateinit var tanggalInput: EditText
    private lateinit var jumlahInput: EditText
    private lateinit var keteranganInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_setoran)

        tanggalInput = findViewById(R.id.tanggalInput)
        jumlahInput = findViewById(R.id.jumlahInput)
        keteranganInput = findViewById(R.id.keteranganInput)

        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val setoran = Setoran(
                nim = "YOUR_NIM", // Ambil dari token/SSO
                tanggal = tanggalInput.text.toString(),
                jumlah = jumlahInput.text.toString().toInt(),
                keterangan = keteranganInput.text.toString()
            )

            lifecycleScope.launch {
                val response = RetrofitClient.instance.tambahSetoran(setoran)
                if (response.isSuccessful) {
                    finish()
                }
            }
        }
    }
}