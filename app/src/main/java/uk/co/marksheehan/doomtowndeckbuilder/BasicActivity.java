package uk.co.marksheehan.doomtowndeckbuilder;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.mark.doomtowndeckbuilder.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasicActivity extends AppCompatActivity
{
    @BindView(R.id.recycler)
    RecyclerView cardsView;

    CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.fab)
    public void fabClick()
    {
        Snackbar.make(BasicActivity.this.findViewById(R.id.fab), "Downloading card data", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        DoomtownDbAccess doomtownDbAccess = new DoomtownDbAccess();
        doomtownDbAccess.sendServerRequestCardList(cardResult);
    }

    DoomtownDbAccess.CallbackOnCards cardResult = new DoomtownDbAccess.CallbackOnCards()
    {
        @Override
        public void success(List<CardModel> result)
        {
            LinearLayoutManager layoutManager = new LinearLayoutManager(BasicActivity.this);
            cardsView.setLayoutManager(layoutManager);

            cardAdapter = new CardAdapter(result);
            cardsView.setAdapter(cardAdapter);

            cardAdapter.notifyDataSetChanged();
        }

        @Override
        public void failure()
        {
            Snackbar.make(BasicActivity.this.findViewById(R.id.fab), "Failed to download card data", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    };
}