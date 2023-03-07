package com.example.ageinminute

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    private var dateSetPckr:TextView?=null
    private var tvSelectedDateInMinutes:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dateSetPckr=findViewById(R.id.tvDatePckr)
        tvSelectedDateInMinutes = findViewById(R.id.tvSelectedDateInMinutes)

        val btnDatePicker: Button = findViewById(R.id.btnDatePckr)
        //set onclicklistener to btnDatePicker
        btnDatePicker.setOnClickListener {
            //call clickDatePicker when this button is clicked
            clickDatePicker()
        }
    }

    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener{view,year,month,dayOfMonth ->



            val SelectedDate = "$year/${month+1}/$dayOfMonth"
            dateSetPckr?.setText(SelectedDate)



            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(SelectedDate)

            theDate?.let {

                val selectedDateInMinutes = theDate.time/60000
                // Here we have parsed the current date with the Date Formatter which is used above.
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                //use the safe call operator with .let to avoid app crashing it currentDate is null
                currentDate?.let {
                    // Current date in to minutes.
                    val currentDateToMinutes = currentDate.time/60000

                    val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
                    tvSelectedDateInMinutes?.text = differenceInMinutes.toString()
                }
            }



        },
        year,
        month,
        day
        ).show()
    }



    }




