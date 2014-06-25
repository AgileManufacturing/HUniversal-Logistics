package discovery;

public enum Service {
	GATEWAY("gateway"),
	DATABASE("database"),
	GUI("gui"),
	TRANSPORTUNIT("transportUnit");
	
	private String name;
	
	private Service(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
