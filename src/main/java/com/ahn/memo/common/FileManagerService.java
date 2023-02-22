package com.ahn.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	public static final String FILE_UPLOAD_PATH = "D:\\study\\java\\spring\\project\\upload\\images";

	// 파일을 저장하고, 클라이언트에서 접근 가능한 주소를 만들어서 리턴하는 기능
	public static String saveFile(int userId, MultipartFile file) {
		
		// 파일 저장
		
		
		// 사용자 별로 폴더를 구분한다.
		// 사용자 별로 폴더를 새로 만든다.
		// 폴더 이름 : userId_현재시간
		// UNIX TIME : 1790월 1월 1일부터 흐른 시간(millisecond 1/1000)
		// D:\\study\\java\\spring\\project\\upload\\1_142385124\\asdf.png
		String directoryName = "/" + userId + "_" + System.currentTimeMillis() + "/";
		
		//디렉토리 생성
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			// 디렉토리 생성 실패
			return null;
		} 
		
		// 파일 저장
		try {
			byte[] bytes = file.getBytes();
			
			String filePath = directoryPath + file.getOriginalFilename();
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		}
		
		// 클라이언트에 접근 가능한 경로를 문자열로 리턴
		// http://localhost:8080/images/~
		return "/images" + directoryName + file.getOriginalFilename();
		
		
	}
	
}
