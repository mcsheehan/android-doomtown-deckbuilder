package net.marksheehan.doomtowndeckbuilder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.text_and_checkbox_item.view.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.database.PackEntity

interface PackEntityClicked {
    fun clicked(packEntity: PackEntity)
}

class PackListAdapter(private val packEntityClicked : PackEntityClicked) :
        ListAdapter<PackEntity, PackListAdapter.ItemViewHolder>(PackListAdapter.PackEntityDiff()) {

    class PackEntityDiff : DiffUtil.ItemCallback<PackEntity>() {

        override fun areItemsTheSame(oldItem: PackEntity, newItem: PackEntity): Boolean {
            return (oldItem.packname == newItem.packname)
        }

        override fun areContentsTheSame(oldItem: PackEntity, newItem: PackEntity): Boolean {
            return (oldItem.isSelected != newItem.isSelected)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // Inflate the custom layout
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.text_and_checkbox_item, parent, false)

        return ItemViewHolder(itemView, packEntityClicked)
    }

    override fun onBindViewHolder(viewItem: PackListAdapter.ItemViewHolder, position: Int) {
        viewItem.bind(getItem(position))
    }

    class ItemViewHolder(itemView: View, private val packEntityClicked : PackEntityClicked) : RecyclerView.ViewHolder(itemView){

        fun bind(packEntity : PackEntity) = with(itemView){
            itemView.checkedTextView.text = packEntity.packname
            itemView.checkedTextView.isChecked = packEntity.isSelected
            itemView.checkedTextView.setOnClickListener {  packEntityClicked.clicked(packEntity)}
        }
    }
}