package com.tsu.dialogs

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tsu.dialogs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main), AlertDialogFragment.AlertDialogListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val myClickListener = DialogInterface.OnClickListener { dialog, which ->
            when (which) {
                Dialog.BUTTON_POSITIVE -> Toast.makeText(this, "Positive", Toast.LENGTH_SHORT)
                    .show()
                Dialog.BUTTON_NEGATIVE -> Toast.makeText(this, "Negative", Toast.LENGTH_SHORT)
                    .show()
                Dialog.BUTTON_NEUTRAL -> Toast.makeText(this, "Neutral", Toast.LENGTH_SHORT).show()
            }
        }

        val myCancelListener = DialogInterface.OnCancelListener { dialog ->
            Toast.makeText(this, "Nothing clicked", Toast.LENGTH_SHORT).show()
        }

        binding.alertButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Test Alert Title")
            builder.setMessage("Test Alert Message")

            builder.setPositiveButton("Positive", myClickListener)
            builder.setNegativeButton("Negative", myClickListener)
            builder.setNeutralButton("Neutral", myClickListener)
            builder.setOnCancelListener(myCancelListener)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setCancelable(false)
            builder.show()
        }

        binding.materialAlertButton.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(this)
            builder.setTitle("Test Alert Title")
            builder.setMessage("Test Alert Message")
            builder.setPositiveButton("Positive") { _, _ ->
                Toast.makeText(this, "Positive button clicked", Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("Negative") { _, _ ->
                Toast.makeText(this, "Negative button clicked", Toast.LENGTH_SHORT).show()
            }
            builder.setOnCancelListener {
                Toast.makeText(this, "Nothing clicked", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }

        val items = arrayOf("Red", "Blue", "Green", "Cyan", "Magenta", "Yellow")

        binding.singleChoiceButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Simple Dialog")
            builder.setItems(items) { dialog, which ->
                when (which) {
                    0, 1, 2 -> Toast.makeText(this, "RGB is chosen", Toast.LENGTH_SHORT).show()
                    3, 4, 5 -> Toast.makeText(this, "CMYK is chosen", Toast.LENGTH_SHORT).show()
                }
            }
            builder.show()
        }

        binding.radiobuttonButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Single choice dialog")
            builder.setSingleChoiceItems(items, 3) { dialog, which ->
                when (which) {
                    0, 1, 2 -> Toast.makeText(this, "RGB is chosen", Toast.LENGTH_SHORT).show()
                    3, 4, 5 -> Toast.makeText(this, "CMYK is chosen", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setPositiveButton("Positive", myClickListener)
            builder.setNeutralButton("Neutral", myClickListener)
            builder.show()
        }

        val selectedItems = arrayListOf(0, 3, 4)
        val checked = booleanArrayOf(true, false, false, true, true, false)
        binding.checkboxesButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Multiple choice dialog")
            builder.setMultiChoiceItems(items, checked) { dialog, which, isChecked ->
                if (isChecked) {
                    selectedItems.add(which)
                } else {
                    selectedItems.remove(which)
                }
            }
            builder.setPositiveButton("Positive") { dialog, which ->
                Toast.makeText(
                    this,
                    selectedItems.map { index -> items[index] }.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            builder.show()
        }

        binding.customViewButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Test")
            builder.setMessage("Test msg")
            builder.setView(R.layout.custom_dialog)
            builder.setPositiveButton("OK", myClickListener)
            builder.show()
        }

        binding.timeButton.setOnClickListener {
            TimePickerDialog(this, { timePicker, hour, minute ->
                    Toast.makeText(this, "$hour:$minute is selected", Toast.LENGTH_SHORT).show()
                },
                14, 40, true
            ).show()
        }

        binding.dateButton.setOnClickListener {
            DatePickerDialog(this, { datePicker, year, month, day ->
                Toast.makeText(this, "$day.$month.$year is selected", Toast.LENGTH_SHORT).show()
            },
                2021, 11, 31).show()
        }

        binding.fragmentButton.setOnClickListener {
            val fragment = AlertDialogFragment()
            fragment.show(supportFragmentManager, "alert")
        }
    }

    override fun onDialogPositiveClick() {
        Toast.makeText(this, "Positive button clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onDialogNegativeClick() {
        Toast.makeText(this, "Negative button clicked", Toast.LENGTH_SHORT).show()
    }
}

