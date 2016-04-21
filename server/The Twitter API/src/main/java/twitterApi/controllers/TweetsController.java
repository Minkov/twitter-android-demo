package twitterApi.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import twitterApi.responses.AuthorResponse;
import twitterApi.responses.ResponseWithResult;
import twitterApi.responses.TweetDetailsResponse;
import twitterApi.responses.TweetResponse;

@RestController
@RequestMapping("/api")
public class TweetsController {
	
	@RequestMapping(value = "/tweets")
	public ResponseWithResult<List<TweetResponse>> getAllTweets(){
		List<TweetResponse> tweets = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
					.map(index -> new TweetResponse(index,  String.format("%d. Tweet", index)))					
					.collect(Collectors.toList());
		
		return new ResponseWithResult<>(tweets);
	}
	
	
	@RequestMapping(value="/tweets/{tweetId}")
	public ResponseWithResult<TweetDetailsResponse> getTweetDetails(@PathVariable(value="tweetId") int id){
		AuthorResponse author = new AuthorResponse(1, "Doncho Minkov");
		
		String text = String.format("This is a sample text for tweet with id %d", id);
		TweetDetailsResponse tweetDetails = new TweetDetailsResponse(id, text, 	author);
		return new ResponseWithResult<>(tweetDetails);		
	}
}
