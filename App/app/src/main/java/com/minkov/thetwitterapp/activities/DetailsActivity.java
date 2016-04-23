package com.minkov.thetwitterapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.minkov.thetwitterapp.R;
import com.minkov.thetwitterapp.dataServices.TweetsDataService;
import com.minkov.thetwitterapp.models.Tweet;

import java.io.IOException;

public class DetailsActivity extends AppCompatActivity {

    private TweetsDataService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.service = new TweetsDataService();

        Intent intent = this.getIntent();

        Tweet tweet = (Tweet) intent.getSerializableExtra("tweetDetails");

        TextView textView = (TextView) this.findViewById(R.id.textView_text);
        textView.setText(tweet.getText());

        ((TextView) this.findViewById(R.id.textView_id)).setText(tweet.getId() + "");

        Tweet tweetDetails = null;
        try {
            tweetDetails = this.service.getTweetById(tweet.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (tweetDetails == null) {
            Toast.makeText(this, "Wrong tweet id", Toast.LENGTH_SHORT)
                    .show();
            this.finish();
            return;
        }

        TextView textViewAuthor = (TextView) this.findViewById(R.id.textView_author);
        textViewAuthor.setText(tweetDetails.getAuthor());
    }
}
