package com.hust.factory;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
	private Connection conn = null;
    private InitialContext ctx = null;
    private DataSource ds = null;
    
    public ConnectionFactory() {        
            try {
				ctx = new InitialContext();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        
    }

    public Connection getConnection(int i) throws Exception {
        try {
            switch (i) {
                case 1:
                    ds = (DataSource) ctx.lookup("java:comp/env/companyShowDatabase");
                    break;                
                default:
                    ds = (DataSource) ctx.lookup("java:comp/env/companyShowDatabase ");
                    break;
            }
            conn = ds.getConnection();

        } catch (Exception e) {
            throw new Exception("Connection Pool:" + i + "Cann't get the connection.", e);
        }
        return conn;        
    }

}
