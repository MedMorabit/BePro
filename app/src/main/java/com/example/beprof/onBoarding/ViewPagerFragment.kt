package com.example.beprof.onBoarding

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.example.beprof.PagerItemPosition
import com.example.beprof.R
import com.example.beprof.databinding.FragmentViewPagerBinding
import com.example.beprof.screens.FirstFragment
import com.example.beprof.screens.SecondFragment
import com.example.beprof.screens.ThirdFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ViewPagerFragment : Fragment() {
lateinit var binding:FragmentViewPagerBinding
private lateinit var adapter: ViewPagerAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentViewPagerBinding.inflate(layoutInflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentList= arrayListOf<Fragment>(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )
        adapter=ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewPager.adapter=adapter
        TabLayoutMediator(binding.tabDots,binding.viewPager){tab,position->


        }.attach()
    }






}


