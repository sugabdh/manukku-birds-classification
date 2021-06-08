package com.bangkit.manukku.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.speech.RecognitionListener
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.manukku.databinding.ActivityCameraBinding
import com.bangkit.manukku.ml.BirdModelVit
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_camera.*
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.util.*


class CameraActivity : AppCompatActivity() {

    private var state = false
    private var bitmap: Bitmap? = null
    private var filepath: Uri? = null
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.getReferenceFromUrl("gs://manukku.appspot.com/images-user/photoUpload")

    private lateinit var binding: ActivityCameraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        button()

    }

    private fun button(){
        binding.button2.setOnClickListener{
            if(state){
                ImageAnalytics(bitmap!!)
                Toast.makeText(this, "Gambar Sedang Diproses", Toast.LENGTH_SHORT).show()
            } else{
                moveToCamera()
            }
        }
    }

    private fun moveToCamera(){
        ImagePicker.with(this)
            .crop()
            .compress(1024)
            .maxResultSize(224,224)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            val uri: Uri = data?.data!!
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            state = true
            binding.button2.text = "Deteksi"
            filepath = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepath)
            imageView.setImageURI(uri)
        }
    }

    private fun ImageAnalytics(bitmap: Bitmap){

        val fileName = "labels.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use { it.readText() }
        val townList = inputString.split("\n")

        val model = BirdModelVit.newInstance(this)

        val result: MutableList<RecognitionListener> = mutableListOf<RecognitionListener>()
        // Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)

        val tensorImage = TensorImage(DataType.FLOAT32)
        val tfImage = tensorImage.load(bitmap)
        val byteBuffer = tensorImage.buffer;

        inputFeature0.loadBuffer(byteBuffer)

        // Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer
        Log.d("detectionResult", outputFeature0.toString())

        var max = getMax(outputFeature0.floatArray)

        binding.tvResult.text = townList[max]

// Releases model resources if no longer used.
        model.close()
    }

    private fun uploadImage() {
        if(filepath != null){
            val ref = storageRef
            val uploadTask = ref.putFile(filepath!!)

            val urlTask = uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    addUploadRecordToDb(downloadUri.toString())
                } else {
                    // Handle failures
                }
            }?.addOnFailureListener{

            }
        }else{
            Toast.makeText(this, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addUploadRecordToDb(uri: String){
        val db = FirebaseFirestore.getInstance()

        val data = HashMap<String, Any>()
        data["imageUrl"] = uri

        db.collection("posts")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Saved to DB", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error saving to DB", Toast.LENGTH_LONG).show()
            }
    }

    fun getMax(arr:FloatArray):Int{
        var ind = 0
        var min = 0.0f

        for(i in 0..13){
            if(arr[i]>min){
                ind = i
                min = arr[i]
            }
        }
        return ind
    }
}