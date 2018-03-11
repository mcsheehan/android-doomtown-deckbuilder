package uk.co.marksheehan.doomtowndeckbuilder;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mark.doomtowndeckbuilder.R;

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

        public CardViewHolder(View itemView)
        {
            super(itemView);
            cardName = itemView.findViewById(R.id.cardName);
            cost = itemView.findViewById(R.id.cost);
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
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
