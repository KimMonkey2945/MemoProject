package com.totti.memo.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.totti.memo.user.bo.UserBO;
import com.totti.memo.user.model.User;

@RestController  //Controller + responseBody
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	
	@PostMapping("/sign_up")
	public Map<String, String> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email){
		
		int count = userBO.addUser(loginId, password, name, email);
		
		//json 형태로 돌려주기 위한 것임 
		//그대로 돌려주려면 map으로 따로 만들 필요가 없음....
		Map<String, String> map = new HashMap<>();
		
		if(count == 1) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		
		return map;
		
	}
	
	
	@PostMapping("/sign_in")
	public Map<String, String> signIn(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,HttpServletRequest request){
		
		User user = userBO.getUser(loginId, password);
		//객체가 있으면 전달 없으면 null이 전달 될 것임...
		Map<String, String> result = new HashMap<>();
		if(user != null) {
			//로그인 성공
			result.put("result", "success");
			
			//session에 값을 저장하는 과정...
			HttpSession session = request.getSession();
			
			//id, loginId, name 총세가지를 session에 저장
			session.setAttribute("userId", user.getId()); //앞에는 key 내마음대로 정하면 됨..
			session.setAttribute("userLoginId", user.getLoginId()); //앞에는 key 내마음대로 정하면 됨..
			session.setAttribute("userName", user.getName()); //앞에는 key 내마음대로 정하면 됨..
			
		}else {
			//로그인 실패
			result.put("result", "fail");
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
}
