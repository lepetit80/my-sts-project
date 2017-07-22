package io.spring4mvc.spring4mvc.profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import io.spring4mvc.spring4mvc.date.PastLocalDate;
import lombok.Data;

@Data
public class ProfileForm {
	
	@Size(min = 2)
    private String twitterHandle;
	
	@Email
	@NotEmpty
    private String email;
	
	@NotNull
	@PastLocalDate
    private LocalDate birthDate;
	
	@NotEmpty
    private List<String> tastes = new ArrayList<>();
}