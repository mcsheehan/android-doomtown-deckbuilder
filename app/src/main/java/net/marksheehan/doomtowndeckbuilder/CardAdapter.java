package net.marksheehan.doomtowndeckbuilder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mark.doomtowndeckbuilder.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder>
{
    public CardAdapter(List<CardModel> list)
    {
        mItemList = list;
    }

    private List<CardModel> mItemList;

    public class CardViewHolder extends RecyclerView.ViewHolder
    {
        public TextView cost;
        public TextView cardName;
        public ImageView cardImage;

        public CardViewHolder(View itemView)
        {
            super(itemView);
            cardName = itemView.findViewById(R.id.cardName);
            cost = itemView.findViewById(R.id.cost);
            cardImage = itemView.findViewById(R.id.cardImage);
        }
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        CardViewHolder viewHolder = new CardViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position)
    {
        final CardModel currentCard = mItemList.get(position);

        String cost = "" + currentCard.cost;

        holder.cardName.setText(currentCard.title);
        holder.cost.setText(cost);

        String fullImagePath = "https://dtdb.co" + currentCard.imagesrc;
        Picasso.get().load(fullImagePath).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_foreground).into(holder.cardImage);
    }

    @Override
    public int getItemCount()
    {
        return mItemList.size();
    }
}
