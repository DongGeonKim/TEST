package com.test.naver.captcha;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApiExamCaptchaImage {
	public static void main(String[] args) throws Exception {
		String clientId = "NJCCsaFJinO7rnvkNBIO"; // 애플리케이션 클라이언트 아이디값";
		String clientSecret = "5x5DkebE7U"; // 애플리케이션 클라이언트 시크릿값";

		String key = "SAB3KRgwCf4OKMCC"; // https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
		String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		InputStream is = get(apiURL, requestHeaders);

		System.out.println(is);
	}

	public static InputStream get(String apiUrl, Map<String, String> requestHeaders) throws Exception {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return con.getInputStream();
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		}
		return null;
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static InputStream getImage(InputStream is) {
		int read;
		byte[] bytes = new byte[1024];
		// 랜덤한 이름으로 파일 생성
		String filename = Long.valueOf(new Date().getTime()).toString();
		File f = new File(filename + ".jpg");
		try (OutputStream outputStream = new FileOutputStream(f)) {
			f.createNewFile();
			while ((read = is.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			return is;
		} catch (IOException e) {
			throw new RuntimeException("이미지 캡차 파일 생성에 실패 했습니다.", e);
		}
	}

	private static String error(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}
}
