package com.dev.cy.models;

public class RecordModel {
	private String gameTypeId;
	private int winCount;
	private int loseCount;
	private int stopCount;
	
	public String getGameTypeId() {
		return gameTypeId;
	}
	public void setGameTypeId(String gameTypeId) {
		this.gameTypeId = gameTypeId;
	}
	public int getWinCount() {
		return winCount;
	}
	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}
	public int getLoseCount() {
		return loseCount;
	}
	public void setLoseCount(int loseCount) {
		this.loseCount = loseCount;
	}
	public int getStopCount() {
		return stopCount;
	}
	public void setStopCount(int stopCount) {
		this.stopCount = stopCount;
	}
}
