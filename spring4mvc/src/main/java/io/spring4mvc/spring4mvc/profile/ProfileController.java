package io.spring4mvc.spring4mvc.profile;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.spring4mvc.spring4mvc.date.KRLocalDateFormatter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProfileController {
	
	private UserProfileSession userProfileSession;
	
	@Autowired
	public ProfileController(UserProfileSession userProfileSession) {
		this.userProfileSession = userProfileSession;
	}
	
	@ModelAttribute
	public ProfileForm getProfileForm() {
		return userProfileSession.toForm();
	}
	
	@ModelAttribute("dateFormat")
	public String localeFormat(Locale locale) {
		return KRLocalDateFormatter.getPattern(locale);
	}
	
	@GetMapping("/profile")
	public String displayProfile(ProfileForm profileForm) {
		//profileForm.setEmail("default@email.com");
		return "profile/profilePage";
	}
	
	@RequestMapping(value="/profile", params = {"save"}, method=RequestMethod.POST)
	public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "profile/profilePage";
		}
		
		log.debug("ProfileForm: {}", profileForm.toString());
		
		userProfileSession.saveForm(profileForm);
		
		return "redirect:/search/mixed;keywords=" + String.join(",", profileForm.getTastes());
	}
	
    @RequestMapping(value="/profile", params={"addTaste"})
	public String addRow(ProfileForm profileForm) {
		profileForm.getTastes().add(null);
		return "profile/profilePage";
	}
	
    @RequestMapping(value="/profile", params={"removeTaste"})
	public String removeRow(ProfileForm profileForm, HttpServletRequest request) {
		Integer rowId = Integer.valueOf(request.getParameter("removeTaste"));
		profileForm.getTastes().remove(rowId.intValue());
		return "profile/profilePage";
	}
	
}
