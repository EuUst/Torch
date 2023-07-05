package com.example.torch

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var toggleBtn: ToggleButton
    private lateinit var torchStatusTV: TextView
    private lateinit var cameraManager: CameraManager
    private lateinit var cameraID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleBtn = findViewById(R.id.idBtnSwitch)
        torchStatusTV = findViewById(R.id.idTVTorchStatus)

        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            cameraID = cameraManager.cameraIdList[0]
        } catch (e: Exception) {
            e.printStackTrace()
        }

        toggleBtn.setOnClickListener {
            if(toggleBtn.isChecked) {
                try {
                    cameraManager.setTorchMode(cameraID,true)
                    Toast.makeText(this@MainActivity, "Turned on mtfcker!", Toast.LENGTH_LONG)
                        .show()
                    torchStatusTV.text = "Torch on"
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                try {
                    cameraManager.setTorchMode(cameraID, false)
                    Toast.makeText(
                        this@MainActivity,
                        "Torch turned of mthfckr (((",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    torchStatusTV.text = "Torch off"
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun finish() {
        super.finish()
        try {
            cameraManager.setTorchMode(cameraID, false)
            Toast.makeText(this@MainActivity, "Torch turned off (((", Toast.LENGTH_SHORT)
                .show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
