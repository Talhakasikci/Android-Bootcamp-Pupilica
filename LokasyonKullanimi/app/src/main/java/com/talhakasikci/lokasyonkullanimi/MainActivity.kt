package com.talhakasikci.lokasyonkullanimi

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import com.talhakasikci.lokasyonkullanimi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMainBinding
    private var izinKontrol = 0
    private lateinit var flpc: FusedLocationProviderClient
    private lateinit var locationTask: Task<Location>
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            flpc = LocationServices.getFusedLocationProviderClient(this)
            
            // Map fragment'ı güvenli bir şekilde başlat
            val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as? SupportMapFragment
            mapFragment?.getMapAsync(this)

            binding.buttonKonum.setOnClickListener {
                izinKontrol = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                if(izinKontrol == PackageManager.PERMISSION_GRANTED){
                    locationTask = flpc.lastLocation
                    konumBilgisiAl()
                } else {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
                }
            }

            binding.buttonKonumaGit.setOnClickListener {
                if (::mMap.isInitialized) {
                    val konum = LatLng(41.0361566, 28.9854576)
                    mMap.addMarker(MarkerOptions()
                        .position(konum)
                        .title("Konum"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(konum, 17f))
                    mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                } else {
                    Toast.makeText(this, "Harita henüz hazır değil", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Bir hata oluştu: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        try {
            mMap = googleMap
            val konum = LatLng(-34.0, 151.0)
            mMap.addMarker(MarkerOptions()
                .position(konum)
                .title("Sydney"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(konum))
        } catch (e: Exception) {
            Toast.makeText(this, "Harita yüklenirken hata oluştu: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    fun konumBilgisiAl() {
        locationTask.addOnSuccessListener {
            if(it != null) {
                binding.textViewEnlem.text = "Enlem : ${it.latitude}"
                binding.textViewBoylam.text = "Boylam : ${it.longitude}"
            } else {
                binding.textViewEnlem.text = "Enlem : Bulunamadı"
                binding.textViewBoylam.text = "Boylam : Bulunamadı"
            }
        }.addOnFailureListener { e ->
            Toast.makeText(this, "Konum alınamadı: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 100) {
            izinKontrol = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationTask = flpc.lastLocation
                konumBilgisiAl()
                Toast.makeText(applicationContext, "İzin onaylandı", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "İzin onaylanmadı", Toast.LENGTH_SHORT).show()
            }
        }
    }
}