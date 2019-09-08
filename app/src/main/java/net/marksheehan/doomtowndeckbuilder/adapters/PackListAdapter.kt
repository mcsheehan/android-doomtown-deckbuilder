package net.marksheehan.doomtowndeckbuilder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.text_and_checkbox_item.view.*
import net.marksheehan.doomtowndeckbuilder.R
import net.marksheehan.doomtowndeckbuilder.database.PackEntity

class TextAndBoolean(var name: String, var checked: Boolean) {
}

class PackListAdapter(context : Context, data : List<PackEntity>) : ArrayAdapter<PackEntity>(context, R.layout.text_and_checkbox_item, data){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val item = getItem(position)!!
        val returnView : View

        if (convertView == null) {
            val inflater: LayoutInflater = LayoutInflater.from(context)
            returnView = inflater.inflate(R.layout.text_and_checkbox_item, parent, false)
        }
        else
        {
            returnView = convertView
        }

        val checkedTextView = returnView.checkedTextView

        checkedTextView.text = item.packname
        checkedTextView.isChecked = item.isSelected

        return returnView
    }
}