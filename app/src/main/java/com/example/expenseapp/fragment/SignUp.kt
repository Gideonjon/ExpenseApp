package com.example.expenseapp.fragment

import android.os.Bundle
import android.text.InputFilter
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.expenseapp.R
import com.example.expenseapp.databinding.FragmentSignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class SignUp : Fragment() {
private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root



        val editText = binding.passwordEt.text
        editText?.filters = arrayOf(InputFilter.LengthFilter(10))

        auth = FirebaseAuth.getInstance()
        binding.btnSignUp.setOnClickListener {


            if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailEt.text.toString()).matches()) {
                Toast.makeText(requireContext(), "Input Correct Email Address", Toast.LENGTH_SHORT)
                    .show()
                binding.emailAddress.requestFocus()
            }
            if (binding.passwordEt.text.toString().length < 10) {
                Toast.makeText(
                    requireContext(),
                    "Password Must Be 10 Digit",
                    Toast.LENGTH_SHORT
                ).show()
                binding.psw.requestFocus()

            }
            if (!(binding.passwordEt.text.toString()
                    .contains(
                        Regex("(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])")
                    )
                        )
            ) {
                Toast.makeText(
                    requireContext(),
                    "Password Must Contain Small letters, UpperCase Letters and Numbers",
                    Toast.LENGTH_SHORT
                ).show()
                binding.psw.requestFocus()

            } else {
                auth.createUserWithEmailAndPassword(
                    binding.emailEt.text.toString(),
                    binding.passwordEt.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val user = Firebase.auth.currentUser
                            user!!.sendEmailVerification()
                            Toast.makeText(
                                requireContext(),
                                "Account Created,",
                                Toast.LENGTH_SHORT
                            ).show()
                            Navigation.findNavController(view)
                                .navigate(R.id.action_signUp_to_login)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Account Not Created",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }


            }
        }


        binding.login.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signUp_to_login)
        }

        return view
    }

}