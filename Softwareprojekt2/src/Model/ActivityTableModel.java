package Model;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;



//Tabellen Darstellung für Aktivitäten

public class ActivityTableModel extends AbstractTableModel {
	private List<Activity> activityList;
	private String[] columns;
	private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	public ActivityTableModel(List<Activity> activities) {
		super();
		this.activityList = activities;
		columns = new String[] { "Name", "Projekt", "Datum", "Zeit in Minuten" };
	}

	public Object getValueAt(int row, int col) {

		Activity act = activityList.get(row);
		switch (col) {
		case 0:
			return act.getName();
		case 1:
			return act.getProjekt();
		case 2:
			return format.format(act.getDate());
		case 3:
			return act.getZeit();

		default:
			return null;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columns.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.activityList.size();
	}

	public String getColumnName(int col) {
		return columns[col];
	}

}
