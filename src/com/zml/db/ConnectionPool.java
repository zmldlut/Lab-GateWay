package com.zml.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;

public class ConnectionPool {

	private static String DRIVER="com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://192.168.9.69:3306/lab";
	private static String USERNAME = "root";
	private static String PASSWORD = "";
	
	private String _testTable = ""; // 测试连接是否可用的测试表名，默认没有测试表
	private int initialConnections = 20; // 连接池的初始大小
	private int incrementalConnections = 5;// 连接池自动增加的大小
    private int maxConnections = 50; // 连接池最大的大小
    private Vector<PooledConnection> connectionVector = null; // 存放连接池中数据库连接的向量 , 初始时为 null
    
	//返回连接池初始大小
	public int getInitialConnections() {
		return this.initialConnections;
	}
	
	
	public void setInitialConnections(int initialConnections) {
		this.initialConnections=initialConnections;
	}
	
	//获取连接池自动增大的大小
	public int getIncrementalConnections() {
		return this.incrementalConnections;
	}
	
	public void setIncrementalConnections(int incrementalConnections) {
		this.incrementalConnections = incrementalConnections;
	}
	
	//返回连接池中的最大可用连接
	public int getMaxConnections() {
		return this.maxConnections;
	}
	
	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}
	
	private static class ConnectionPoolHolder{
    	/** 单例变量  */
		private static ConnectionPool instance = new ConnectionPool();
    }
    
    private ConnectionPool() {
    	try {
			createPool();
		} catch (Exception e) {
			e.printStackTrace();
		}//创建数据库连接池
    }
    public static ConnectionPool getInstance() {
    	return ConnectionPoolHolder.instance;
    }
	
	//创建数据库连接池
	public synchronized void createPool()throws Exception{
		if(connectionVector!=null) {
			return;//如果已经创建
		}
		
		//实例化JDBC Driver中指定的驱动类实例
		Driver driver =(Driver)(Class.forName(DRIVER).newInstance());
		
		DriverManager.registerDriver(driver);
		connectionVector = new Vector<PooledConnection>();
		
		createConnections(this.initialConnections);
		System.out.println("数据库创建成功");
	}
	
	private void createConnections(int numConnections)throws SQLException{
		for(int x = 0;x<numConnections;x++)
		{
			if (this.maxConnections >0 && this.connectionVector.size() >= this.maxConnections) {
	            break;
			}

			try {
				connectionVector.addElement(new PooledConnection(createConnection()));
			} catch (SQLException e){
				System.out.println("创建数据库连接失败"+e.getMessage());
			}
			System.out.println("数据库连接已经能够建立");
		}
	}
	
	private Connection createConnection() throws SQLException{
		Connection conn = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
		if (connectionVector.size()==0) {
			DatabaseMetaData metaData = (DatabaseMetaData) conn.getMetaData();
			int driverMaxConnections = metaData.getMaxConnections();
			if (driverMaxConnections>0&& this.maxConnections>driverMaxConnections) {
				this.maxConnections = driverMaxConnections;
			}
		}
		return conn;
	}
	
	public synchronized Connection getConnection() throws SQLException{
		int i=0;
		//确保连接池已经被创建
		if(connectionVector == null) {
			return null;
		}
		Connection conn = getFreeConnection();
		
		while(conn == null&&i<5){   ///最长等待5*50milliseconds,最多循环5次
			try {
				wait(50);//等待50 milliseconds
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = getFreeConnection();
			i++;
		}
		return conn;
	}
	
	private Connection getFreeConnection()throws SQLException{
		return findFreeConnection();
	}
	
	private Connection findFreeConnection()throws SQLException{
		Connection conn =null;
		PooledConnection pConn=null;
		Enumeration<PooledConnection> enumerate = connectionVector.elements();
		while(enumerate.hasMoreElements()) {
			pConn = (PooledConnection)enumerate.nextElement();
		 	if (!pConn.isBusy()) {
			    conn = pConn.getConnection();
			    pConn.setBusy(true);
			    // 测试此连接是否可用
			    if (!testConnection(conn)) {
			    	try {	
			    		conn = createConnection();
			    	} catch (SQLException e) {
			    		System.out.println(" 创建数据库连接失败！ " + e.getMessage());
			    		return null;
			    	}
			  		pConn.setConnection(conn);
			    }
			    break; // 己经找到一个可用的连接，退出
			}
		}
		return conn;
	}
	
	/*
	 * 此函数返回一个数据库连接到连接池中，并把此链接置为空闲
	 * 所有使用连接池获得的数据库连接均应该在不使用它时返回它
	 */
	public synchronized void returnConnection(Connection conn)
	{
		if(connectionVector == null) {
			System.out.println("连接池不存在");
			return;
		}
		PooledConnection pConn = null;
		Enumeration<PooledConnection> enumerate = connectionVector.elements();
		while(enumerate.hasMoreElements()){
			pConn = (PooledConnection)enumerate.nextElement();
			//先找到连接池中的要返回的连接对象
			if(conn == pConn.getConnection()) {
				pConn.setBusy(false);//置为空闲状态
				break;
			}
		}	
	}
	
    //测试一个连接是否可用
    private boolean testConnection(Connection conn) {
    	try {
            if (_testTable.equals("")) {
               conn.setAutoCommit(true);
            } else {// 有测试表的时候使用测试表测试
            	Statement stmt = (Statement) conn.createStatement();
            	stmt.execute("select count(*) from " + _testTable);
            }
       } catch (SQLException e) {
           closeConnection(conn);
           return false;
       }
       return true;
    }

    //关闭连接池中所有的连接，并清空连接池。
    public synchronized void closeConnectionPool() throws SQLException {
    	// 确保连接池存在，如果不存在，返回
        if (connectionVector == null) {
        	System.out.println(" 连接池不存在，无法关闭 !");
            return;
        }
        PooledConnection pConn = null;
        Enumeration<PooledConnection> enumerate = connectionVector.elements();
        while (enumerate.hasMoreElements()) {
        	pConn = (PooledConnection) enumerate.nextElement();
        	// 如果忙，等 5 秒
            if (pConn.isBusy()) {
            	try {
					wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
            closeConnection(pConn.getConnection());
            connectionVector.removeElement(pConn);
        }
        connectionVector = null;
   }

   	//关闭一个数据库连接
	private void closeConnection(Connection conn) {
		try{
			conn.close();
		}catch(SQLException e){
			System.out.println("关闭数据库连接出错:"+e.getMessage());
		}
	}
    
    class PooledConnection {   
    	private Connection connection = null;// 数据库连接
	    private boolean busy = false; // 此连接是否正在使用的标志，默认没有正在使用
	    // 构造函数，根据一个 Connection 构告一个 PooledConnection 对象
        public PooledConnection(Connection connection) {
        	this.connection = connection;
        }
        // 返回此对象中的连接
        public Connection getConnection() {	
        	return connection;
        }
	
        // 设置此对象的，连接
        public void setConnection(Connection connection) {
        	this.connection = connection;	
        }

        // 获得对象连接是否忙
        public boolean isBusy() {	
            return busy;
        }
        
       // 设置对象的连接正在忙
       public void setBusy(boolean busy) {
           this.busy = busy;
       }
	}	
}
