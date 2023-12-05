package com.test.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class TestController {
	
	@GetMapping("/test1_call")
	public void test1Call() throws Exception {
		for(int i=0; i<3; i++) {
			String result = this.getText1();
			System.out.println(result);
		}
	}
	
	@GetMapping("/test2_call")
    public void test2Call() throws Exception{
        for(int i=0; i<3; i++) {
			this.getText2().subscribe(str -> {System.out.println(str);});
		}
		
    }
	
	@GetMapping("/test1")
    public String getText1() throws Exception{
		BufferedReader in = null;
		String result = "";
		 try {
	            URL obj = new URL("http://localhost:8090/test1"); // 호출할 url
	            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
	 
	            con.setRequestMethod("GET");
	 
	            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
	        
	            String line;
	            while((line = in.readLine()) != null) {
	            	result = line;
	            }
	        } catch(Exception e) {
	            e.printStackTrace();
	        } finally {
	            if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
	        }
		 return result;
    }
	
	@GetMapping("/test2")
    public Mono<String> getText2() throws Exception{
		 return WebClient.create()
	                .get()
	                .uri("http://localhost:8090/test2")
	                .retrieve()
	                .bodyToMono(String.class);
		
    }
	
}
