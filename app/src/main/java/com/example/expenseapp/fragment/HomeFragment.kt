package com.example.expenseapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expenseapp.databinding.FragmentHomeBinding
import java.util.Calendar


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        check()

        return view
    }

    private fun check() {

        val c: Calendar = Calendar.getInstance()
        val timeOfDay: Int = c.get(Calendar.HOUR_OF_DAY)
        val text1 = "Good Morning"
        val text2 = "Good Afternoon"
        val text3 = "Good Evening"
        val text4 = "Good Night"
        if (timeOfDay in 0..11) {
            binding.greetingsTxt.text = text1.toString()
        } else if (timeOfDay in 12..15) {
            binding.greetingsTxt.text = text2.toString()
        } else if (timeOfDay in 16..20) {
            binding.greetingsTxt.text = text3.toString()
        } else {
            if (timeOfDay in (21..23)) {
                binding.greetingsTxt.text = text4.toString()
            }
        }
    }
}