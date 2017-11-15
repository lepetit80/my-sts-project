package io.spring4mvc.spring4mvc.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthenticationSignInAdapter implements SignInAdapter {

	public static void authenticte(Connection<?> connection) {
		UserProfile userProfile = connection.fetchUserProfile();
		String userName = userProfile.getUsername();
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, null, null);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		log.debug("user {} {} connected.", userProfile.getFirstName(), userProfile.getLastName());
	}
	
	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		authenticte(connection);
		return null;
	}

}
