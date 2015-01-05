package com.zml.packet;

public class CmdPacket extends BaseDataPacket {
	private int Cmd;

	public int getCmd() {
		return Cmd;
	}

	public void setCmd(int cmd) {
		Cmd = cmd;
	}
	
	public CmdPacket(long syn, int id, int type, int cmd) {
		Syn = syn;
		Id = id;
		PacketType = type;
		Cmd = cmd;
	}
}
