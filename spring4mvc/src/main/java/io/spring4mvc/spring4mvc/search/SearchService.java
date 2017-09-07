package io.spring4mvc.spring4mvc.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

	@Autowired
	private Twitter twitter;
	
//	public SearchService(Twitter twitter) {
//		this.twitter = twitter;
//	}
	
	public List<Tweet> search(String searchType, List<String> keywords) {
		final List<SearchParameters> searchs = keywords.stream()
			.map(taste -> createSearchParam(searchType, taste))
			.collect(Collectors.toList());
		
		final List<Tweet> tweets = searchs.stream()
				.map(param -> twitter.searchOperations().search(param))
				.flatMap(searchResults -> searchResults.getTweets().stream())
				.collect(Collectors.toList());
		
		return tweets;
	}

	
	private SearchParameters.ResultType getResultType(String searchType) {
		for (SearchParameters.ResultType knownType: SearchParameters.ResultType.values()) {
			if (knownType.name().equalsIgnoreCase(searchType)) {
				return knownType;
			}
		}
		
		return SearchParameters.ResultType.RECENT;
	}
	
	private SearchParameters createSearchParam(String searchType, String taste) {
		SearchParameters.ResultType resultType = getResultType(searchType);
		SearchParameters searchParameters = new SearchParameters(taste);
		searchParameters.resultType(resultType);
		searchParameters.count(3);
		return searchParameters;
	}
	
}
