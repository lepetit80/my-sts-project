package io.spring4mvc.spring4mvc.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class User {
	private String twitterHandle;
	private String email;
	private LocalDate birthDate;
	private List<String> tastes = new ArrayList<>();
}
