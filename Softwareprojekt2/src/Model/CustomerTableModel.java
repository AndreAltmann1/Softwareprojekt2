package Model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

//Tabellendarstellung f�r Kunden
public class CustomerTableModel extends AbstractTableModel {

	private List<Customer> customerList;
	private String[] columns;

	public CustomerTableModel(List<Customer> customer) {
		super();
		this.customerList = customer;
		columns = new String[] { "Name", "Vorname", "Firma", "Plz", "Ort", "Anschrift", "Telefon" };
	}

	@Override
	public Object getValueAt(int row, int col) {

		Customer cust = customerList.get(row);
		switch (col) {
		case 0:
			return cust.getName();
		case 1:
			return cust.getVorname();
		case 2:
			return cust.getFirma();
		case 3:
			return cust.getPlz();
		case 4:
			return cust.getOrt();
		case 5:
			return cust.getAnschrift();
		case 6:
			return cust.getTelefon();
		default:
			return null;
		}
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.customerList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columns.length;
	}

	@Override
	public String getColumnName(int col) {
		return columns[col];
	}

}
