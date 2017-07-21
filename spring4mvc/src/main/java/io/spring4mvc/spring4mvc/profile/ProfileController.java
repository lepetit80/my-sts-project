package io.spring4mvc.spring4mvc.profile;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import io.spring4mvc.spring4mvc.date.KRLocalDateFormatter;

@Controller
public class ProfileController {
	
	@GetMapping("/profile")
	public String displayProfile(ProfileForm profileForm) {
		return "profile/profilePage";
	}
	
	@PostMapping("/profile")
	public String saveProfile(ProfileForm profileForm) {
		System.out.println("saved: " + profileForm.toString());
		return "redirect:/profile";
	}
	
	@ModelAttribute("dateFormat")
	public String localeFormat(Locale locale) {
		return KRLocalDateFormatter.getPattern(locale);
	}
}
