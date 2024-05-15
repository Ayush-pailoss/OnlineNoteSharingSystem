package com.onss.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onss.test.controller.request.ForgetPasswordRequest;
import com.onss.test.controller.request.UserLoginRequest;
import com.onss.test.controller.request.UserRegistrationRequest;
import com.onss.test.controller.response.UsersResponseEntity;
import com.onss.test.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

// USER	SIGN UP CONTROLLER
	@PostMapping("/signup")
	public UsersResponseEntity savingUserData( @Valid @RequestBody UserRegistrationRequest userRequest) {	
		return userService.saveUser(userRequest); 
	}
	
	
//	USER SIGN IN CONTROLLER
	@PostMapping("/signin")
	public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginRequest loginRequest ) {
		 userService.login(loginRequest) ;
		 return userService.login(loginRequest);
	}
	
//   PASSWORD FORGET COTROLLER
	 @PostMapping("/forgetPassword")
	 public ResponseEntity<String> forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest){
		 return userService.changePassword(forgetPasswordRequest) ;
		 
	 }
	
	
	
	
	


// USER LOGIN CONTROLLER
//	@PostMapping("/{email}/{password}")
//	public ResponseEntity<String> loginUser(@Valid @PathVariable String email, @PathVariable String password) {
//		try {
//			boolean result = userService.login(email, password);
//			if (result) {
//				return ResponseEntity.ok().body("successfully loged in");
//			} else {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found!!!");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
//		}
//	}

//  FORGET PASSWORD CONTROLLER
//	@PostMapping("/{email}/{phoneNo}/{newPass}/{confirmPass}")	
//public ResponseEntity<String> forgetPass(@Valid @PathVariable String email,
//		                                        @PathVariable Long phoneNo,
//		                                        @PathVariable String newPass,
//		                                        @PathVariable String confirmPass){
//		try {
//			if(!newPass.equals(confirmPass)) {
//				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("new pass and confirm pass does not match!!!");
//			}
//			 boolean result = userService.changePassword(email, phoneNo, newPass, confirmPass);
//			 if(result) {
//				return ResponseEntity.ok().body("pass changed successfully!!!");
//			 }
//			 else {
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
//			 }
//					 
//										 
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user does not exist");
//		}
//}
}
