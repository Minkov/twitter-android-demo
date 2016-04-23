package com.minkov.thetwitterapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.annimon.stream.Stream;
import com.minkov.thetwitterapp.R;
import com.minkov.thetwitterapp.dataServices.TweetsDataService;
import com.minkov.thetwitterapp.models.Tweet;
import com.minkov.thetwitterapp.utils.TweetArrayAdapter;

import java.io.IOException;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class MainActivity extends AppCompatActivity {

    public static final int CREATE_ACTIVITY_REQUEST_CODE = 1;
    private int lastId = 10;
    private TweetsDataService service;
    private TweetArrayAdapter adapter;

    private int getNextId() {
        return ++lastId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);

        ListView listView = (ListView) this.findViewById(R.id.listView_messages);

        this.adapter =
                new TweetArrayAdapter(this);

        this.service = new TweetsDataService();

        ACProgressFlower dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Loading tweets")
                .fadeColor(Color.DKGRAY)
                .build();
        dialog.show();

        this.service.getAllTweetsAsync(tweets -> {
            Stream.of(tweets)
                    .forEach(adapter::add);
            this.runOnUiThread(() -> {
                dialog.hide();
                this.adapter.notifyDataSetChanged();
            });
            return null;
        });

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Tweet tweet = (Tweet) adapter.getItem(position);

            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("tweetDetails", tweet);
            this.startActivity(intent);
        });

        FloatingActionButton button = (FloatingActionButton)
                this.findViewById(R.id.fab);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateTweetActivity.class);
            this.startActivityForResult(intent, CREATE_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tweet newTweet =
                (Tweet) data
                        .getSerializableExtra(CreateTweetActivity.NEW_TWEET_INTENT_KEY);
        this.adapter.add(newTweet);
        this.adapter.notifyDataSetChanged();
    }
}
