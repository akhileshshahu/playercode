package com.akhiplayer.akhi.utils


import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.showDialog(
    @StringRes title: Int,
    @StringRes message: Int,
    positiveBlock: () -> (Unit),
    negativeBlock: () -> (Unit)
) {
    AlertDialog.Builder(this)
        .setTitle(getString(title))
        .setMessage(getString(message))
        .setPositiveButton(android.R.string.yes) { dialog, _ ->
            positiveBlock()
            dialog.dismiss()
        }
        .setNegativeButton(android.R.string.no) { dialog, _ ->
            negativeBlock()
            dialog.dismiss()
        }
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show().apply {
            getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
            getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
        }

}