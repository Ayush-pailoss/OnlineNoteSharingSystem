package com.onss.test.controller.request;

import lombok.Data;

@Data
public class ForgetPasswordRequest {
	private String email;
	private long mobileNo;
	private String newPassword;
	private String confirmPassword;
}
