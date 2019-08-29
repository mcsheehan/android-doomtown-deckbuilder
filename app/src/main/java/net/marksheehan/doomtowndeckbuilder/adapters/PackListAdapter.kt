package net.marksheehan.doomtowndeckbuilder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_fullscreen_view.view.*
import kotlinx.android.synthetic.main.text_and_checkbox_item.view.*
import net.marksheehan.doomtowndeckbuilder.R

class NameAndChecked(var name: String, var checked: Boolean) {
}

class PackListAdapter(context : Context, data : List<NameAndChecked>) : ArrayAdapter<NameAndChecked>(context, R.layout.text_and_checkbox_item, data){

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

        val textBox = returnView.text_view_box
        val checkBox = returnView.check_box

        textBox.text = item.name
        checkBox.isChecked = item.checked

        return returnView
    }
}