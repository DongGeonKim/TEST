package com.test.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.naver.captcha.ApiExamCaptchaImage;
import com.test.naver.captcha.ApiExamCaptchaNkey;
import com.test.naver.captcha.ApiExamCaptchaNkeyResult;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PageController {

	@GetMapping("/captcha")
	public String captcha() throws Exception{
		return "captcha";
	}
	
	@GetMapping("/captchaGetKey")
	@ResponseBody
	public String captchaGetKey(HttpServletResponse response) throws Exception{
		String clientId = ""; // 애플리케이션 클라이언트 아이디값";
		String clientSecret = ""; // 애플리케이션 클라이언트 시크릿값";

		String code = "0"; // 키 발급시 0, 캡차 이미지 비교시 1로 세팅
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = ApiExamCaptchaNkey.get(apiURL, requestHeaders);

		System.out.println(responseBody);
		return responseBody;
	}
	
	@GetMapping("/captchaGetImg")
	public void captchaGetImg(HttpServletResponse response, @RequestParam("key") String key) throws Exception{
		
		String clientId = ""; // 애플리케이션 클라이언트 아이디값";
		String clientSecret = ""; // 애플리케이션 클라이언트 시크릿값";

		String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		
		InputStream is = ApiExamCaptchaImage.get(apiURL, requestHeaders);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    IOUtils.copy(is, response.getOutputStream());
	    is.close();
	}
	
	@PostMapping("/captchaCheck")
	@ResponseBody
	public String captchaCheck(@RequestParam Map<String, String> paramMap) throws Exception{
		System.out.println("paramMap : " + paramMap);
		String clientId = ""; // 애플리케이션 클라이언트 아이디값";
		String clientSecret = ""; // 애플리케이션 클라이언트 시크릿값";

		String code = "1"; // 키 발급시 0, 캡차 이미지 비교시 1로 세팅
		String key = paramMap.get("captchaKey"); // 캡차 키 발급시 받은 키값
		String value = paramMap.get("captchaText"); // 사용자가 입력한 캡차 이미지 글자값
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code + "&key=" + key + "&value=" + value;
		
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = ApiExamCaptchaNkeyResult.get(apiURL, requestHeaders);

		System.out.println(responseBody);
		
		return responseBody;
	}

	@PostMapping("/formSerializeTest")
	@ResponseBody
	public String captchaTest(@RequestParam Map<String, Object> paramMap) throws Exception{
		System.out.println("paramMap : " + paramMap);
		
		System.out.println("paramMap : " + paramMap.get("test1"));
		System.out.println("paramMap : " + paramMap.get("test2"));
		System.out.println("paramMap : " + paramMap.get("test3"));
		
		return "111";
	}
	
	
}
