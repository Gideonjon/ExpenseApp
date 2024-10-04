package com.example.expenseapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expenseapp.R
import com.example.expenseapp.data.ExpenseData

class ExpenseAdapter(private val list: MutableList<ExpenseData>) :
    RecyclerView.Adapter<ExpenseAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val expensePrice: TextView = itemView.findViewById(R.id.amount)
        val expenseDescription: TextView = itemView.findViewById(R.id.expenseDescription)
        val splitOption: TextView = itemView.findViewById(R.id.splitOption)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.expense, parent, false)


        return ExpenseAdapter.MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {

        return list.size


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = list[position]

        holder.expensePrice.text = currentItem.expensePrice
        holder.expenseDescription.text = currentItem.expenseDescription
        holder.splitOption.text = currentItem.expenseSplitOption


    }


}