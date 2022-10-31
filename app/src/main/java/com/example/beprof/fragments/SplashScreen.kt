package com.example.beprof.fragments

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.beprof.R
import com.example.beprof.databinding.FragmentSplashScreenBinding

class SplashScreen: Fragment() {
lateinit var binding:FragmentSplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding= FragmentSplashScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animation=AnimationUtils.loadAnimation(requireContext(),R.anim.splash_anim)
        binding.imageView.startAnimation(animation)
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashScreen_to_viewPagerFragment)

        },3000)
    }

}

