//package com.onss.test.repository.entity;
//
//import java.util.List;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.NotEmpty;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "subject")
//public class SubjectEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//	@NotEmpty
//	private String subject;
//	@OneToMany(cascade = CascadeType.ALL)
//	private List<NotesEntity> notes ; 
//	}
