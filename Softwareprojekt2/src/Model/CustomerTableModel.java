package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CustomerTableModel extends AbstractTableModel {
	
	private ArrayList<Customer> customerList;
	private String[] columns;
	
	public CustomerTableModel (ArrayList <Customer> customer) {
		super();
		this.customerList = customer;
		columns = new String[]{"Name","Vorname", "Firma", "plz", "ort", "anschrift", "Telefon"};
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
