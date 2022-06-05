package kr.co.exhibitionThreeAdmin.s3;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
public class S3Uploader {
	
	//보안상 키 생략
    public static String accessKey;
    public static String secretKey;
	
	
	
	  @Value("${aws.secretKey}") 
	  public void setSecret(String value) { 
		  secretKey = value; 
	  }
	  
	 @Value("${aws.accessKey}") 
	 public void setClient(String value) { 
		 accessKey =value; 
     }
	 
    
    
	private AmazonS3 conn;

	public void S3Uploaders() {
		System.out.println("accessKey ==>"+accessKey);
		System.out.println("secretKey ==>"+secretKey);
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setProtocol(Protocol.HTTP);
		this.conn = new AmazonS3Client(credentials, clientConfig);
		conn.setEndpoint("s3.ap-northeast-2.amazonaws.com"); // 엔드포인트 설정 [ 아시아 태평양 서울 ]
	}

	
	/**
	 * 버킷 리스트를 가져온다
	 * @return
	 */
	public List<Bucket> getBucketList() {
	
		return conn.listBuckets();
	}

	/**
	 * 버킷을 생성하는 메서드이다.
	 * @param bucketName
	 * @return
	 */
	public Bucket createBucket(String bucketName) {
		return conn.createBucket(bucketName);
	}

	/**
	 * 폴더 생성 (폴더 생성 시 뒤에 "/"를 붙여야함)
	 * @param bucketName
	 * @param folderName
	 */
	public void createFolder(String bucketName, String folderName) {
		conn.putObject(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
	}
	
	
	/**
	 * 파일 업로드
	 * @param bucketName
	 * @param fileName
	 * @param file
	 * @throws IOException
	 */
	public void fileUpload(String bucketName, String fileName, MultipartFile file) throws IOException {
		byte [] fileData=file.getBytes();
		
		String filePath = (fileName).replace(File.separatorChar, '/'); // 파일 구별자를 `/`로 설정(\->/) 
		ObjectMetadata metaData = new ObjectMetadata();
		
		//메타 테이터 타입을 multipartfile 타입과 맞춰줘야 링크를 타고 넘어갔을 때 다운로드 안됨(06.03 해결)
		metaData.setContentType(file.getContentType());
		
		metaData.setContentLength(fileData.length);   //메타데이터 설정 
	    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData); //파일 넣음

		conn.putObject(bucketName, filePath, byteArrayInputStream, metaData);
		
	}
	
	/**
	 * 파일 삭제 
	 * @param bucketName
	 * @param fileName
	 */
	public void fileDelete(String bucketName, String fileName) {
		String imgName = (fileName).replace(File.separatorChar, '/');
		conn.deleteObject(bucketName, imgName);
//		System.out.println("삭제성공");
	}

	/**
	 * 파일 URL
	 * @param bucketName
	 * @param fileName
	 * @return
	 */
	public String getFileURL(String bucketName, String fileName) {
		//System.out.println("넘어오는 파일명 : "+fileName);
		String imgName = (fileName).replace(File.separatorChar, '/');
		return conn.generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, imgName)).toString();
	}
}//class
