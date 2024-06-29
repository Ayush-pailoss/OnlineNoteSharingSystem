package com.onss.test.controller;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.onss.test.controller.request.NotesSavingRequest;
import com.onss.test.controller.response.NotesResponseEntity;
import com.onss.test.repository.NotesRepository;
import com.onss.test.service.FileUploadService;
@RestController
public class NotesController {
	
	private final Logger logInfo = LoggerFactory.getLogger(NotesController.class);

	@Value("${dynamic.directory.path}")
	private String UPLOAD_DIR;

	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private NotesRepository notesRepository;

	@PostMapping("/fileUpload/{email}")
	public NotesResponseEntity fileUpload( @RequestParam("files")  List<MultipartFile> files 
			                              , NotesSavingRequest notesSavingRequest, @PathVariable String email ) {
		
		for (MultipartFile file : files) {
			
			String filename = StringUtils.cleanPath(file.getOriginalFilename());
			String uploadDirPath = UPLOAD_DIR;
			String filePath = uploadDirPath + File.separator + filename;
			
			if(notesRepository.findByFilePath(filePath)!=null) {
				return new NotesResponseEntity("file is already been uploaded");
			}
			
			if (file.isEmpty()) {
				logInfo.info("the is no file selected");
				return new NotesResponseEntity("Please select a file to upload");
			}
			if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
				logInfo.info("the file should be a pdf file");
				return new NotesResponseEntity("the file should be a pdf file"); 
			}
			
			try {
				fileUploadService.uploadFile(email,file,notesSavingRequest);
			} catch (Exception e) {
				e.printStackTrace();
				logInfo.info("something went wrong while uploading the file in directory ");
				return new NotesResponseEntity("internal server error");
			}

		}
		logInfo.info(" file upload successfully ");
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
  
