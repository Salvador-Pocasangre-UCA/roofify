package com.rootusergroup.roofify.ui.detailedProperty

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.SupportMapFragment
import com.rootusergroup.roofify.R
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_detailedHouse.newInstance] factory method to
 * create an instance of this fragment.
 */

class Fragment_detailedHouse : Fragment() {

    private lateinit var googleMapsFragment: SupportMapFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun pickDateTime() {
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                TimePickerDialog(
                    requireContext(),
                    TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(year, month, day, hour, minute)
                        //doSomethingWith(pickedDateTime)
                    },
                    startHour,
                    startMinute,
                    false
                ).show()
            },
            startYear,
            startMonth,
            startDay
        ).show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detailed_house, container, false).apply {
        googleMapsFragment =
            childFragmentManager.findFragmentById(R.id.map_detailedProperty) as SupportMapFragment
        val dpicon = findViewById<ImageView>(R.id.user_agenda_icon)
        dpicon.setOnClickListener {
            pickDateTime()
        }

        findViewById<ImageView>(R.id.arrowBack_detailed_property).setOnClickListener {
            findNavController().popBackStack()
        }
    }

}