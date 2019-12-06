package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

//Wird genutzt um in einem Diagramm nur Datum und Zeit einer Aktivität wiederzugeben

public class ActivitySumQuery {
	private Date date;
	private int zeit;
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	public ActivitySumQuery(Date date, int zeit) {
		this.date = date;
		this.zeit = zeit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getZeit() {
		return zeit;
	}

	public void setZeit(int zeit) {
		this.zeit = zeit;
	}

	@Override
	public String toString() {
		return date + " " + zeit;
	}

}
