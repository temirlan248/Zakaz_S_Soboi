package kz.iitu.zakaz_s_soboi.presentation.loader

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import kz.iitu.zakaz_s_soboi.R

class Loader {
    companion object {

        private lateinit var alertDialog: AlertDialog

        fun start() {
            if (!alertDialog.isShowing) {
                alertDialog.show()
            }
        }

        fun stop() = alertDialog.hide()

        fun init(activity: Activity) {
            AlertDialog.Builder(activity).apply {
                setView(activity.layoutInflater.inflate(R.layout.loading_dialog, null))
                setCancelable(false)
                alertDialog = create()
            }
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}
