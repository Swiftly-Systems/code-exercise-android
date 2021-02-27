package com.swiftly.codingexersicejavi.presentation

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.swiftly.codingexersicejavi.R
import com.swiftly.core.data.ManagerSpecial
import kotlinx.android.synthetic.main.item_special.view.*

class ManagerSpecialsAdapter(): ListAdapter<ManagerSpecial, ManagerSpecialsAdapter.SpecialsViewHolder>(
    ManagerSpecialDiffCallback()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialsViewHolder {
        return SpecialsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_special, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SpecialsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SpecialsViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val description = view.tv_description
        private val originalPrice = view.tv_original_price
        private val newPrice = view.tv_new_price
        private val imgView = view.imageView

        fun bind(special: ManagerSpecial){
            description.text = special.display_name
            originalPrice.text = '$'+"%.2f".format(special.original_price)
            newPrice.text = '$'+"%.2f".format(special.price)
            originalPrice.setPaintFlags(originalPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

            var imgUrl = special.imageUrl

            imgUrl?.let {
                val imgUri =
                        imgUrl.toUri().buildUpon().scheme("https").build()
                Glide.with(imgView.context)
                    .load(imgUri)
                    .fitCenter()
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_download)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgView)
            }

        }
    }

}

class ManagerSpecialDiffCallback: DiffUtil.ItemCallback<ManagerSpecial>(){

    //helps determine whether item was added, removed, or moved
    override fun areItemsTheSame(oldItem: ManagerSpecial, newItem: ManagerSpecial): Boolean {
        return oldItem.display_name == newItem.display_name
    }

    //helps determine whether item was updated
    override fun areContentsTheSame(oldItem: ManagerSpecial, newItem: ManagerSpecial): Boolean {
        return oldItem.equals(newItem)
    }

}