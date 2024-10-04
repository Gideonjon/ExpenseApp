package com.example.expenseapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.expenseapp.R
import com.example.expenseapp.data.ExpenseData
import com.example.expenseapp.databinding.FragmentExpenseBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class ExpenseFragment : Fragment() {
    private var _binding: FragmentExpenseBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentExpenseBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.arrowBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_expenseFragment_to_homeFragment)
        }


        val languages = resources.getStringArray(R.array.SplitOptions)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdownmenu, languages)
        val autoCompleteTv = binding.dropMenu
        autoCompleteTv.setAdapter(arrayAdapter)


        binding.postExpense.setOnClickListener {

            if (isSpinnerEmpty(binding.dropMenu)) {
                Toast.makeText(requireContext(), "Select An Item", Toast.LENGTH_LONG).show()
            }
            if (binding.descriptionEt.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Description Cant Be Empty", Toast.LENGTH_LONG)
                    .show()
            }

            if (binding.priceEt.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Price Cant Be Empty", Toast.LENGTH_LONG).show()
            } else {
                val projectType = binding.dropMenu.text.toString()
                val projectDescription = binding.descriptionEt.text.toString()
                val projectTitle = binding.priceEt.text.toString()

                val jobPosted =
                    ExpenseData(projectType, projectDescription, projectTitle)
            }
        }

        return view

    }

    fun isSpinnerEmpty(autoCompleteTextView: AutoCompleteTextView): Boolean {
        val adapter = autoCompleteTextView.adapter
        return adapter == null || adapter.count == 0
    }

    private fun showSnackbar(message: String) {
        val rootView = binding.root
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()
        Snackbar.ANIMATION_MODE_SLIDE
    }


}