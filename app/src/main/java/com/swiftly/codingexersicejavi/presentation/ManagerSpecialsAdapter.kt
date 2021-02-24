package com.swiftly.codingexersicejavi.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swiftly.codingexersicejavi.R
import com.swiftly.core.data.ManagerSpecial
import kotlinx.android.synthetic.main.item_special.view.*

class ManagerSpecialsAdapter(var specials: ArrayList<ManagerSpecial>): RecyclerView.Adapter<ManagerSpecialsAdapter.SpecialsViewHolder>() {

    fun update(newSpecials: List<ManagerSpecial>?) {
        if (newSpecials.isNullOrEmpty())
            return
        specials.clear()
        specials.addAll(newSpecials)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialsViewHolder {
        return SpecialsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_special, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SpecialsViewHolder, position: Int) {
        holder.bind(specials[position])
    }

    override fun getItemCount(): Int = specials.size

    inner class SpecialsViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val description = view.tv_description
        private val originalPrice = view.tv_original_price
        private val newPrice = view.tv_new_price

        fun bind(special: ManagerSpecial){
            description.text = special.display_name
            originalPrice.text = '$'+"%.2f".format(special.original_price)
            newPrice.text = '$'+"%.2f".format(special.price)
        }
    }

}