package com.totti.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

//파일 저장 사용법으로 생각하면됨...........
public class FileManagerService {

	public final static String FILE_UPLOAD_PATH ="D:\\김용브\\spring\\spring project\\project\\upload\\image/";
	//항상 같은 값일 때 static을 사용하는 것임.....이유를 잘 생각...
	
	
	//logBack과 log4j를 찾아보기
	
	private static Logger logger = LoggerFactory.getLogger(FileManagerService.class);
	
	//만약 파일을 안올릴때
	public static String saveFile(int userId, MultipartFile file) {
		
		
		if(file == null) {
			logger.error("FileMnagerService::saveFile - 업로드 파일 없음");
			return null;
		}
		
		
		
		// 파일경로
		// 사용자 별로 구분 할 수 있도록
		// 사용자가 파일을 올린 시간으로 구분
		// /12_3265464/test.png
		
		//1970년 1월 1일 기준으로 현지 밀리세컨이 경과되었는지 나타내는수...
		String directoryName = userId + "_" + System.currentTimeMillis() + "/"; 
		//위의 형태로 만든거임..
		
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
			logger.error("FileMnagerService::saveFile - 파일 저장 에러");
			e.printStackTrace();
			return null;
		} 
		
		
		// 파일 접근 가능한 주소 리턴(강사님이 임의로 만든것임..)
		//<img src="/images/12_1234568/test.png">

		return	"/images/" + directoryName + file.getOriginalFilename();  //위의 경로 + 부분 잘보며 이해하기
		
		
		
	}
	
	
	//파일 삭제
	public static void removeFile(String filePath) {
		
		if(filePath == null) {
			logger.error("FileMnagerService::removeFile - 삭제 할 파일 없음");
			return ;
		}
		
		// 삭제할 파일 경로
		// filePath: /imges/2_45646546465/test.png
		// 실제 파일 경로: D:\\김용브\\spring\\spring project\\project\\upload\\image\\ + 2_45646546465/test.png
	
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images/", ""); //임의로 한 것이기 때문에 삭제
		
		//파일 지우기
		Path path = Paths.get(realFilePath);    //경로를 문자열이 아닌 다른 형태로 변경해야함...
		// 파일이 있는지 확인.
		if(Files.exists(path)){
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("FileMnagerService::removeFile - 파일 삭제 실패");
				e.printStackTrace();
			}
		}
	
		//디렉토리 삭제(폴더)
		//실제 디렉토리 경로: D:\\김용브\\spring\\spring project\\project\\upload\\image\\ + 2_45646546465 파일 이름을 제외한것.
		path = path.getParent();
		
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("FileMnagerService::removeFile - 디렉토리 삭제 실패");
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
