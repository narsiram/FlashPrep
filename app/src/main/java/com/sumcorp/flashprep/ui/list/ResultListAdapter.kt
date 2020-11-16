package com.sumcorp.flashprep.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sumcorp.flashprep.R
import com.sumcorp.flashprep.data.model.ResultListData
import kotlinx.android.synthetic.main.item_superhero_layout.view.*
import java.util.ArrayList

class ResultListAdapter(val onClickEvent: OnClickEvent) :
    RecyclerView.Adapter<ResultListAdapter.Holder>() {
    var resultList: ArrayList<ResultListData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_superhero_layout, parent,
                false
            )
        )

    override fun onBindViewHolder(holder: Holder, position: Int) {

        if (resultList != null) {
            holder.bind(resultList!![position])
            holder.itemView.setOnClickListener {
                onClickEvent.onItemClick(holder.adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int =
        if (resultList != null)
            resultList!!.size
        else
            0


    fun setData(resultList: ArrayList<ResultListData>) {
        this.resultList = resultList
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(resultListData: ResultListData) {
            itemView.tvName.text = resultListData.name
            itemView.tvDescription.text = resultListData.connections.description

            Glide.with(itemView.context)
                .load(resultListData.profileUrl.url)
                .centerCrop()
                .placeholder(R.drawable.default_image_24)
                .into(itemView.imageProfilePic)
        }

    }

    interface OnClickEvent {
        fun onItemClick(position: Int)
    }
}