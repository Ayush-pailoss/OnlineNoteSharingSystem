package com.onss.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onss.test.repository.entity.UserEntity;
@Repository 
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	 public UserEntity findByEmailAndPassword(String email,String password);
	 public UserEntity findByEmail(String email);
	 public UserEntity findByPhoneNo(Long phoneNo);
	 public UserEntity findByPassword(String password);
	 public UserEntity findByEmailAndPhoneNo(String email,Long phoneNo);
	 
	  @Query("SELECT COUNT(DISTINCT n.subject) FROM UserEntity u JOIN u.notes n WHERE u.email = :email")
	  Long countDistinctSubjectsByUsersEmail(@Param("email") String email);	 
}
