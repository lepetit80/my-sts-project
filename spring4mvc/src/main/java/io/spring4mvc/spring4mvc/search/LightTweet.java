package io.spring4mvc.spring4mvc.search;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;

import lombok.Data;

@Data
public class LightTweet {
	private String profileImageUrl;
	private String user;
	private String text;
	private LocalDateTime date;
	private String lang;
	private Integer retweetCount;
	
	public LightTweet() {
	}

	public LightTweet(String text) {
		this.text = text;
	}
	
	public static LightTweet ofTweet(Tweet tweet) {
		final LightTweet lightTweet = new LightTweet(tweet.getText());
		final Date createdAt = tweet.getCreatedAt();
		
		if (createdAt != null) {
			lightTweet.date = LocalDateTime.ofInstant(createdAt.toInstant(), ZoneId.systemDefault());
		}
		
		final TwitterProfile tweetUser = tweet.getUser();
		
		if (tweetUser != null) {
			lightTweet.user = tweetUser.getName();
			lightTweet.profileImageUrl = tweetUser.getProfileImageUrl();
		}
		
		lightTweet.lang = tweet.getLanguageCode();
		lightTweet.retweetCount = tweet.getRetweetCount();
		
		return lightTweet;
	}
	
}
