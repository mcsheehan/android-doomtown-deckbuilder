package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import android.os.Parcelable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation

import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class CardAdapter(private val mItemList: List<CardModel>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardImage: ImageView

        val onItemClicked = OnClickListener { view: View ->
            val currentCard = mItemList.get(adapterPosition)
            val title = currentCard.title
            Snackbar.make(view, "$title", Snackbar.LENGTH_LONG).show()
            var bundle = Bundle()

            bundle.putParcelable(null, currentCard)
            Navigation.findNavController(view).navigate(R.id.action_cardViewerFragment_to_individualCardViewer, bundle)
        }

        init {
            cardImage = itemView.findViewById(R.id.cardImage)
            itemView.setOnClickListener(onItemClicked)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentCard = mItemList[position]

        val fullImagePath = "https://dtdb.co" + currentCard.imagesrc!!
        Picasso.get().load(fullImagePath).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(holder.cardImage)
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }
}
