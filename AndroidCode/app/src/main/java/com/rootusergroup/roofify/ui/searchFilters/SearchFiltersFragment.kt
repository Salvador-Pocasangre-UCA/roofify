package com.rootusergroup.roofify.ui.searchFilters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.google.android.material.slider.Slider
import com.rootusergroup.roofify.R
import java.text.NumberFormat
import java.util.*

class SearchFiltersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search_filters, container, false)
        view.findViewById<Slider>(R.id.price_slider).setLabelFormatter { value: Float ->
            val format = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency = Currency.getInstance("USD")
            format.format(value.toDouble())
        }

        view.findViewById<ImageView>(R.id.ic_close).setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }
}