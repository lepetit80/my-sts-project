package io.spring4mvc.spring4mvc.profile;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserProfileSession implements Serializable {
	//TARGET_CLASS CGI 프락시를 사용한다.
	//INTERFACES JDK 프락시를 생성한다.
	//NO 프락시를 생성하지 않는다.
	
	private String twitterHandle;
	private String email;
	private LocalDate birthDate;
	private List<String> tastes = new ArrayList<>();
	
	public void saveForm(ProfileForm form) {
		this.twitterHandle = form.getTwitterHandle();
		this.email = form.getEmail();
		this.birthDate = form.getBirthDate();
		this.tastes = form.getTastes();
	}
	
	public ProfileForm toForm() {
		final ProfileForm profileForm = new ProfileForm();
		profileForm.setTwitterHandle(twitterHandle);
		profileForm.setEmail(email);
		profileForm.setBirthDate(birthDate);
		profileForm.setTastes(tastes);
		return profileForm;
	}
}
