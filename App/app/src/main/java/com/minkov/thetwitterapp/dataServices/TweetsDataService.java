package com.minkov.thetwitterapp.dataServices;

import android.os.AsyncTask;

import com.annimon.stream.function.Function;
import com.minkov.thetwitterapp.models.Tweet;
import com.minkov.thetwitterapp.responses.TweetDetailsResponse;
import com.minkov.thetwitterapp.responses.TweetsResponse;
import com.minkov.thetwitterapp.utils.HttpRequester;

import java.io.IOException;
import java.util.List;

/**
 * Created by minkov on 4/23/16.
 */
public class TweetsDataService {
    String url = "http://192.168.199.27:3001/api/tweets";

    public List<Tweet> getAllTweets() throws IOException {
        HttpRequester requester = HttpRequester.getHttpRequester();
        TweetsResponse tweetsResponse = requester.get(this.url, TweetsResponse.class);
        return tweetsResponse.getResult();
    }

    public void getAllTweetsAsync(Function<List<Tweet>,Void> finishedCallback){
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                HttpRequester requester = HttpRequester.getHttpRequester();
                try {
                    TweetsResponse tweetsResponse = requester.get(url, TweetsResponse.class);
                    List<Tweet> tweets = tweetsResponse.getResult();

                    finishedCallback.apply(tweets);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task.execute();
    }

    public Tweet getTweetById(int id) throws IOException {
        HttpRequester requester = HttpRequester.getHttpRequester();
        String url = String.format("%s/%d", this.url, id);
        TweetDetailsResponse tweetsResponse = requester.get(url, TweetDetailsResponse.class);
        return tweetsResponse.getResult();
    }

    public Tweet createTweet(String text, String author) throws IOException {
        HttpRequester requester = HttpRequester.getHttpRequester();
        Tweet tweet = new Tweet(-1, text, author);
        TweetDetailsResponse tweetResponse = requester.post(this.url, tweet, TweetDetailsResponse.class);
        return tweetResponse.getResult();
    }

    public void createTweetAsync(String text, String author, Function<Tweet, Void> finishedCallback){
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                HttpRequester requester = HttpRequester.getHttpRequester();
                Tweet tweet = new Tweet(-1, text, author);
                TweetDetailsResponse tweetResponse = null;
                try {
                    tweetResponse = requester.post(url, tweet, TweetDetailsResponse.class);
                    Tweet newTweet = tweetResponse.getResult();
                    finishedCallback.apply(newTweet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        task.execute();
    }
}
