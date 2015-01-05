package com.zml.packet;

public class VerifyPacket extends BaseDataPacket {
	private String UserID;

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}
}
