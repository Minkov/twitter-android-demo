package com.minkov.twitterapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.minkov.twitterapp.R;
import com.minkov.twitterapp.dataServices.TweetsDataService;
import com.minkov.twitterapp.models.Tweet;
import com.minkov.twitterapp.utils.TweetTextArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TweetsActivity extends AppCompatActivity {

    private TweetsDataService service;
    private ArrayAdapter<Tweet> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ListView tweetsListView = (ListView) this.findViewById(R.id.listview_tweets);

        this.adapter = new TweetTextArrayAdapter(this, new ArrayList<>());

        tweetsListView.setAdapter(adapter);
        tweetsListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, TweetDetailsActivity.class);
            Tweet tweet = adapter.getItem(position);
            intent.putExtra("tweet", tweet);

            startActivity(intent);
        });

        this.service = new TweetsDataService();

        service.setGetAllFinishedCallback((value) -> {
            this.updateAllTweets(value);
            return null;
        });

        service.getGetAll().execute();
    }

    @NonNull
    private void updateAllTweets(List<Tweet> tweets) {
        this.runOnUiThread(() -> {
            Stream.of(tweets)
                .forEach(this.adapter::add);
            adapter.notifyDataSetChanged();
        });
    }
}
