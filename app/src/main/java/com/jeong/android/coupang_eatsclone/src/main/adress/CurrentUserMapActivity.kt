package com.jeong.android.coupang_eatsclone.src.main.adress

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.jeong.android.coupang_eatsclone.R
//import com.google.android.gms.location.R
import com.jeong.android.coupang_eatsclone.config.BaseActivity
import com.jeong.android.coupang_eatsclone.databinding.ActivityCurrentUserMapBinding
import com.jeong.android.coupang_eatsclone.src.main.adress.kakaomodels.KaKaoData
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker

class CurrentUserMapActivity : BaseActivity<ActivityCurrentUserMapBinding>(ActivityCurrentUserMapBinding::inflate) ,
    CurrentUserMapInterface,
    OnMapReadyCallback  {

    var permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION)
    val permission_request = 99
    val geocoder = Geocoder(this)

    private lateinit var naverMap: NaverMap
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isPermitted()) {
            startProcess()
        } else {
            ActivityCompat.requestPermissions(this, permissions, permission_request)
        }

        binding.btnSetting.setOnClickListener {
            val intent = Intent(this, MapDetailActivity::class.java)
            intent.putExtra("Adress",binding.tvAdress.text.toString())
            intent.putExtra("RoadAdress",binding.tvRoadAdress.text.toString())
            startActivity(intent)
        }

    }
    override fun onMapReady(naverMap: NaverMap) {
        val cameraPosition = CameraPosition(
            LatLng(37.56662, 126.987493),
            16.0
        )
        naverMap.cameraPosition = cameraPosition
        this.naverMap = naverMap

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this) //gps 자동으로 받아오기
        setUpdateLocationListner() //내위치를 가져오는 코드
    }

    fun isPermitted(): Boolean {
        for (perm in permissions) {
            // perm의 대한 권한을 가지고 있으면 PERMISSION_GRANTED를 리턴하고 가지고 있지 않다면 PERMISSION_DENIED를 리턴
            if(ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    fun startProcess() {
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    fun setUpdateLocationListner() {
        val locationRequest = LocationRequest.create()
        locationRequest.run {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for ((i, location) in locationResult.locations.withIndex()) {
                    setLastLocation(location)
                }
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()
        )
    }

    fun setLastLocation(location: Location) {
        val myLocation = LatLng(location.latitude, location.longitude)
        val marker = Marker()
        CurrentUserMapService(this).tryGetAdress(location.longitude.toString(), location.latitude.toString())
        marker.position = myLocation

        marker.map = naverMap
        //마커
        val cameraUpdate = CameraUpdate.scrollTo(myLocation)
        naverMap.moveCamera(cameraUpdate)
        naverMap.maxZoom = 18.0
        naverMap.minZoom = 5.0
        //marker.map = null

        Log.e("TAG", "\"${location.latitude}////${location.longitude}\"", )
    }

    override fun onGetAddresSuccess(response: KaKaoData) {
        binding.tvAdress.text = response.documents[0].road_address.building_name
        binding.tvRoadAdress.text = response.documents[0].road_address.address_name
        showCustomToast(response.documents[0].road_address.address_name)
        showCustomToast(response.documents[0].road_address.building_name)
    }

    override fun onGetAdressFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}