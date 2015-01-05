package com.zml.packet;

public class AckPacket extends BaseDataPacket {
	private long Ack;

	public long getAck() {
		return Ack;
	}

	public void setAck(long ack) {
		Ack = ack;
	}
	
	public AckPacket(long syn, int type, long ack){
		Syn = syn;
		PacketType = type;
		Ack = ack;
	}
}
