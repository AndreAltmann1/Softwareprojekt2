package Model;
//Wird genutzt um Name und gesamt Zeit des Projekts des jeweiligen Kunden auszugeben  
public class CustomerProjektQuery {

	private String name;
	private int time;

	public CustomerProjektQuery(String name, int time) {
		this.name = name;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return name + " " + time;
	}

}
