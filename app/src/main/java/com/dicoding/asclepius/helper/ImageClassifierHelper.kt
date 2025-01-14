package com.dicoding.asclepius.helper

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.dicoding.asclepius.ml.CancerClassification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.tensorflow.lite.support.image.TensorImage

@Suppress("DEPRECATION")
class ImageClassifierHelper(private val context: Context) {
    private var model: CancerClassification? = null

    init {
        setupImageClassifier()
    }

    private fun setupImageClassifier() {
        model = CancerClassification.newInstance(context)
    }

    fun classifyStaticImage(imageUri: Uri, callback: (List<Pair<String, Float>>?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val bitmap = uriToBitmap(imageUri)
            if (bitmap == null) {
                withContext(Dispatchers.Main) {
                    callback(null)
                }
                return@launch
            }
            val tensorImage = TensorImage.fromBitmap(bitmap)
            val outputs = model?.process(tensorImage)
            val probability = outputs?.probabilityAsCategoryList
            val result = probability?.map { category ->
                category.label to category.score
            }
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

    private fun uriToBitmap(uri: Uri): Bitmap? {
        return try {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun close() {
        model?.close()
        model = null
    }
}