package communication;

public class Host {
	private String _address, _name;
	
	public Host(String address) {
		_address = address;
		_name = "";
	}
	
	public Host(String address, String name) {
		_address = address;
		_name = name;
	}
	
	public void setName(String name){
		_name = name;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getAddress() {
		return _address;
	}
}
