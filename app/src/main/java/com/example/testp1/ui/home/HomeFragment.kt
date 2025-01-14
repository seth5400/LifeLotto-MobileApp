package com.example.testp1.ui.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testp1.Setting
import com.example.testp1.databinding.FragmentHomeBinding
import com.example.testp1.dice
import com.example.testp1.faq
import com.example.testp1.fav

class HomeFragment : Fragment() {
    private lateinit var Home_Faq: Button
    private lateinit var Home_Setting: Button
    private lateinit var Home_Dice: Button
    private lateinit var Home_Fav: Button


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Home_Setting = binding.HomeSetting

        Home_Setting.setOnClickListener {
            val intent = Intent(requireContext(),Setting::class.java)
            startActivity(intent)
        }

        Home_Faq = binding.HomeFq

        Home_Faq.setOnClickListener {
            val intent = Intent(requireContext(),faq::class.java)
            startActivity(intent)
        }

        Home_Dice = binding.HomeDice

        Home_Dice.setOnClickListener {
            val intent = Intent(requireContext(),dice::class.java)
            startActivity(intent)
        }


        Home_Fav = binding.HomeFav

        Home_Fav.setOnClickListener {
            val intent = Intent(requireContext(),fav::class.java)
            startActivity(intent)
        }

        // เพิ่มคำสั่งนี้
        //activity?.window?.statusBarColor = Color.parseColor("#0cc0df")




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}