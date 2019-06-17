package net.marksheehan.doomtowndeckbuilder;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.example.mark.doomtowndeckbuilder.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.recycler)
    RecyclerView cardsView;

    CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.fab)
    public void fabClick()
    {
        Snackbar.make(MainActivity.this.findViewById(R.id.fab), R.string.downloading_card_data, Snackbar.LENGTH_LONG).show();
        DoomtownDbAccess doomtownDbAccess = new DoomtownDbAccess();
        doomtownDbAccess.sendServerRequestCardList(cardResult);
    }

    DoomtownDbAccess.CardQueryCallback cardResult = new DoomtownDbAccess.CardQueryCallback()
    {
        @Override
        public void success(List<CardModel> result)
        {
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            cardsView.setLayoutManager(layoutManager);

            cardAdapter = new CardAdapter(result);
            cardsView.setAdapter(cardAdapter);
            cardAdapter.notifyDataSetChanged();
        }

        @Override
        public void failure()
        {
            Snackbar.make(MainActivity.this.findViewById(R.id.fab), R.string.internet_connection_failure, Snackbar.LENGTH_LONG).show();
        }
    };
}