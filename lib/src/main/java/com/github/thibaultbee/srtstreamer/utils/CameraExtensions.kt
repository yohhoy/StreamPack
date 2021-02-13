package com.github.thibaultbee.srtstreamer.utils

import android.Manifest
import android.content.Context
import android.graphics.ImageFormat
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.util.Range
import android.util.Size
import androidx.annotation.RequiresPermission


@RequiresPermission(Manifest.permission.CAMERA)
fun Context.getCameraCharacteristics(cameraId: String): CameraCharacteristics? {
    val cameraManager = this.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    return cameraManager.getCameraCharacteristics(cameraId)
}

@RequiresPermission(Manifest.permission.CAMERA)
fun Context.getCameraList(): List<String> {
    val cameraManager = this.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    return cameraManager.cameraIdList.toList()
}

@RequiresPermission(Manifest.permission.CAMERA)
fun Context.getOutputCaptureSizes(cameraId: String): List<Size> {
    val cameraManager = this.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    return cameraManager.getCameraCharacteristics(cameraId)[CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP]?.getOutputSizes(
        ImageFormat.YUV_420_888
    )?.toList() ?: emptyList()
}

@RequiresPermission(Manifest.permission.CAMERA)
fun <T : Any> Context.getOutputSizes(klass: Class<T>, cameraId: String): List<Size> {
    val cameraManager = this.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    return cameraManager.getCameraCharacteristics(cameraId)[CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP]?.getOutputSizes(
        klass
    )?.toList() ?: emptyList()
}

@RequiresPermission(Manifest.permission.CAMERA)
fun Context.getFpsList(cameraId: String): List<Range<Int>> {
    val cameraManager = this.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    return cameraManager.getCameraCharacteristics(cameraId)[CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES]?.toList()
        ?: emptyList()
}