package gateway;

public class Gateway {
	private static Gateway instance = null;
	
	private Gateway() {

	}
	
	public static Gateway getInstance() {
		if (instance == null) {
			instance = new Gateway();
		}
		return instance;
	}
	
	public void handleRequest() {
		
	}
}
