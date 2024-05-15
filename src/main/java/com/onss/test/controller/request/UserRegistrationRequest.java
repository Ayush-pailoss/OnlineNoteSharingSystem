package com.onss.test.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationRequest {
	 private String fullName;
		private long phoneNo;
		private String email;
		private String password;
}
