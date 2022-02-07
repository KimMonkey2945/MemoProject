package com.totti.memo.user.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.totti.memo.common.FileManagerService;
import com.totti.memo.user.post.dao.PostDAO;
import com.totti.memo.user.post.model.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String subject, String content, MultipartFile file) {
		//여기서 파일을 저장하는 로직을 수행 컨트롤러에서 하는것 x 클래스 따로 만들어서 사용... 암호화랑 비슷
		
		//파일을 컴퓨터(서버)에 저장하고, 클라이언트가(브라우저) 접근 가능한 주소를 만들어낸다.
		String filePath = FileManagerService.saveFile(userId, file);
		
		return postDAO.insertPost(userId, subject, content, filePath);
	}

	public List<Post> getPostList(int userId){
		return postDAO.selectPostList(userId);
	}
	
	public Post getPost(int postId) {
		return postDAO.selectPost(postId);
	}
	
	public int deletePost(int postId) {
		
		Post post = postDAO.selectPost(postId);
		//운좋게 쓰던것이 있으니 다행 없으면 만들어야함.....
		FileManagerService.removeFile(post.getImagePath());
		
		
		return postDAO.deletePost(postId);
	}
}
