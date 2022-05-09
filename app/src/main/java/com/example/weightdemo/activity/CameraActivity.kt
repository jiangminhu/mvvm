package com.example.weightdemo.activity

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.example.weightdemo.R
import com.google.common.util.concurrent.ListenableFuture
import com.lihui.qmyn.util.permission.PermissionUtil

class CameraActivity : AppCompatActivity() {

    private var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>? = null
    private lateinit var previewView: PreviewView
    private var camera: Camera? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        previewView = findViewById(R.id.surfaceView)

        PermissionUtil.get().with(this, arrayOf(Manifest.permission.CAMERA)) {
            if (it) {
                initCamera()
            }
        }


    }

    private fun initCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture?.addListener({
            val cameraProvider = cameraProviderFuture?.get()
            if (cameraProvider != null) {
                bindPreview(cameraProvider)
            }
        }, ContextCompat.getMainExecutor(this))

    }


    private fun getPreview(): Preview {
        return Preview.Builder().build()
    }


    private fun bindPreview(cameraProvider: ProcessCameraProvider) {
        val preview = getPreview()

        val cameraSelector =
            CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
        preview.setSurfaceProvider(previewView.surfaceProvider)
        val imageCapture = ImageCapture.Builder()
            .setTargetRotation(previewView.display.rotation).build()
        camera = cameraProvider.bindToLifecycle(this, cameraSelector, imageCapture,preview)
    }


    private fun takeCapture() {

    }

}