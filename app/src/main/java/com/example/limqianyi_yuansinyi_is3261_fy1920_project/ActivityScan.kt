package com.example.limqianyi_yuansinyi_is3261_fy1920_project

import android.Manifest
import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import java.io.IOException

class ActivityScan : AppCompatActivity() {

    var allPermissionsGrantedFlag : Int = 0

    private val permissionList = arrayOf(
        Manifest.permission.CAMERA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        val myIntent = Intent(this, ActivityScanPayment::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (allPermissionEnabled()){
                allPermissionsGrantedFlag = 1
            } else {
                setupMultiplePermissions()
            }
        } else {
            allPermissionsGrantedFlag = 1
        }


        val cameraView = findViewById<SurfaceView>(R.id.scanCamera)

        val barcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.QR_CODE)
            .build()

        val cameraSource = CameraSource.Builder(this, barcodeDetector)
            .setRequestedPreviewSize(640, 480)
            .build()

        cameraView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder?) {
                try {
                    cameraSource.start(cameraView.holder)
                } catch (ie: IOException) {
                    Log.e("CAMERA SOURCE", ie.message)
                }
            }

            override fun surfaceChanged(holder: SurfaceHolder?,
                                        format: Int, width: Int,
                                        height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                cameraSource.stop()
            }
        })

        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {

            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>?) {
                val barcodes = detections?.detectedItems
                if (barcodes?.size() != 0) {
                    var ID = barcodes!!.valueAt(0)!!.displayValue

                    myIntent.putExtra("ID", ID)
                    startActivity(myIntent)

                    barcodeDetector.release()
                }
            }
        })

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun allPermissionEnabled() : Boolean {
        return permissionList.none{ checkSelfPermission(it) !=
                PackageManager.PERMISSION_GRANTED}
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupMultiplePermissions(){
        val remainingPermissions = permissionList.filter {
            checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED
        }
        requestPermissions(remainingPermissions.toTypedArray(), 101)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissionList: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissionList, grantResults)
        if (requestCode == 101) {
            if (grantResults.any{it != PackageManager.PERMISSION_GRANTED}) {
                @TargetApi(Build.VERSION_CODES.M)
                if (permissionList.any{shouldShowRequestPermissionRationale(it)}) {
                    AlertDialog.Builder(this)
                        .setMessage("Press Permissions to Decide Permission Again")
                        .setPositiveButton("Permissions"){dialog, which -> setupMultiplePermissions()}
                        .setNegativeButton("Cancel"){dialog, which->dialog.dismiss()}
                        .create()
                        .show()
                }
            }
        }
    }
}
