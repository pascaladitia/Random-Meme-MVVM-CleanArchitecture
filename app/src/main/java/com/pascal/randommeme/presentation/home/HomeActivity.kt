package com.pascal.randommeme.presentation.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.pascal.randommeme.databinding.ActivityHomeBinding
import com.pascal.randommeme.domain.model.MemesItem
import com.pascal.randommeme.domain.model.ResponseMeme
import com.pascal.randommeme.utils.initPermissionStorage
import com.pascal.randommeme.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initView()
        attachObserve()
        initPermissionStorage()
    }

    private fun initView() {
        viewModel.loadMeme()

        val pullToRefresh: SwipeRefreshLayout = binding.swipe
        pullToRefresh.setOnRefreshListener {
            viewModel.loadMeme()

            pullToRefresh.isRefreshing = false
        }
    }

    private fun attachObserve() {
        viewModel.responseMeme.observe(this, Observer {showData(it)})
        viewModel.isError.observe(this, Observer {showToast(it.toString())})
        viewModel.isLoading.observe(this, Observer {showLoading(it)})
    }

    private fun showData(it: ResponseMeme?) {
        val adapter = HomeAdapter(it?.memes, object : HomeAdapter.OnClickListener {
            override fun detail(item: MemesItem) {
                saveImage2(item.url.toString())
            }

        })
        binding.carouselRecyclerview.adapter = adapter
        binding.carouselRecyclerview.setOrientation(RecyclerView.VERTICAL)
    }

    private fun showLoading(it: Boolean?) {
        if (it == true) {

        } else {

        }
    }

    private fun saveImage2(url: String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : SimpleTarget<Bitmap>(1920, 1080) {
                override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                    saveImage(bitmap)
                }
            })
    }

    private fun saveImage(image: Bitmap) {
        try {
            val timeStamp: String = SimpleDateFormat("ddMMyyyy_HHmm").format(Date())

            val root = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            ).toString()
            val myDir = File("$root/saved_images")
            myDir.mkdirs()
            val fname = timeStamp + ".png"
            val file = File(myDir, fname)
            val out = FileOutputStream(file)
            val bm: Bitmap = image
            bm.compress(Bitmap.CompressFormat.PNG, 100, out)
            out.flush()
            out.close()

            showToast("Image Saving...")
        } catch (e: Exception) {
            Log.d("onBtnSavePng", e.toString())
        }

    }
}