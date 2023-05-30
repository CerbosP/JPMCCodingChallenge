package com.example.jpmccodingchallenge.view.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jpmccodingchallenge.databinding.FragmentStartingPageBinding

class StartingPageFragment: ViewModelFragment() {

    lateinit var binding: FragmentStartingPageBinding
    val args: StartingPageFragmentArgs by navArgs()

    private lateinit var tempUnit: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStartingPageBinding.inflate(layoutInflater)

        binding.tietCity.setText(args.cityName)

        if(args.cityName!!.isNotEmpty()) binding.btnFindWeather.isEnabled = true

        binding.tietCity.addTextChangedListener {
            binding.btnFindWeather.isEnabled = (binding.tietCity.length() != 0)
        }


        binding.btnFindWeather.setOnClickListener {
            viewModel.setWeatherLoading()

            tempUnit = when (binding.rgUnit.checkedRadioButtonId) {
                binding.rbFahrenheit.id -> "imperial"
                binding.rbCelsius.id -> "metric"
                else -> "standard"
            }

            findNavController().navigate(StartingPageFragmentDirections
                .actionStartingPageToWeatherDetail(binding.tietCity.text.toString(), tempUnit))
        }

        return binding.root

    }
}