package Model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

//Tabellen Darstellung f�r Projekte
public class ProjectTableModel extends AbstractTableModel {
	private List<Projekt> projectList;
	private String[] columns;

	public ProjectTableModel(List<Projekt> projects) {
		super();
		this.projectList = projects;
		columns = new String[] { "Name", "Kunde", "Zweck" };
	}

	public Object getValueAt(int row, int col) {
		Projekt prj = projectList.get(row);
		switch (col) {
		case 0:
			return prj.getName();
		case 1:
			return prj.getCustomer();
		case 2:
			return prj.getZweck();

		default:
			return null;
		}
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.projectList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columns.length;
	}

	public String getColumnName(int col) {
		return columns[col];
	}

}
