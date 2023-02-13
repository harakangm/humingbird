package com.humingbird.service;

import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log // 정보를 위한것 / 자세한 정보 / 심각한 에러를 알려주는 로그
public class FileService {
	
	//파일 업로드 메서드
	public String uploadFile(String uploadPath, String originalFileName, byte[] fileDate) throws Exception{
		UUID uuid = UUID.randomUUID(); //중복되지 않은 랜덤한 파일이름 생성
		
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //확장자명
		String savedFileName = uuid.toString() + extension; //파일이름 생성
		String fileUploadFullUrl = uploadPath + "/" + savedFileName; // 업로드 경로
		
		// 생성자에 파일이 저장될 위치와 파일의 이름을 같이 넘겨 출력스트림을 만든다.
		FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
		fos.write(fileDate);
		fos.close();
		
		return savedFileName;
	}
}
