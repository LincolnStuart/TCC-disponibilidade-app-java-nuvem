package br.com.lincoln.intencaovoto.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TestConnection extends Thread {

	private boolean statusConnection;
	
	public TestConnection() {
		statusConnection = true;
	}
		
	public boolean getStatusConnection() {
		return statusConnection;
	}

	@Override
	public void run() {
		statusConnection = false;
		while (!statusConnection) {
			Connection conn = null;
			try {
				Context ctx = new InitialContext();
				DataSource ds = (DataSource) ctx
						.lookup("java:jboss/datasources/NuvemDS");
				ds.setLoginTimeout(5);
				// tenta abrir uma conex�o no pool, caso consiga, � sinal
				// de que a comunica��o foi reestabelecida, caso n�o con-
				// siga uma exce��o � lan�ada.
				conn = ds.getConnection();
				// sai do la�o.
				statusConnection = true;
			} catch (Exception e) {
				statusConnection = false;
			} finally {
				if(conn != null){
					//fecha a conex�o
					try {
						conn.close();
					} catch (SQLException e) {
						statusConnection = false;
					}
				}
			}
			try {
				// espere 2 segundos para testar a conex�o novamente.
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
}
