package com.onss.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onss.test.repository.entity.NotesEntity;
@Repository
public interface NotesRepository extends JpaRepository<NotesEntity, Integer> {
//@Query("SELECT COUNT(n) FROM notes n WHERE n.subject = :subject")
	  @Query("SELECT COUNT(n) FROM NotesEntity n WHERE n.user.email = :email")
	  Long countNotesByUsersEmail(@Param("email") String email);

	void deleteByFilePath(String filePath);
	  
//	  @Query("SELECT COUNT(DISTINCT n.subject) FROM UserEntity u JOIN u.notes n WHERE u.id = :userId")
//	  Long countDistinctSubjectsByUserId(@Param("userId") Long userId);	 
}

