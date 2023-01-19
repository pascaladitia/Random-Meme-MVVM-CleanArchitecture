package com.pascal.randommeme.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.widget.Toast

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.showAlert(title: String, msg: String) {
    AlertDialog.Builder(this).apply {
        setTitle(title)
        setMessage(msg)
        setCancelable(false)

        setPositiveButton("Ok") { dialogInterface, i ->
            dialogInterface?.dismiss()
        }
    }.show()
}

fun Activity.initPermissionStorage() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), 2
        )
    }
}

class Constans {

    companion object {
        const val BASE_URL = "https://meme-api.com/"
    }
}