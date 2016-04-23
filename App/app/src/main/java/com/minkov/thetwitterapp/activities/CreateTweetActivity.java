package com.minkov.thetwitterapp.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.minkov.thetwitterapp.R;
import com.minkov.thetwitterapp.dataServices.TweetsDataService;
import com.minkov.thetwitterapp.models.Tweet;

import java.io.IOException;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class CreateTweetActivity extends AppCompatActivity {

    public static final String NEW_TWEET_INTENT_KEY = "newTweet";

    private TweetsDataService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tweet);

        this.service = new TweetsDataService();

        Button btn = (Button) this.findViewById(R.id.button_post);

        btn.setOnClickListener((view) -> {
            String text = ((EditText) this.findViewById(R.id.editText_text)).getText().toString();
            String author = ((EditText) this.findViewById(R.id.editText_author)).getText().toString();

//            ProgressDialog dialog =
//                    ProgressDialog.show(this, "Loading...", "Second string");

            ACProgressFlower dialog = new ACProgressFlower.Builder(this)
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE)
                    .text("Tweeting")
                    .fadeColor(Color.DKGRAY)
                    .build();
            dialog.show();

            this.service.createTweetAsync(text, author, (tweet) -> {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(NEW_TWEET_INTENT_KEY, tweet);

                this.setResult(Activity.RESULT_OK, resultIntent);

                this.runOnUiThread(() -> {
                    dialog.hide();
                    Toast.makeText(this, "Tweet posted", Toast.LENGTH_SHORT)
                            .show();
                    this.finish();
                });

                return null;
            });
        });
    }

}
