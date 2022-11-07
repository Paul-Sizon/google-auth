package com.sizon.google_auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.sizon.google_auth.ui.theme.GoogleauthTheme

class MapsActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleauthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MapsScreen()
                }
            }
        }
    }

    val singapore = LatLng(1.35, 103.87)

    @Composable
    fun MapsScreen(){

        var mapProperties by remember {
            mutableStateOf(
                MapProperties(minZoomPreference = 5f)
            )
        }
        var mapUiSettings by remember {
            mutableStateOf(
                MapUiSettings(mapToolbarEnabled = false)
            )
        }
        Box(Modifier.fillMaxSize()) {
            GoogleMap(properties = mapProperties, uiSettings = mapUiSettings)
            Column {
                Button(onClick = {
                    mapProperties = mapProperties.copy(
                        isBuildingEnabled = !mapProperties.isBuildingEnabled,
                        isMyLocationEnabled = !mapProperties.isMyLocationEnabled,
                        isTrafficEnabled = !mapProperties.isTrafficEnabled
                    )
                }) {
                    Text(text = "Toggle mapProperties")
                }
                Button(onClick = {
                    mapUiSettings = mapUiSettings.copy(
                        mapToolbarEnabled = !mapUiSettings.mapToolbarEnabled,
                        myLocationButtonEnabled = !mapUiSettings.myLocationButtonEnabled
                    )
                }) {
                    Text(text = "Toggle mapUiSettings")
                }
            }
        }
    }

    @Composable
    fun CityMap() {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 10f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = singapore),
                title = "Singapore",
                snippet = "Marker in Singapore"
            )
        }
    }

    @Preview
    @Composable
    fun PreviewMaps(){
        MapsScreen()
    }
}