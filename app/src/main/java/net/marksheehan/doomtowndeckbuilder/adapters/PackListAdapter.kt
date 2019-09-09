package net.marksheehan.doomtowndeckbuilder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.text_and_checkbox_item.view.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.database.PackEntity

class PackListAdapter : ListAdapter<PackEntity, PackListAdapter.ViewHolder>(PackListAdapter.PackEntityDiff()) {

    class PackEntityDiff : DiffUtil.ItemCallback<PackEntity>() {

        override fun areItemsTheSame(oldItem: PackEntity, newItem: PackEntity): Boolean {
            return oldItem.packname == newItem.packname
        }

        override fun areContentsTheSame(oldItem: PackEntity, newItem: PackEntity): Boolean {
            return oldItem.isSelected == oldItem.isSelected
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val context = parent.context
//        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
//        val contactView = inflater.inflate(R.layout.text_and_checkbox_item, parent, false)
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.text_and_checkbox_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PackListAdapter.ViewHolder, position: Int) {
        //Can add click listener as second argument
        holder.bind(getItem(position));
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(packEntity : PackEntity){
            itemView.checkedTextView.setText(packEntity.packname)
            itemView.checkedTextView.isChecked = packEntity.isSelected
//            checkedTextView.setText(packEntity.packname)
//            checkedTextView.isChecked = packEntity.isSelected
        }
    }
}