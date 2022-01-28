package com.totti.memo.user.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.totti.memo.user.post.bo.PostBO;
import com.totti.memo.user.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {

	//view 와 api를 구분!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@Autowired 
	private PostBO postBO;
	
	@GetMapping("/list_view")
	public String listView(HttpServletRequest request, Model model) {
														//model은 jsp에서 사용 할 수 있도록 저장하는 것임...		
		//모든 사용자가 쓴글이 다 있으므로 로그인한 사용자의 글만 가져온다.
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		List<Post> postList = postBO.getPostList(userId);
		model.addAttribute("postList", postList);
		
		return "post/listView";
	}
	
	@GetMapping("/create_view")
	public String createView() {
		return "post/createView";
	}
	
	@GetMapping("/detail_view")  //view는 무조건 getmapping
	public String detailView(@RequestParam("postId") int postId, Model model) {
		//id 로 셀렉트
		
		Post post = postBO.getPost(postId);
		model.addAttribute("post", post);
		
		return "post/detailView";
	}
}
