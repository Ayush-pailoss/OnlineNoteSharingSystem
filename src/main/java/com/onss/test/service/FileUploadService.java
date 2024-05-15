package com.onss.test.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.onss.test.controller.request.NotesSavingRequest;
//import com.onss.test.controller.request.SubjectRequest;
import com.onss.test.controller.response.NotesResponseEntity;
import com.onss.test.repository.NotesRepository;
//import com.onss.test.repository.SubjectRepository;
import com.onss.test.repository.UserRepository;
import com.onss.test.repository.entity.NotesEntity;
//import com.onss.test.repository.entity.SubjectEntity;
import com.onss.test.repository.entity.UserEntity;

import jakarta.transaction.Transactional;

@Service
public class FileUploadService {
	@Value("${dynamic.directory.path}")
	private String UPLOAD_DIR;
	@Autowired
	private NotesRepository notesRepository;
	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private SubjectRepository subjectRepository;

	public NotesResponseEntity uploadFile(String email, MultipartFile multipartFile,
			NotesSavingRequest notesSavingRequest) {
		UserEntity userEntity = userRepository.findByEmail(email);
		String savedFilepath = saveFile(multipartFile);
		if (savedFilepath != null) {
			NotesEntity notesEntity = NotesEntity.builder().notesTitle(notesSavingRequest.getNotesTitle())
					.description(notesSavingRequest.getDescription())
					.filePath(savedFilepath)
					.subject(notesSavingRequest.getSubject())
					.user(userEntity)
					.build();
			List<NotesEntity> list = new ArrayList<>();
			list.add(notesEntity);
			userEntity.setNotes(list);
			notesRepository.save(notesEntity);

//			SubjectEntity subject = SubjectEntity.builder().subject(subjectRequest.getSubject()).build();
//			userEntity.setSubject(subject);
//			subjectRepository.save(subject);
			userRepository.save(userEntity);
			return new NotesResponseEntity("file saved sucessfully");
		} else {
			return new NotesResponseEntity("failed to  save");
		}
	}

// SAVING FILE IN LOCAL DIRECTORY LOGIC
	private String saveFile(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDirPath = UPLOAD_DIR;
		String filePath = uploadDirPath + File.separator + filename;
		Path path = Paths.get(uploadDirPath);
		
			try {
				if (!Files.exists(path)) {
					Files.createDirectories(path);
				}	
				Path targetPath = path.resolve(filename);
				
				Files.write(targetPath, file.getBytes());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		return filePath;
	}

//		File uploadDir = new File(uploadDirPath);
//		if (!uploadDir.exists()) {
//		uploadDir.mkdirs();
//	}
//		String filePath = uploadDirPath + File.separator + filename;	   
//		File newFile = new File(filePath);
//		if (!newFile.exists()) {
//			try {
//				newFile.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		try {
//
//			FileOutputStream fileOutputStream = new FileOutputStream(newFile);
//			fileOutputStream.write(file.getBytes());
//			fileOutputStream.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return filePath;
//	}

//	FILE DOWNLOAD SERVICE
	public Path downloadFile(String filename) {
		String filePath = UPLOAD_DIR + File.separator + filename;
		Path path = Paths.get(filePath);
//		File file = new File(filePath);
//		Path path = Paths.get("UPLOAD_DIR").resolve("filename");
        return path;
  
	}

// TO COUNT NUMBER OF SUBJECTS
	public Long countSubjectByUsersEmail(String email) {
		return userRepository.countDistinctSubjectsByUsersEmail(email);
	}

//	TO COUNT NUMBER OF NOTES
	public Long countNotesBySubject(String email) {
		return notesRepository.countNotesByUsersEmail(email);
	}

//REMOVE FILE SERVICE
	@Transactional
	public NotesResponseEntity removeFile(String fileName) {
		String filePath = UPLOAD_DIR + File.separator + fileName;
		        deleteFile(filePath);
		        // Delete the entry from the database
		        notesRepository.deleteByFilePath(filePath);
				return new NotesResponseEntity(filePath);
		    }
		    private void deleteFile(String filePath) {
		        File file = new File(filePath);
		        if (file.exists()) {
		            file.delete();
		        }
		    }	
	}
