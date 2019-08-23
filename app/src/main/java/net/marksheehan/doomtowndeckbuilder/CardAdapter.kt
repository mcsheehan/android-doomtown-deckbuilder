package net.marksheehan.doomtowndeckbuilder

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation

import com.squareup.picasso.Picasso
import net.marksheehan.doomtowndeckbuilder.datamodel.CardModel

class CardAdapter(private val mItemList: List<CardModel>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardImage: ImageView

        val onItemClicked = OnClickListener { view: View ->
            val currentCard = mItemList.get(adapterPosition)

            val bundle = Bundle()
            bundle.putParcelable(null, currentCard)
            Navigation.findNavController(view).navigate(R.id.action_cardViewerFragment_to_individualCardViewer, bundle)
        }

        init {
            cardImage = itemView.findViewById(R.id.cardImage)
            itemView.setOnClickListener(onItemClicked)
        }
    }

    fun getClickListenerForCardPosition(position: Int) : OnClickListener{

        val clickListener = OnClickListener() {view: View  ->
            val currentCard = mItemList.get(position)
            val bundle = Bundle()
            bundle.putParcelable(null, currentCard)
            Navigation.findNavController(view).navigate(R.id.action_cardViewerFragment_to_individualCardViewer, bundle)
        }

        return clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentCard = mItemList[position]

        val fullImagePath = "https://dtdb.co" + currentCard.imagesrc!!
        Picasso.get().load(fullImagePath).placeholder(R.drawable.card_back).error(R.drawable.card_back).into(holder.cardImage)
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }
}
