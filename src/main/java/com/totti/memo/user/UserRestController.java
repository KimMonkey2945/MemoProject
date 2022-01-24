package com.totti.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.totti.memo.user.bo.UserBO;

@RestController   //Controller + responseBody
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	
	@PostMapping("/user/sign_up")
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
	
	
	
}
