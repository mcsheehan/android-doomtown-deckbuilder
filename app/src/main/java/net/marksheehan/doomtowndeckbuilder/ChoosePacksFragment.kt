package net.marksheehan.doomtowndeckbuilder

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.choose_pack_layout.*
import kotlinx.android.synthetic.main.text_and_checkbox_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.marksheehan.doomtowndeckbuilder.adapters.TextAndBoolean
import net.marksheehan.doomtowndeckbuilder.adapters.PackListAdapter
import net.marksheehan.doomtowndeckbuilder.viewmodels.DoomtownCardsViewModel

class ChoosePacksFragment : Fragment(R.layout.choose_pack_layout) {

    lateinit var doomtownCardsViewModel : DoomtownCardsViewModel

    fun serialiseSelectedPackMapToSharedPreferences(activity : Activity){
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val map = doomtownCardsViewModel.selectedCardPacks.value
        val jsonMapString = Gson().toJson(map)

        editor.putString("selectedPacks", jsonMapString)
        editor.apply()
    }

    val checkedTextViewOnClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
        val item: TextAndBoolean = parent.getItemAtPosition(position) as TextAndBoolean
        item.checked = !item.checked
        view.checkedTextView.isChecked = item.checked

        this.doomtownCardsViewModel.selectedCardPacks.value!![item.name] = item.checked

        GlobalScope.launch{
            serialiseSelectedPackMapToSharedPreferences(activity!!)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        doomtownCardsViewModel = ViewModelProviders.of(activity!!,
                DoomtownCardsViewModel.DoomtownCardsViewModelFactory(activity!!))[DoomtownCardsViewModel::class.java]

        doomtownCardsViewModel.selectedCardPacks.observe(this, Observer { item ->  })

        val mutableMap = doomtownCardsViewModel.selectedCardPacks.value

        val fullListOfCardPacks: MutableList<TextAndBoolean> = mutableListOf()
        mutableMap?.forEach { (key, value) -> fullListOfCardPacks.add(TextAndBoolean(key, value)) }

        val packListAdapter = PackListAdapter(context!!, fullListOfCardPacks)
        pack_list.adapter = packListAdapter
        pack_list.onItemClickListener = checkedTextViewOnClickListener
    }
}