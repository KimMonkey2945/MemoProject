package com.totti.memo.user;

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
}
