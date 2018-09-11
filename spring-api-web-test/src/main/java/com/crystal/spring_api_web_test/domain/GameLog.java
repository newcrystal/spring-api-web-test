package com.crystal.spring_api_web_test.domain;

import java.math.BigInteger;

public class GameLog {
	private BigInteger logId;
	private int characterId;
	public BigInteger getLogId() {
		return logId;
	}
	public int getCharacterId() {
		return characterId;
	}
	public void setLogId(BigInteger logId) {
		this.logId = logId;
	}
	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}
}
