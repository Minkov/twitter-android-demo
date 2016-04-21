package com.minkov.twitterapp.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.minkov.twitterapp.R;
import com.minkov.twitterapp.dataServices.TweetsDataService;
import com.minkov.twitterapp.models.Tweet;

import org.w3c.dom.Text;

public class TweetDetailsActivity extends AppCompatActivity {
    private TweetsDataService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        Tweet tweet = (Tweet) this.getIntent().getSerializableExtra("tweet");
        TextView textView =(TextView) this.findViewById(R.id.textView_text);
        textView.setText(tweet.getText());

        this.service = new TweetsDataService();
        this.service.setGetByIdFinishedCallback((tweetDetails) -> {
            this.updateTweet(tweetDetails);
            return null;
        });

        this.service.getGetById().execute(tweet.getId());
    }

    private void updateTweet(Tweet tweet) {
        int b = 5;
    }

}
