package com.zml.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;

import net.sf.json.JSONObject;

import com.zml.dao.factory.DaoFactory;
import com.zml.dao.proxy.DoorDaoProxy;
import com.zml.dao.proxy.HumidityDaoProxy;
import com.zml.dao.proxy.LightDaoProxy;
import com.zml.dao.proxy.PM2_5DaoProxy;
import com.zml.dao.proxy.SiginDaoProxy;
import com.zml.dao.proxy.StudentDaoProxy;
import com.zml.dao.proxy.TemperatureDaoProxy;
import com.zml.model.DoorRecord;
import com.zml.model.Humidity;
import com.zml.model.Light;
import com.zml.model.PM2_5;
import com.zml.model.SgRecord;
import com.zml.model.Student;
import com.zml.model.Temperature;
import com.zml.packet.BaseDataPacket;
import com.zml.packet.CmdPacket;
import com.zml.util.C;

public class Socket_thread implements Runnable{

	private final int MAX_TIMEOUT = 3000;//ms
	
	private Socket socket;
	private BufferedReader in_buff;
	private OutputStream out_buff;
	private JSONObject json = null;
	
	private boolean acceptMessage;
	
	public Socket_thread(Socket new_socket) {
		socket = new_socket;
		acceptMessage=true;
		try {
			socket.setSoTimeout(MAX_TIMEOUT);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	private void sendPacket(OutputStream out_buff, BaseDataPacket packet) {
		String msg = JSONObject.fromObject(packet).toString();
		System.out.println("发送 "+msg);
		try {
			out_buff.write(msg.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		acceptMessage=true;
		try {
			in_buff = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			out_buff = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String msg=null;//保存接收到客户端的数据信息
		int type = 0;//数据类型
		while(acceptMessage) {
			try {
				System.out.println("socketThread正在读取数据。。。。。。");
				msg = in_buff.readLine();
				if (msg != null) {
					System.out.println("socketThread "+msg);
				} else {
					continue;
				}
				
				json =  JSONObject.fromObject(msg);
//				VerifyPacket verify = (VerifyPacket) JSONObject.toBean(json, VerifyPacket.class); 
//				type = verify.getPacketType();
				type = json.getInt("packetType");
				int cmd = 0;
				switch(type) {
				case C.DOOROPENPACKETTYPE:
					if(((StudentDaoProxy) DaoFactory.getDaoInstance(StudentDaoProxy.class)).findDao(json.getString("userID"))) {
						cmd = 1;
						Student std = ((StudentDaoProxy) DaoFactory.getDaoInstance(StudentDaoProxy.class))._findDao(json.getString("userID"));
						System.out.println("DOOROPENPACKETTYPE "+std.getStdnum());
						DoorRecord record = new DoorRecord(std.getStdnum(), new Date(), json.getInt("id"));
						((DoorDaoProxy) DaoFactory.getDaoInstance(DoorDaoProxy.class)).doCreate(record);
					} else {
						cmd = 0;
					}
					BaseDataPacket openCmd = new CmdPacket(json.getInt("syn")+1, json.getInt("id"), C.DOOROPENPACKETTYPE, cmd);
					sendPacket(out_buff, openCmd);
					acceptMessage = false;
					break;
				case C.HUMIDITYPACKETTYPE:
					cmd = 1;
					Humidity humidity = new Humidity(json.getInt("id"), Integer.parseInt(json.getString("humidity")), new Date());
					((HumidityDaoProxy) DaoFactory.getDaoInstance(HumidityDaoProxy.class)).doCreate(humidity);
					BaseDataPacket humidityCmd = new CmdPacket(json.getInt("syn")+1, json.getInt("id"), C.HUMIDITYPACKETTYPE, cmd);
					sendPacket(out_buff, humidityCmd);
					System.out.println("HUMIDITYPACKETTYPE "+cmd);
					acceptMessage = false;
					break;
				case C.TEMPERPACKETTYPE:
					cmd = 1;
					Temperature temperature = new Temperature(json.getInt("id"), Integer.parseInt(json.getString("temperature")), new Date());
					((TemperatureDaoProxy) DaoFactory.getDaoInstance(TemperatureDaoProxy.class)).doCreate(temperature);
					BaseDataPacket temperCmd = new CmdPacket(json.getInt("syn")+1, json.getInt("id"), C.TEMPERPACKETTYPE, cmd);
					sendPacket(out_buff, temperCmd);
					System.out.println("TEMPERPACKETTYPE "+cmd);
					acceptMessage = false;
					break;
				case C.CHECKINPACKETTYPE:
					if(((StudentDaoProxy) DaoFactory.getDaoInstance(StudentDaoProxy.class)).findDao(json.getString("userID"))) {
						cmd = 1;
						Student std = ((StudentDaoProxy) DaoFactory.getDaoInstance(StudentDaoProxy.class))._findDao(json.getString("userID"));
						System.out.println("CHECKINPACKETTYPE "+std.getStdnum());
						SgRecord sgRecord = new SgRecord(std.getStdnum(), new Date(), new Date(), json.getInt("id"));
						((SiginDaoProxy) DaoFactory.getDaoInstance(SiginDaoProxy.class)).doCreate(sgRecord);
					} else {
						cmd = 0;
					}
					System.out.println("CHECKINPACKETTYPE "+cmd);
					BaseDataPacket checkinCmd = new CmdPacket(json.getInt("syn")+1, json.getInt("id"), C.CHECKINPACKETTYPE, cmd);
					sendPacket(out_buff, checkinCmd);
					acceptMessage = false;
					break;
				case C.PM2_5PACKETTYPE:
					cmd = 1;
					PM2_5 pm = new PM2_5(json.getInt("id"), Integer.parseInt(json.getString("PM2.5")), new Date());
					((PM2_5DaoProxy) DaoFactory.getDaoInstance(PM2_5DaoProxy.class)).doCreate(pm);
					BaseDataPacket pmCmd = new CmdPacket(json.getInt("syn")+1, json.getInt("id"), C.TEMPERPACKETTYPE, cmd);
					sendPacket(out_buff, pmCmd);
					System.out.println("TEMPERPACKETTYPE "+cmd);
					acceptMessage = false;
					break;
				case C.LIGHTPACKETTYPE:
					cmd = 1;
					Light light = new Light(json.getInt("id"), json.getString("lightsensor"), new Date());
					((LightDaoProxy) DaoFactory.getDaoInstance(LightDaoProxy.class)).doCreate(light);
					BaseDataPacket lightCmd = new CmdPacket(json.getInt("syn")+1, json.getInt("id"), C.TEMPERPACKETTYPE, cmd);
					sendPacket(out_buff, lightCmd);
					System.out.println("TEMPERPACKETTYPE "+cmd);
					acceptMessage = false;
					break;
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				acceptMessage = false;
				break;
			}
		}
		//关闭socket连接，释放数据库连接
		if(socket!=null) {
			try {
				in_buff.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
