package com.onss.test.repository.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notes")
public class NotesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	private String notesTitle;
	@NotEmpty
	private String description;
	
	@Column(unique = true)
	@NotEmpty(message = "file should not be empty")
	private String filePath;	
	@NotEmpty
	private String subject;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	private UserEntity user;
//	@ManyToOne(cascade = CascadeType.ALL)
//	private SubjectEntity subjects ; 
	

}
