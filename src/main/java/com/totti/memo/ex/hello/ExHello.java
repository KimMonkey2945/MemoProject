package com.totti.memo.ex.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExHello {

	@ResponseBody
	@GetMapping("/hello")
	public String hello() {
		return "hello world!!!";
	}
	
}
