package com.tsu.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class AlertDialogFragment: DialogFragment() {
    interface AlertDialogListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    private lateinit var listener: AlertDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as AlertDialogListener
        } catch (e: ClassCastException) {
            throw java.lang.ClassCastException(("$context must implement AlertDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val activity = activity ?: throw IllegalStateException("Activity cannot be null")
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Test Alert Title")
        builder.setMessage("Test Alert Message")

        builder.setPositiveButton("Positive") { dialog, which ->
            listener.onDialogPositiveClick()
        }
        builder.setNegativeButton("Negative") { dialog, which ->
            listener.onDialogNegativeClick()
        }

        return builder.create()
    }
}

