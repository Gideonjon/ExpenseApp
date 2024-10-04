package com.example.expenseapp.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.expenseapp.R
import com.example.expenseapp.databinding.FragmentSplashScreenBinding
import com.google.firebase.auth.FirebaseAuth


class SplashScreen : Fragment() {
    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = FirebaseAuth.getInstance()


        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            if (auth.currentUser != null) {
              Navigation.findNavController(view).navigate(R.id.action_splashScreen_to_homeFragment)
            } else {
                Navigation.findNavController(view)
                    .navigate(R.id.action_splashScreen_to_login)
            }
        }, 7000)

return view
    }

}