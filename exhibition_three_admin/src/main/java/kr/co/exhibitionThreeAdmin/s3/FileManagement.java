package kr.co.exhibitionThreeAdmin.s3;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileManagement {
	@Autowired
	private S3Uploader S3Uploader;
	
	public String FileUploader(MultipartFile mf) throws IOException {
		String FileNames ="";
		S3Uploader.S3Uploaders();
	    String originFileName = mf.getOriginalFilename(); // 원본 파일 명
	    long fileSize = mf.getSize(); // 파일 사이즈

//	    System.out.println("originFileName : " + originFileName);
//	    System.out.println("fileSize : " + fileSize);
	    S3Uploader.fileUpload("exhibition3",originFileName,mf);
//	    System.out.println(S3Uploader.getFileURL("exhibition3", originFileName));
	    String safeFile =System.currentTimeMillis() + originFileName;
	            
	    FileNames = FileNames+","+safeFile; 
	    try {
	   //	File f1 = new File(filepath+safeFile);
	   //    mf.transferTo(f1);
	     } catch (IllegalStateException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	      }//end catch
		return originFileName;
	}//FileUploader
	
	public String getFileUrl(String fileName) {
		String url =""; 
		S3Uploader.S3Uploaders();
		url= S3Uploader.getFileURL("exhibition3", fileName);
		return url;
	}//getFileUrl
	
	public void deleteFile(String fileName) {
		S3Uploader.S3Uploaders();
		S3Uploader.fileDelete("exhibition3", fileName);
	}//deleteFile
	
}//class
