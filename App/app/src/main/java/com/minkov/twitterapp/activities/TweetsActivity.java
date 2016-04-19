package com.minkov.twitterapp.activities;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.minkov.twitterapp.R;
import com.minkov.twitterapp.dataServices.TweetsDataService;
import com.minkov.twitterapp.models.Tweet;
import com.minkov.twitterapp.utils.TweetTextArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class TweetsActivity extends AppCompatActivity {

    private TweetsDataService tweetsDataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ListView tweetsListView = (ListView) this.findViewById(R.id.listview_tweets);

        TweetTextArrayAdapter adapter = new TweetTextArrayAdapter(this, new ArrayList<Tweet>());

        tweetsListView.setAdapter(adapter);

        TweetsDataService service = new TweetsDataService(updateAllTweets(adapter));
        service.getGetAll().execute();
    }

    @NonNull
    private Function<List<Tweet>, Void> updateAllTweets(TweetTextArrayAdapter adapter) {
        return value -> {
            List<Tweet> tweets = value;
            Stream.of(tweets)
                    .forEach(adapter::add);
            this.runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
            });
            return null;
        };
    }
}
