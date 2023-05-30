package com.example.jpmccodingchallenge.view.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jpmccodingchallenge.R
import com.example.jpmccodingchallenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}