package View;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Customer;
import Model.CustomerModel;

import Model.ProjectModel;
import Model.Projekt;

import java.awt.GridBagLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;


public class AddProjektView extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JComboBox<Customer> cbCustomer;
	private JComboBox<String> cbZweck;
	private String[] cbFill;
	private List<Customer> customerList;

	//Fenster zum hinzufügen von Projekten
	public AddProjektView() {
		
		Icon iconAdd = new ImageIcon("resources/icons8-hinzufügen-24.png");
		
		setTitle("Projekte hinzufügen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnAddProjekt = new JButton();
		btnAddProjekt.setIcon(iconAdd);
		panel.add(btnAddProjekt);

		//Hinzufügen vom Projekten
		btnAddProjekt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bitte alle Felder Füllen", "Fehlermeldung",
							JOptionPane.ERROR_MESSAGE);
				} else if (cbCustomer.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Bitte vorher einen Kunden anlegen", "Fehlermeldung",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String name = tfName.getText();
					Customer customer = (Customer) cbCustomer.getSelectedItem();
					String zweck = cbZweck.getSelectedItem().toString();

					Projekt projekt = new Projekt(name, customer, zweck);
					ProjectModel.addProject(projekt);
					dispose();
				}
			}
		});

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
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

		//Combobox mit Kunden füllen
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

		cbFill = new String[] { "Privat", "Arbeit" };
		cbZweck = new JComboBox<>(cbFill);
		GridBagConstraints gbc_cbZweck = new GridBagConstraints();
		gbc_cbZweck.anchor = GridBagConstraints.NORTH;
		gbc_cbZweck.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbZweck.gridx = 2;
		gbc_cbZweck.gridy = 3;
		panel_1.add(cbZweck, gbc_cbZweck);

		pack();
	}

}
