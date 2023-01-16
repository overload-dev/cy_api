package com.dev.cy.models;

import java.util.ArrayList;

public class PlayerDetailModel {
	private String playerId;
	private String nickname;
	private int grade;
	private String clanName;
	private int ratingPoint;
	private int maxRatingPoint;
	private String tierName;
	private ArrayList<RecordModel> records;
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getClanName() {
		return clanName;
	}
	public void setClanName(String clanName) {
		this.clanName = clanName;
	}
	public int getRatingPoint() {
		return ratingPoint;
	}
	public void setRatingPoint(int ratingPoint) {
		this.ratingPoint = ratingPoint;
	}
	public int getMaxRatingPoint() {
		return maxRatingPoint;
	}
	public void setMaxRatingPoint(int maxRatingPoint) {
		this.maxRatingPoint = maxRatingPoint;
	}
	public String getTierName() {
		return tierName;
	}
	public void setTierName(String tierName) {
		this.tierName = tierName;
	}
	public ArrayList<RecordModel> getRecords() {
		return records;
	}
	public void setRecords(ArrayList<RecordModel> records) {
		this.records = records;
	}

	

}
