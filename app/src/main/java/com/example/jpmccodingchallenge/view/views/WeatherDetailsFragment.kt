package com.example.jpmccodingchallenge.view.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jpmccodingchallenge.R
import com.example.jpmccodingchallenge.databinding.FragmentWeatherDetailsBinding
import com.example.jpmccodingchallenge.model.UIState
import com.example.jpmccodingchallenge.model.json.WeatherResponse
import java.util.Locale

class WeatherDetailsFragment: ViewModelFragment() {
    lateinit var binding: FragmentWeatherDetailsBinding
    val args: WeatherDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWeatherDetailsBinding.inflate(layoutInflater)

        binding.tvCityDetail.text = args.cityName

        binding.ibtnBackDetail.setOnClickListener {
            findNavController().popBackStack()
        }

        configureObserver()

        return binding.root
    }

    private fun configureObserver() {
        viewModel.weatherList.observe(viewLifecycleOwner) { state ->
            when(state) {
                is UIState.Loading -> {
                    viewModel.getWeather(args.cityName, args.tempUnit)
                }
                is UIState.Error -> {
                    binding.pbLoadDetails.visibility = View.GONE
                    binding.tvLoadDetailsText.text = state.error.message
                }
                is UIState.Success<*> -> {

                    val weatherItem: WeatherResponse = (state.response as WeatherResponse)
                    binding.apply {
                        pbLoadDetails.visibility = View.GONE
                        tvLoadDetailsText.visibility = View.GONE

                        tvTempDetail.text = "${(weatherItem.main.temp + 0.5).toInt()}°"
                        tvHighDetail.text = "High: ${(weatherItem.main.temp_max + 0.5).toInt()}°"
                        tvLowDetail.text = "Low: ${(weatherItem.main.temp_min + 0.5).toInt()}°"
                        tvFeelsLikeDetails.text = "Feels Like: ${(weatherItem.main.feels_like + 0.5).toInt()}°"
                        tvRainDetails.text = "Rain: ${(weatherItem.pop * 100).toInt()}%"
                        tvWeatherDetail.text = weatherItem.weather[0].main
                        tvDescriptionDetail.text = weatherItem.weather[0].description.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }


                        when(weatherItem.weather[0].main) {
                            "Clear" -> ivConditionDetail.setImageResource(R.drawable.ic_clear)
                            "Clouds" -> ivConditionDetail.setImageResource(R.drawable.ic_cloud)
                            "Rain" -> ivConditionDetail.setImageResource(R.drawable.ic_rain)
                            "Snow" -> ivConditionDetail.setImageResource(R.drawable.ic_snow)
                        }
                    }
                }
            }
        }
    }
}