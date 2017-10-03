//package io.spring4mvc.spring4mvc.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.social.twitter.api.SearchResults;
//import org.springframework.social.twitter.api.Tweet;
//import org.springframework.social.twitter.api.Twitter;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//@Controller
//public class HelloController {
//
//	@Autowired
//	private Twitter twitter;
//
//	@GetMapping("/")
//	public String home() {
//		return "searchPage";
//	}
//
//	@PostMapping("/postSearch")
////	@RequestMapping(value = "/postSearch", method = RequestMethod.POST)
//	public String postSearch(HttpServletRequest request, RedirectAttributes redirectAttributes) {
//		String search = request.getParameter("search");
//		
//		if (search.toLowerCase().contains("struts")) {
//			redirectAttributes.addFlashAttribute("error", "Try using spring	instead!");
//			return "redirect:/";
//		}
//		
//		redirectAttributes.addAttribute("search", search);
//		return "redirect:result";
//	}
//
//	@GetMapping("/result")
//	// @ResponseBody
//	public String hello(@RequestParam(defaultValue = "springboot-webmvc") String search, Model model) {
//		SearchResults results = twitter.searchOperations().search(search);
//		// String text = results.getTweets().get(0).getText();
//		// model.addAttribute("message", "Hello " + search);
//		// model.addAttribute("message", text);
//
//		// List<String> tweets = results.getTweets()
//		// .stream()
//		// .map(Tweet::getText)
//		// .collect(Collectors.toList());
//
//		List<Tweet> tweets = results.getTweets();
//		model.addAttribute("tweets", tweets);
//		model.addAttribute("search", search);
//		return "resultPage";
//	}
//}
