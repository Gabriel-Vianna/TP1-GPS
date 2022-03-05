package br.edu.infnet.gps_tp1

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.time.LocalDateTime

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import br.edu.infnet.gps_tp1.databinding.ActivityMapsBinding
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    val CREATE_FILE = 0

    @SuppressLint("WrongConstant", "ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        findViewById<Button>(R.id.salvar).setOnClickListener {
            val currentDate = LocalDateTime.now()
            val file = File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "${currentDate.toString()}.crd")
            val fos = FileOutputStream(file)
            val txtTexto = "Hello World!"
            fos.write(txtTexto.toByteArray())
            fos.close()
            Toast.makeText(applicationContext, "Localização salva!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var extras = intent.extras
        var lat = -22.9035
        var long = -43.2096

        if(extras != null) {
            lat = extras.getDouble("lat")
            long = extras.getDouble("long")
        }

        val location = LatLng(lat, long)
        mMap.addMarker(MarkerOptions().position(location).title("Localização atual"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 12.0f ) );
    }
}