package br.com.lincoln.intencaovoto.util;

public class TestConnectionManager {

	private static boolean threadRunning;
	private static TestConnection tc;

	// bloco de inicialização para atributos static
	static {
		threadRunning = false;
		tc = new TestConnection();
	}

	public static boolean test() {
		if (!threadRunning) {
			tc = new TestConnection();
			tc.start();
			threadRunning = true;
		}
		if (tc.getStatusConnection()) {
			threadRunning = false;
		}
		return tc.getStatusConnection();
	}

	public static boolean getThreadRunning() {
		return threadRunning;
	}

	public static TestConnection getTc() {
		if(tc.getStatusConnection()){
			threadRunning = false;
		} 
		return tc;
	}
	
}
