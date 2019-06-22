package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.Toolbar

import com.example.mark.doomtowndeckbuilder.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_list.*

class MainActivity : AppCompatActivity() {

    internal lateinit var cardAdapter: CardAdapter

    internal var cardResult: DoomtownDbAccess.CardQueryCallback = object : DoomtownDbAccess.CardQueryCallback {

        override fun success(result: List<CardModel>?) {
            cardAdapter = CardAdapter(result!!)

            card_recycler.layoutManager = LinearLayoutManager(this@MainActivity)
            card_recycler.adapter = cardAdapter
            cardAdapter.notifyDataSetChanged()
        }

        override fun failure() {
            Snackbar.make(this@MainActivity.findViewById<View>(R.id.fab), R.string.internet_connection_failure, Snackbar.LENGTH_LONG).show()
        }
    }

    val fabClick = View.OnClickListener { view: View ->
        Snackbar.make(view, R.string.downloading_card_data, Snackbar.LENGTH_LONG).show()
        val doomtownDbAccess = DoomtownDbAccess()
        doomtownDbAccess.sendServerRequestCardList(cardResult)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        fab.setOnClickListener(fabClick)
    }
}