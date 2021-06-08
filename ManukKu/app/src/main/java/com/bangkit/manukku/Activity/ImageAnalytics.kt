package com.bangkit.manukku.Activity

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.speech.RecognitionListener
import android.speech.RecognitionService
import com.bangkit.manukku.ml.BirdModelVit
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class ImageAnalytics(context: Context, private val listener: RecognitionListener, Image: Bitmap){
//    val model = BirdModelVit.newInstance(context)
//
//    // Creates inputs for reference.
//    val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
//
//    var tensorImage = TensorImage(DataType.FLOAT32)
//    tensorU
//
//    inputFeature0.loadBuffer(byteBuffer)
//
//    // Runs model inference and gets result.
//    val outputs = model.process(inputFeature0)
//    val outputFeature0 = outputs.outputFeature0AsTensorBuffer
//
//// Releases model resources if no longer used.
//    model.close()

}