package com.example.jpmccodingchallenge.view.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.jpmccodingchallenge.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class ViewModelFragment: Fragment() {
    protected val viewModel: WeatherViewModel by activityViewModels()
}