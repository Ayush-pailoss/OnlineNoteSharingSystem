package com.onss.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.onss.test.controller.request.ForgetPasswordRequest;
import com.onss.test.controller.request.UserLoginRequest;
import com.onss.test.controller.request.UserRegistrationRequest;
import com.onss.test.controller.response.UsersResponseEntity;
import com.onss.test.repository.UserRepository;
import com.onss.test.repository.entity.UserEntity;

@Service
public class UserService {
  private 	Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserRepository repo;

//	USER SIGN UP SERVICE
	public UsersResponseEntity saveUser(UserRegistrationRequest userRegistrationRequest) {
		UserEntity userEntity = repo.findByEmailAndPhoneNo(userRegistrationRequest.getEmail(),userRegistrationRequest.getPhoneNo());
		if (userEntity.getEmail()  == null) {
			UserEntity user2 = UserEntity.builder()
							.fullName(userRegistrationRequest.getFullName())
							.email(userRegistrationRequest.getEmail())
							.phoneNo(userRegistrationRequest.getPhoneNo())
							.password(userRegistrationRequest.getPassword())
							.build();
			              repo.save(user2);
			              logger.info("user is registered successfully");
			              return new UsersResponseEntity("user registered successfully");
		} else {
			logger.info("user already exist's");
			return new UsersResponseEntity("user already exist's");
		}
	}

// USER SIGN IN SERVICE	
	public ResponseEntity<String> login(UserLoginRequest loginRequest) {
		UserEntity entity = repo.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
		if (entity == null) {
			logger.warn("email id or password is  invalid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid email id or password");
		} else {
			logger.info("user has successfully logged in ");
			return ResponseEntity.status(HttpStatus.FOUND).body(" logged in successfully");
		}
	}

// FORGET PASSWORD SERVICE
	public ResponseEntity<String> changePassword(ForgetPasswordRequest forgetPasswordRequest) {
		UserEntity userEntity = repo.findByEmailAndPhoneNo(forgetPasswordRequest.getEmail(),
				forgetPasswordRequest.getMobileNo());
		if (userEntity == null) {
			logger.warn("user does not exist");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("user does not exist");
		}
		if (forgetPasswordRequest.getNewPassword().equals(forgetPasswordRequest.getConfirmPassword())) {
			userEntity.setPassword(forgetPasswordRequest.getConfirmPassword());
			repo.save(userEntity);
			logger.info("password is successfully changed by user");
			return ResponseEntity.ok("password changed successfully");
		}

		else {
			logger.warn("incorrect password is entered during password confirmation");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("new pass and confirm pass does not match");
			
		}

	}

//	public User login(String email,String password) throws Exception {
//	     User user= repo.findByEmail(email);
//		if(user==null|| !user.getPassword().equals(repo.findByPassword(password))) {
//			throw new Exception("invalid user id or password");
//		}
//		else { 
//		return user;
//		}
//		}

////	USER LOGIN SERVICE
//	public boolean login(String email, String Password) {
//		return repo.findByEmailAndPassword(email, Password) != null;

//	USER CHANGE PASSWORD SERVICE
//	public Boolean changePassword(String email, Long phoneNo, String newPassword, String confirmNewPassword) {
//		UserEntity user = repo.findByEmailAndPhoneNo(email, phoneNo);
//		if(user!=null) {
//			
//		if (newPassword.equals(confirmNewPassword)) {
//			user.setPassword(newPassword);
//			repo.save(user);
//		}}
//		else {
//			return false;
//		}
//		return true;
//
//	}

}
