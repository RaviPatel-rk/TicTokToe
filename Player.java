package com.main.tictoktoe;

public class Player {

	private String playerId;
	private String playerName;
	private String address;
	private PlayerHistory playerHistory;
	private char symbol;
	
	
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public PlayerHistory getPlayerHistory() {
		return playerHistory;
	}
	public void setPlayerHistory(PlayerHistory playerHistory) {
		this.playerHistory = playerHistory;
	}
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
}
