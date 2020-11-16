package com.sumcorp.flashprep.ui.recentSearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumcorp.flashprep.R
import com.sumcorp.flashprep.data.local.entity.RecentSearchData
import kotlinx.android.synthetic.main.item_recent_search.view.*

class RecentSearchAdapter(onItemClick: OnItemClick) :
    RecyclerView.Adapter<RecentSearchAdapter.Holder>() {

    private var onItemClick: OnItemClick? = onItemClick
    private var searchlist: ArrayList<RecentSearchData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recent_search, parent,
                false
            )
        )


    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (searchlist != null) {
            holder.bind(searchlist!!.get(position))

            holder.itemView.setOnClickListener {
                onItemClick!!.itemClick(searchlist!![holder.adapterPosition].recentSearch)
            }
        }
    }

    override fun getItemCount(): Int =
        if (searchlist != null) {
            searchlist!!.size
        } else
            0

    fun sendData(resultList: ArrayList<RecentSearchData>) {
        this.searchlist = resultList
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(searchString: RecentSearchData) {
            itemView.tvRecentSearch.text = searchString.recentSearch
        }

    }

    interface OnItemClick {
        fun itemClick(text: String)
    }
}