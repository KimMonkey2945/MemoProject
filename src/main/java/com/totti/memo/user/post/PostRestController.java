package com.totti.memo.user.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.totti.memo.user.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String,String> create(
			@RequestParam("subject") String subject,
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request){
		
		// 세션에 로그인에 성공하면 정보를 저장하므로 세션을 가져온다

		HttpSession session = request.getSession();
		// 현재 로그인된 사용자의 user table id(pk) String , Object 형태라서 (Integer)로 캐스팅
		int userId = (Integer)session.getAttribute("userId"); 
		
		int count = postBO.addPost(userId, subject, content, file);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		}else {
			result.put("result", "fail");
		}
		
		return result;
		
	}
	
	
	@GetMapping("/delete")
	public Map<String,String> postDelete(@RequestParam("postId") int postId){
		
		int count = postBO.deletePost(postId);
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		}else {
			result.put("result", "fail");		
		}
		
		
		return result;
	}
	
	//글수정은 업뎃을 사용해서 해보기.....
	
	
	
	
	
	
	
	
	
	
}
