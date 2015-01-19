package com.zml.util;

public class C {
	//数据包类型
	public static final int CHECKINPACKETTYPE 		= 1000;
	public static final int HUMIDITYPACKETTYPE 		= 1001;
	public static final int TEMPERPACKETTYPE 		= 1002;
	public static final int DOOROPENPACKETTYPE		= 1003;
	public static final int PM2_5PACKETTYPE			= 1004;
	public static final int LIGHTPACKETTYPE			= 1007;


	//json数据中的key
	public static final String SYN = "syn";
	public static final String PACKETTYPE = "packetType";
	public static final String CMD = "command";
	public static final String USERID = "userID";
	public static final String ACK = "ack";
}
