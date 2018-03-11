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
    RecyclerView mRecyclerView;

    CardAdapter adapter;

    DoomtownDbAccess.CallbackOnCards mCardResult = new DoomtownDbAccess.CallbackOnCards()
    {
        @Override
        public void success(List<CardModel> result)
        {
            Snackbar.make(BasicActivity.this.findViewById(R.id.fab), "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            adapter = new CardAdapter(result);
            LinearLayoutManager layoutManager = new LinearLayoutManager(BasicActivity.this);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void failure()
        {

        }
    };

    @OnClick(R.id.fab)
    public void fabClick()
    {
        DoomtownDbAccess doomtownDbAccess = new DoomtownDbAccess();
        doomtownDbAccess.sendServerRequestCardList(mCardResult);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}