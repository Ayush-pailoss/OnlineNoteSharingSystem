package com.onss.test.repository.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")

public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String fullName;
	
	
	@Column(unique = true)
	private long phoneNo;
	
	@Column(unique = true)
	@NotEmpty
	private String email;
	
    @NotEmpty
	private String password;
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<NotesEntity> notes;
    
//    @OneToOne(cascade = CascadeType.ALL)
//    private SubjectEntity subject;
}
