package com.totti.memo.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user") //공통적인 것은 묶어서...
public class UserController {

	@GetMapping("/signup_view")
	public String signupView() {
		return "user/signUp";
	}
	
	@GetMapping("/signin_view")
	public String signinView() {
		return "user/signIn";
	}
	
	@GetMapping("/sign_out")
	public String signOut(HttpServletRequest request) {
		
		//session에 있는 정보를 지우면됨..
		
		HttpSession session = request.getSession();
		
		//session에 회원 정보 제거
		session.removeAttribute("userId"); //지우는 거니 키 이름만 입력하면됨....
		session.removeAttribute("userLoginId"); //지우는 거니 키 이름만 입력하면됨....
		session.removeAttribute("userName"); //지우는 거니 키 이름만 입력하면됨....
		
		return "redirect:/user/signin_view";
		
	}
}
