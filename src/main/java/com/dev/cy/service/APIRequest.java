package com.dev.cy.service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

public class APIRequest {
	/// HTTP 요청 타입은 GET, POST, DELETE, PUT, PATCH 등이 있다
	/// 보편적으로 아래와 같은 용도로 각각의 요청 타입(메소드라 하기도함)을 사용한다.
	/// 단순 정보 획득에는 GET
	/// 무언가 값을 들려보내는데 감춰져야할 경우 POST
	/// 특정 데이터 삭제 DELETE
	/// 특정 데이터를 추가해야할 경우 PUT
	/// 특정 데이터의 일부를 수정해야 할 경우 PATCH
	public static JSONObject requestAPI(String reURL) {

		// HTTP 통신 객체
		HttpURLConnection conn = null;
		// 반환 받을 값 객체
		JSONObject resJson = null;

		try {

			URL url = new URL(reURL);

			/// 서버 통신 커넥션 인스턴스 생성
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			/// 무언가 요청을 보내는데 필요한 헤더값 정의
			/// 헤더 값은 서버에서 인식하는 요청의 종류를 식별하기 위해 사용된다.
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Connection", "keep-alive");
			conn.setDoOutput(true);
		

			// 실질 통신 작업
			int resCode = conn.getResponseCode();

			/// HTTP는 실제로 크게 3가지 반환이 있다 (파고들면 훨씬 많다)
			/// 대표적으로 200번 대 반환 코드는 요청에 대한 작업 성공
			/// 400번 대는 잘못된 파라미터를 보내는등 서버가 수용할 수 없는 형태의 요청 시 반환하는 에러
			/// 500번 대는 서버측에 무언가 문제가 발생할경우 반환 에러
			if (resCode == 200) {
				// 반환 값을 읽어들인다.
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}

				resJson = new JSONObject(sb.toString());

				// 응답 데이터
				System.out.println("responseJson :: " + resJson);
			}

			/// 무언가 문제가 발생할 경우 아래의 코드를 탄다.
		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resJson;

	}

	public static String encodeURIComponent(String component) {
		String result = null;

		try {
			result = URLEncoder.encode(component, "UTF-8");
		} catch (UnsupportedEncodingException e) {

			result = component;
		}
		return result;

	}

}
