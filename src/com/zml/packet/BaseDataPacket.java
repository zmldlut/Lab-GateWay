package com.zml.packet;

public class BaseDataPacket {
	protected long Syn;
	protected int Id;
	protected int PacketType;
	public long getSyn() {
		return Syn;
	}
	public void setSyn(long syn) {
		Syn = syn;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getPacketType() {
		return PacketType;
	}
	public void setPacketType(int packetType) {
		PacketType = packetType;
	}
}
