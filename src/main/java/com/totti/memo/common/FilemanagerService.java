package com.totti.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FilemanagerService {

	public final String FILE_UPLOAD_PATH ="D:\\김용브\\spring\\spring project\\project\\upload\\image/";
	
	
	//파일 저장
	public String saveFile(int userId, MultipartFile file) {
		
		
		// 파일경로
		// 사용자 별로 구분 할 수 있도록
		// 사용자가 파일을 올린 시간으로 구분
		// /12_3265464/test.png
		
		//1970년 1월 1일 기준으로 현지 밀리세컨이 경과되었는지 나타내는수...
		String directoryName = userId + "_" + System.currentTimeMillis() + "/"; //위의 형태로 만든거임..
		
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		// 디렉토리 생성
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			//디렉토리 생성에러
			return null;
		}
		
		try {
			//실제파일데이터를 얻어옴
			byte[] bytes = file.getBytes();
			// 파일 저장 경로 + 파일 이름 경로 객체
			Path path = Paths.get(filePath + file.getOriginalFilename());
			// 파일 저장
			Files.write(path, bytes);
			
		} catch (IOException e) {
			//파일 저장 실패			
			e.printStackTrace();
			return null;
		} 
		
		
		// 파일 접근 가능한 주소 리턴(강사님이 임의로 만든것임..)
		//<img src="/images/12_1234568/test.png">

		return	"/imges/" + directoryName + file.getOriginalFilename();  //위의 경로 + 부분 잘보며 이해하기
		
		
		
	}
	
	
}
