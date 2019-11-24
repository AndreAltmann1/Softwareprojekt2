package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Model.Customer;
import Model.CustomerModel;
import Model.ProjectModel;
import Model.Projekt;

public class ChangeProjectView extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JComboBox<Customer> cbCustomer;
	private JComboBox<String> cbZweck;
	private String[] cbFill;
	private List<Customer> customerList;
	private Projekt prj;
	private List<Projekt> projectList;
	private int row;
	
	
	public ChangeProjectView(JTable table) {
		
		String name = (String) table.getValueAt(table.getSelectedRow(), 0);
		Customer ku = (Customer) table.getValueAt(table.getSelectedRow(), 1);
		String zweck = (String) table.getValueAt(table.getSelectedRow(), 2);
		
		
		
		
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnChangeProjekt = new JButton("Ändern");
		panel.add(btnChangeProjekt);
		
		btnChangeProjekt.addActionListener(new ActionListener()
		{
			   public void actionPerformed(ActionEvent e)
			   {
				
				  dispose();
				  
			   }
			});
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblPrjName = new JLabel("Name:");
		GridBagConstraints gbc_lblPrjName = new GridBagConstraints();
		gbc_lblPrjName.anchor = GridBagConstraints.WEST;
		gbc_lblPrjName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrjName.gridx = 0;
		gbc_lblPrjName.gridy = 1;
		panel_1.add(lblPrjName, gbc_lblPrjName);
		
		tfName = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel_1.add(tfName, gbc_textField);
		tfName.setColumns(10);
		
		JLabel lblPrjKunde = new JLabel("Kunde:");
		GridBagConstraints gbc_lblPrjKunde = new GridBagConstraints();
		gbc_lblPrjKunde.anchor = GridBagConstraints.WEST;
		gbc_lblPrjKunde.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrjKunde.gridx = 0;
		gbc_lblPrjKunde.gridy = 2;
		panel_1.add(lblPrjKunde, gbc_lblPrjKunde);
		
		
		
		
		cbCustomer = new JComboBox<Customer>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 2;
		panel_1.add(cbCustomer, gbc_comboBox);
		
		customerList = new ArrayList<Customer>(CustomerModel.getAllCustomer());
		for (Customer cust : customerList) {
		    cbCustomer.addItem(cust);
		}
		
		JLabel lblPrjZweck = new JLabel("Zweck:");
		GridBagConstraints gbc_lblPrjZweck = new GridBagConstraints();
		gbc_lblPrjZweck.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblPrjZweck.insets = new Insets(0, 0, 0, 5);
		gbc_lblPrjZweck.gridx = 0;
		gbc_lblPrjZweck.gridy = 3;
		panel_1.add(lblPrjZweck, gbc_lblPrjZweck);
		
		cbFill = new String[] {"Privat", "Arbeit"};
		cbZweck = new JComboBox<>(cbFill);
		GridBagConstraints gbc_cbZweck = new GridBagConstraints();
		gbc_cbZweck.anchor = GridBagConstraints.NORTH;
		gbc_cbZweck.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbZweck.gridx = 2;
		gbc_cbZweck.gridy = 3;
		panel_1.add(cbZweck, gbc_cbZweck);
		
		
		tfName.setText(name);
		cbCustomer.setSelectedItem(ku);
		cbZweck.setSelectedItem(zweck);
		
		btnChangeProjekt.addActionListener(new ActionListener()
		{
			   public void actionPerformed(ActionEvent e)
			   {
				   if(tfName.getText().equals("")) {
					   JOptionPane.showMessageDialog(null, "Bitte alle Felder Füllen", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
				   }else {
					   row = table.getSelectedRow();
						projectList = ProjectModel.getAllProjects();
						prj = projectList.get(table.convertRowIndexToModel(row));
						ProjectModel.changeProject(prj, tfName.getText(),  (Customer) cbCustomer.getSelectedItem(), cbZweck.getSelectedItem().toString());
					  dispose();
				   }
				  
				  
			   }
			});
	}


}
