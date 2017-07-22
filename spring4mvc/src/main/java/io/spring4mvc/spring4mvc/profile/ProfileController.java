package io.spring4mvc.spring4mvc.profile;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import io.spring4mvc.spring4mvc.date.KRLocalDateFormatter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProfileController {
	
	@GetMapping("/profile")
	public String displayProfile(ProfileForm profileForm) {
		//profileForm.setEmail("default@email.com");
		return "profile/profilePage";
	}
	
	@PostMapping("/profile")
	public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "profile/profilePage";
		}
		
		log.debug("ProfileForm: {}", profileForm);
		return "redirect:/profile";
	}
	
	@ModelAttribute("dateFormat")
	public String localeFormat(Locale locale) {
		return KRLocalDateFormatter.getPattern(locale);
	}
}
