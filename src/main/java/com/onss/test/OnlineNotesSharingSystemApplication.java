package com.onss.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.onss.test.repository.UserRepository;
import com.onss.test.repository.entity.UserEntity;

@SpringBootApplication
public class OnlineNotesSharingSystemApplication implements CommandLineRunner {

	@Autowired
	UserRepository repository;
	
	public static void main(String[] args) {

		SpringApplication.run(OnlineNotesSharingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		print();
	}

	public List<UserEntity> print() {
		
	List<UserEntity> results = repository.findAll();
	
	for(UserEntity value : results) {
		System.out.println(value);
	}
	
	return results;
	
	
	

		 
		
		
		
	}

}
