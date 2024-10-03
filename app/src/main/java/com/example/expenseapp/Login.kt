package com.example.expenseapp

import android.os.Bundle
import android.text.InputFilter
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.expenseapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class Login : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root


        val editText = binding.passwordEt.text
        editText?.filters = arrayOf(InputFilter.LengthFilter(10))

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        binding.login.setOnClickListener {

            if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailEt.text.toString()).matches()) {
                Toast.makeText(requireContext(), "Incorrect Email", Toast.LENGTH_SHORT).show()
                binding.emailAddress.requestFocus()
            }
            if (binding.passwordEt.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Password Field Cant Be Empty", Toast.LENGTH_SHORT)
                    .show()
                binding.psw.requestFocus()
            } else {
                auth.signInWithEmailAndPassword(
                    binding.emailEt.text.toString(),
                    binding.passwordEt.text.toString()
                )
                    .addOnCompleteListener {
                        if (currentUser != null) {
                            if (currentUser.isEmailVerified)
                                Toast.makeText(
                                    requireContext(),
                                    "Login Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                            Navigation.findNavController(view)
                                .navigate(R.id.action_login_to_homeFragment)

                        }


                    }.addOnFailureListener {
                        Toast.makeText(requireContext(), "Cant Login", Toast.LENGTH_SHORT).show()
                    }
            }


        }

        binding.signUp.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_login_to_signUp)
        }




        return view
    }


}