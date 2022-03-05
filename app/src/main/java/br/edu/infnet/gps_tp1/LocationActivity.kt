package br.edu.infnet.gps_tp1

import android.R.attr.path
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.gps_tp1.viewModel.LocationModel
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        layoutManager = LinearLayoutManager(this)

        val downloadFolder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val files = downloadFolder?.listFiles()
        val filesNames = arrayListOf<LocationModel>()
        if (files != null) {
            for (file in files) {
                filesNames.add(LocationModel(file.name.toString()))
            }
        }

        recyclerView.layoutManager = layoutManager
        adapter = RecyclerViewAdapter(filesNames, this)
        recyclerView.adapter = adapter


    }
}