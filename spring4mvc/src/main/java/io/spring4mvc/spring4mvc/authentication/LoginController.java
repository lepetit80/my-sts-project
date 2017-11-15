package io.spring4mvc.spring4mvc.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping(value="/login")
	public String authenticate() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null && auth.isAuthenticated()) {
			System.out.println("###########################################################################");
			System.out.println(auth.getPrincipal());
			System.out.println(auth.getName());
			System.out.println("###########################################################################");
	    }
		
		return "login";
	}
}
