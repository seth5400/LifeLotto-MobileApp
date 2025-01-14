package com.example.testp1.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testp1.R
import com.example.testp1.databinding.FragmentDashboardBinding
import com.example.testp1.menu_activity
import com.example.testp1.menu_clothes
import com.example.testp1.menu_edit
import com.example.testp1.menu_food
import com.example.testp1.menu_movie
import com.example.testp1.menu_music

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val clothesMenuButton: Button = root.findViewById(R.id.menu_btn_clothes)
        clothesMenuButton.setOnClickListener {
            val intent = Intent(activity,menu_clothes::class.java)
            startActivity(intent)
        }
        val foodMenuButton: Button = root.findViewById(R.id.menu_btn_food)
        foodMenuButton.setOnClickListener {
            val intent = Intent(activity,menu_food::class.java)
            startActivity(intent)
        }
        val musicMenuButton: Button = root.findViewById(R.id.menu_btn_music)
        musicMenuButton.setOnClickListener {
            val intent = Intent(activity,menu_music::class.java)
            startActivity(intent)
        }
        val activityMenuButton: Button = root.findViewById(R.id.menu_btn_activity)
        activityMenuButton.setOnClickListener {
            val intent = Intent(activity,menu_activity::class.java)
            startActivity(intent)
        }
        val movieMenuButton: Button = root.findViewById(R.id.menu_btn_movie)
        movieMenuButton.setOnClickListener {
            val intent = Intent(activity,menu_movie::class.java)
            startActivity(intent)
        }
        val editMenuButton: Button = root.findViewById(R.id.menu_btn_edit)
        editMenuButton.setOnClickListener {
            val intent = Intent(activity,menu_edit::class.java)
            startActivity(intent)
        }


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}