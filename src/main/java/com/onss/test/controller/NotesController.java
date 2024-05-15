  package com.onss.test.controller;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onss.test.controller.request.NotesSavingRequest;
import com.onss.test.controller.response.NotesResponseEntity;
import com.onss.test.service.FileUploadService;
@RestController
public class NotesController {

	@Value("${dynamic.directory.path}")
	private String UPLOAD_DIR;

	@Autowired
	private FileUploadService fileUploadService;

	@PostMapping("/fileUpload/{email}")
	public NotesResponseEntity fileUpload( @RequestParam("files")  List<MultipartFile> files 
			                              , NotesSavingRequest notesSavingRequest, @PathVariable String email ) {
		
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				return new NotesResponseEntity("Please select a file to upload");
			}
			if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
				return new NotesResponseEntity("the file should be a pdf file"); 
			}
			try {
				fileUploadService.uploadFile(email,file,notesSavingRequest);
			} catch (Exception e) {
				e.printStackTrace();
				return new NotesResponseEntity("internal server error");
			}

		}
		return new NotesResponseEntity("file uploaded successfully");
	}

@GetMapping("/download")
public Path getfilepath(@RequestParam("filename") String filename){
	 return fileUploadService.downloadFile(filename) ;
}

@GetMapping("/subjectCount/{email}")
public ResponseEntity<Long> getDistinctSubjectsCountByUserEmail(@PathVariable String email) {
    Long count = fileUploadService.countSubjectByUsersEmail( email);
    return ResponseEntity.ok(count);
}

@GetMapping("/notesCount/{email}")
public ResponseEntity<Long> getNotesCountBySubject(@PathVariable String email) {
    Long count = fileUploadService.countNotesBySubject(email);
    return ResponseEntity.ok(count);
}


@DeleteMapping("/removefile")
public NotesResponseEntity deleteFile(@RequestParam("filename") String filename) {
	return fileUploadService.removeFile(filename);
}
//}
}
  
