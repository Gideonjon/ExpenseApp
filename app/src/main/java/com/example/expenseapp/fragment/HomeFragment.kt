package com.example.expenseapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expenseapp.R
import com.example.expenseapp.adapter.ExpenseAdapter
import com.example.expenseapp.data.ExpenseData
import com.example.expenseapp.databinding.FragmentHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import java.util.Calendar


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var userArrayList: MutableList<ExpenseData>
    private lateinit var taskAdapter: ExpenseAdapter
    var fabVisible = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference.child("ExpenseAdded")

        binding.BtnAddExpense.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_expenseFragment)
        }


        check()
        getUserData()

        return view
    }

    private fun getUserData() {
        userArrayList = mutableListOf()
        taskAdapter = ExpenseAdapter(userArrayList)
        binding.transactionList.setHasFixedSize(true)
        binding.transactionList.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {


                    for (userSnapshot in snapshot.children) {

                        val user = userSnapshot.getValue(ExpenseData::class.java)

                        if (user != null) {
                            userArrayList.add(user)
                        }

                    }
                    binding.transactionList.adapter = taskAdapter

                }
                taskAdapter.notifyDataSetChanged()


            }

            override fun onCancelled(error: DatabaseError) {


            }


        })


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