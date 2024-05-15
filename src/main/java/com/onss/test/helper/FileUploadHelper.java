package com.onss.test.helper;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

//@Component
//public class FileUploadHelper {
//	public final String UPLOAD_DIR = "C:\\Users\\AYUSH\\Desktop\\springbootsuit\\OnlineNotesSharingSystem\\src\\main\\resources\\static\\MathsNotes";
//			
//	
//			public boolean uploadFile(MultipartFile multipartFile) {
//			
//				boolean flag = false;
//				try {
//					
//					InputStream inputStream = multipartFile.getInputStream();
//					byte[] streamData =new byte[inputStream.available()];
//					inputStream.read(streamData);
//					
//					
//					FileOutputStream fileOutputStream = new FileOutputStream(UPLOAD_DIR+"\\"+ multipartFile.getOriginalFilename());
//					fileOutputStream.write(streamData);	
//					fileOutputStream.flush();
//					fileOutputStream.close();
//					flag=true;
//					
////					instead of this we can use anIO  package alternative of above code
//					
////					Files.copy(multipartFile.getInputStream(),
////							Paths.get(UPLOAD_DIR+"\\"+multipartFile.getOriginalFilename())
////							,StandardCopyOption.COPY_ATTRIBUTES );
////		 			
//					
//					} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return flag;
//			}
//}


