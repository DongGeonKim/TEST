package com.test.controller;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.annotation.UserAnnotation;
import com.test.dto.UserDto;

@RestController
public class MainController {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@GetMapping("/")
    public String main() throws Exception {
		Class<UserAnnotation> userAnnotation = UserAnnotation.class;
		
		System.out.println(userAnnotation.getDeclaredMethod("country").getDefaultValue());
		System.out.println(userAnnotation.getDeclaredMethod("gender").getDefaultValue());
		System.out.println("------------------------------------------------");
		
		Class<?> clazz = UserDto.class;
		Field nameField = clazz.getDeclaredField("name");
		UserAnnotation nameAnnotation = nameField.getAnnotation(UserAnnotation.class);
		
		System.out.println((nameAnnotation != null) ? nameAnnotation.country()+"/"+nameAnnotation.gender() : "");
		
		UserDto dto = new UserDto();
		dto.setName("test_name");
		dto.setAge(30);
		System.out.println(dto);
		
		return dto.toString();
    }
	
	@GetMapping("/mailTest")
	public String mailTest() throws Exception {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("dgkim@naruint.com"); //받는 사람 주소
        //mailMessage.setFrom("121212"); //보내는 사람 주소(호출하지 않으면 yml의 username으로 세팅)
        mailMessage.setSubject("test"); //제목
        mailMessage.setText("testText"); //메시지 내용
        javaMailSender.send(mailMessage); //메일 발송
		return "";
	}
	
}
