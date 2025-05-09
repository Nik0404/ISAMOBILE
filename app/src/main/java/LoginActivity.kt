package com.example.isa.presentation.activity.login

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.isa.R
import com.example.isa.common.constants.Constants
import com.example.isa.presentation.fragment.login.auth.LoginFragment
import com.example.isa.presentation.mvp.BaseActivity
import kotlin.math.abs


class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadBackground()

        if (savedInstanceState == null) {
            val isAfterLogout =
                intent.getBooleanExtra(Constants.KEY_IS_AFTER_LOGOUT, false)

            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, LoginFragment.newInstance(isAfterLogout))
                .commit()
        }
    }

    private fun loadBackground() {
        val drawable = if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            R.drawable.activity_login_background_low_api
        } else R.drawable.activity_login_background

        val backgroundBitmap = BitmapFactory.decodeResource(resources, drawable)
        val croppedBitmap = cropBitmap(backgroundBitmap)
        setBitmapToWindow(croppedBitmap)
    }

    private fun cropBitmap(sourceBitmap: Bitmap): Bitmap {
        val size = Point()
        windowManager.defaultDisplay.getSize(size)

        val screenWidth = size.x.toDouble()
        val screenHeight = size.y.toDouble()

        val imageWidth = sourceBitmap.width.toDouble()
        val imageHeight = sourceBitmap.height.toDouble()

        val heightsRelation = (screenHeight / imageHeight)
        val widthsRelation = (screenWidth / imageWidth)

        val magicNumber = 0.9
        return when {
            heightsRelation > widthsRelation -> {
                val newImageWidth = screenWidth / heightsRelation
                Bitmap.createBitmap(
                    sourceBitmap,
                    (abs((imageWidth - newImageWidth)) / 2).toInt(),
                    0,
                    (newImageWidth * magicNumber).toInt(),
                    imageHeight.toInt()
                )
            }

            heightsRelation < widthsRelation -> {
                val newImageHeight = screenHeight / widthsRelation
                Bitmap.createBitmap(
                    sourceBitmap,
                    0,
                    (abs((imageHeight - newImageHeight)) / 2).toInt(),
                    (imageWidth * magicNumber).toInt(),
                    newImageHeight.toInt()
                )
            }

            else -> sourceBitmap
        }
    }

    private fun setBitmapToWindow(bitmap: Bitmap) {
        Glide.with(this)
            .asBitmap().load(bitmap)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) = Unit
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    window?.setBackgroundDrawable(BitmapDrawable(resources, resource))
                }
            })
    }
}
