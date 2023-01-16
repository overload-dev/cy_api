package com.dev.cy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dev.cy.common.Constants;
import com.dev.cy.models.CharacterModel;
import com.dev.cy.models.PlayerDetailModel;
import com.dev.cy.models.PlayerModel;
import com.dev.cy.service.APIRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class MainController {

	/// 홈 화면 이동
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String goHome(HttpServletRequest request) {
		return "content/home";
	}

	/// 플레이어 검색
	@RequestMapping(value = "search/players", method = RequestMethod.GET)
	public ModelAndView searchPlayers(HttpServletRequest request,
			@RequestParam("player_nickname") String playerNickname) {
		ModelAndView mav = new ModelAndView();
		// 요청 보낼 파라미터 및 url 세팅, 자세한건 네오플 api 문서 01.플레이어 검색 항목 참조
		//
		String reqUrl = Constants.REQ_BASE_URL + Constants.REQ_PLAYERS_URL;
		reqUrl += "?apikey=" + Constants.API_CLIENT;
		reqUrl += "&wordType=" + APIRequest.encodeURIComponent("full");
		reqUrl += "&limit=" + APIRequest.encodeURIComponent("10");
		reqUrl += "&nickname=" + APIRequest.encodeURIComponent(playerNickname);

		JSONObject res = APIRequest.requestAPI(reqUrl);

		// 받아온 JSON 객체를 내부에서 정의한 데이터모델로 변환
		Gson gson = new Gson();
		System.out.println("================================================");
		System.out.println(res);
		System.out.println("================================================");
		List<PlayerModel> players = new ArrayList<>();
		players = gson.fromJson(res.get("rows").toString(), new TypeToken<ArrayList<PlayerModel>>() {
		}.getType());

		/// 받아온 데이터와 함께 다른 페이지로 이동
		mav.addObject("players", players); // view에서 인식할 객체 이름
		mav.setViewName("content/result_players.html");
		return mav;
	}

	@RequestMapping(value = "player/detail", method = RequestMethod.GET)
	public ModelAndView playerDetail(HttpServletRequest request, @RequestParam("playerId") String playerId) {
		ModelAndView mav = new ModelAndView();
		// 요청 보낼 파라미터 및 url 세팅, 자세한건 네오플 api 문서 01.플레이어 검색 항목 참조
		//
		String reqUrl = Constants.REQ_BASE_URL + Constants.REQ_PLAYERS_URL;
		reqUrl += "/" + APIRequest.encodeURIComponent(playerId);
		reqUrl += "?apikey=" + Constants.API_CLIENT;

		JSONObject res = APIRequest.requestAPI(reqUrl);

		// 받아온 JSON 객체를 내부에서 정의한 데이터모델로 변환
		Gson gson = new Gson();
		System.out.println("================================================");
		System.out.println(res);
		System.out.println("================================================");
		PlayerDetailModel playerDetail = new PlayerDetailModel();
		playerDetail = gson.fromJson(res.toString(), PlayerDetailModel.class);

		mav.addObject("playerDetail", playerDetail);
		mav.setViewName("content/result_player_detail.html");
		return mav;
	}

	@RequestMapping(value = "characters", method = RequestMethod.GET)
	public ModelAndView characters(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		// 요청 보낼 파라미터 및 url 세팅, 자세한건 네오플 api 문서 01.플레이어 검색 항목 참조
		//
		String reqUrl = Constants.REQ_BASE_URL + Constants.REQ_CHARACTERS;
		reqUrl += "?apikey=" + Constants.API_CLIENT;

		System.out.println(reqUrl);
		
		JSONObject res = APIRequest.requestAPI(reqUrl);

		// 받아온 JSON 객체를 내부에서 정의한 데이터모델로 변환
		Gson gson = new Gson();
		System.out.println("================================================");
		System.out.println(res);
		System.out.println("================================================");
		ArrayList<CharacterModel> characters = new ArrayList<CharacterModel>();
		characters = gson.fromJson(res.get("rows").toString(), new TypeToken<ArrayList<CharacterModel>>() {
		}.getType());
		mav.addObject("characters", characters);
		mav.setViewName("content/result_characters.html");
		return mav;
	}

}
