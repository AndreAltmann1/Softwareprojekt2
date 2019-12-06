package View;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Model.Customer;
import Model.CustomerModel;

//Fenster zum ändern eins Kunden

public class ChangeCustView extends JFrame {

	private JPanel contentPane;
	private JTextField tfKuName;
	private JTextField tfKuFirma;
	private JTextField tfKuPlz;
	private JTextField tfKuOrt;
	private JTextField tfKuAnschrift;
	private JTextField tfKuVorname;
	private JTextField tfKuTelefon;
	private Customer cust;
	private List<Customer> customerList;
	private int row;

	
	public ChangeCustView(JTable table) {
		
		Icon iconChange = new ImageIcon("resources/icons8-bearbeiten-24.png");
		
//Values des ausgewählten Kunden
		String name = (String) table.getValueAt(table.getSelectedRow(), 0);
		String vorname = (String) table.getValueAt(table.getSelectedRow(), 1);
		String firma = (String) table.getValueAt(table.getSelectedRow(), 2);
		String plz = (String) table.getValueAt(table.getSelectedRow(), 3);
		String ort = (String) table.getValueAt(table.getSelectedRow(), 4);
		String anschrift = (String) table.getValueAt(table.getSelectedRow(), 5);
		String telefon = (String) table.getValueAt(table.getSelectedRow(), 6);

		setTitle("Kunde bearbeiten");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnChangeKunde = new JButton();
		btnChangeKunde.setIcon(iconChange);
		panel.add(btnChangeKunde);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblKuName = new JLabel("Name:");
		GridBagConstraints gbc_lblKuName = new GridBagConstraints();
		gbc_lblKuName.anchor = GridBagConstraints.WEST;
		gbc_lblKuName.insets = new Insets(0, 0, 5, 5);
		gbc_lblKuName.gridx = 0;
		gbc_lblKuName.gridy = 1;
		panel_1.add(lblKuName, gbc_lblKuName);

		tfKuName = new JTextField();
		GridBagConstraints gbc_tfKuName = new GridBagConstraints();
		gbc_tfKuName.insets = new Insets(0, 0, 5, 0);
		gbc_tfKuName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfKuName.gridx = 3;
		gbc_tfKuName.gridy = 1;
		panel_1.add(tfKuName, gbc_tfKuName);
		tfKuName.setColumns(10);

		JLabel lblKuVorname = new JLabel("Vorname:");
		GridBagConstraints gbc_lblKuVorname = new GridBagConstraints();
		gbc_lblKuVorname.insets = new Insets(0, 0, 5, 5);
		gbc_lblKuVorname.gridx = 0;
		gbc_lblKuVorname.gridy = 2;
		panel_1.add(lblKuVorname, gbc_lblKuVorname);

		tfKuVorname = new JTextField();
		GridBagConstraints gbc_tfKuVorname = new GridBagConstraints();
		gbc_tfKuVorname.anchor = GridBagConstraints.NORTH;
		gbc_tfKuVorname.insets = new Insets(0, 0, 5, 0);
		gbc_tfKuVorname.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfKuVorname.gridx = 3;
		gbc_tfKuVorname.gridy = 2;
		panel_1.add(tfKuVorname, gbc_tfKuVorname);
		tfKuVorname.setColumns(10);

		JLabel lblKuFirma = new JLabel("Firma:");
		GridBagConstraints gbc_lblKuFirma = new GridBagConstraints();
		gbc_lblKuFirma.anchor = GridBagConstraints.WEST;
		gbc_lblKuFirma.insets = new Insets(0, 0, 5, 5);
		gbc_lblKuFirma.gridx = 0;
		gbc_lblKuFirma.gridy = 3;
		panel_1.add(lblKuFirma, gbc_lblKuFirma);

		tfKuFirma = new JTextField();
		GridBagConstraints gbc_tfKuFirma = new GridBagConstraints();
		gbc_tfKuFirma.insets = new Insets(0, 0, 5, 0);
		gbc_tfKuFirma.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfKuFirma.gridx = 3;
		gbc_tfKuFirma.gridy = 3;
		panel_1.add(tfKuFirma, gbc_tfKuFirma);
		tfKuFirma.setColumns(10);

		JLabel lblKuPlz = new JLabel("Plz:");
		GridBagConstraints gbc_lblKuPlz = new GridBagConstraints();
		gbc_lblKuPlz.anchor = GridBagConstraints.WEST;
		gbc_lblKuPlz.insets = new Insets(0, 0, 5, 5);
		gbc_lblKuPlz.gridx = 0;
		gbc_lblKuPlz.gridy = 4;
		panel_1.add(lblKuPlz, gbc_lblKuPlz);

		tfKuPlz = new JTextField();
		GridBagConstraints gbc_tfKuPlz = new GridBagConstraints();
		gbc_tfKuPlz.insets = new Insets(0, 0, 5, 0);
		gbc_tfKuPlz.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfKuPlz.gridx = 3;
		gbc_tfKuPlz.gridy = 4;
		panel_1.add(tfKuPlz, gbc_tfKuPlz);
		tfKuPlz.setColumns(10);

		JLabel lblKuOrt = new JLabel("Ort:");
		GridBagConstraints gbc_lblKuOrt = new GridBagConstraints();
		gbc_lblKuOrt.anchor = GridBagConstraints.WEST;
		gbc_lblKuOrt.insets = new Insets(0, 0, 5, 5);
		gbc_lblKuOrt.gridx = 0;
		gbc_lblKuOrt.gridy = 5;
		panel_1.add(lblKuOrt, gbc_lblKuOrt);

		tfKuOrt = new JTextField();
		GridBagConstraints gbc_tfKuOrt = new GridBagConstraints();
		gbc_tfKuOrt.insets = new Insets(0, 0, 5, 0);
		gbc_tfKuOrt.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfKuOrt.gridx = 3;
		gbc_tfKuOrt.gridy = 5;
		panel_1.add(tfKuOrt, gbc_tfKuOrt);
		tfKuOrt.setColumns(10);

		JLabel lblKuAnschrift = new JLabel("Anschrift:");
		GridBagConstraints gbc_lblKuAnschrift = new GridBagConstraints();
		gbc_lblKuAnschrift.anchor = GridBagConstraints.WEST;
		gbc_lblKuAnschrift.insets = new Insets(0, 0, 5, 5);
		gbc_lblKuAnschrift.gridx = 0;
		gbc_lblKuAnschrift.gridy = 6;
		panel_1.add(lblKuAnschrift, gbc_lblKuAnschrift);

		tfKuAnschrift = new JTextField();
		GridBagConstraints gbc_tfKuAnschrift = new GridBagConstraints();
		gbc_tfKuAnschrift.insets = new Insets(0, 0, 5, 0);
		gbc_tfKuAnschrift.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfKuAnschrift.gridx = 3;
		gbc_tfKuAnschrift.gridy = 6;
		panel_1.add(tfKuAnschrift, gbc_tfKuAnschrift);
		tfKuAnschrift.setColumns(10);

		JLabel lblKuTelefon = new JLabel("Telefon:");
		GridBagConstraints gbc_lblKuTelefon = new GridBagConstraints();
		gbc_lblKuTelefon.anchor = GridBagConstraints.WEST;
		gbc_lblKuTelefon.insets = new Insets(0, 0, 0, 5);
		gbc_lblKuTelefon.gridx = 0;
		gbc_lblKuTelefon.gridy = 7;
		panel_1.add(lblKuTelefon, gbc_lblKuTelefon);

		tfKuTelefon = new JTextField();
		GridBagConstraints gbc_tfKuTelefon = new GridBagConstraints();
		gbc_tfKuTelefon.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfKuTelefon.gridx = 3;
		gbc_tfKuTelefon.gridy = 7;
		panel_1.add(tfKuTelefon, gbc_tfKuTelefon);
		tfKuTelefon.setColumns(10);

		//Übergeben der Values des Kunden and die Textfelder
		tfKuName.setText(name);
		tfKuVorname.setText(vorname);
		tfKuFirma.setText(firma);
		tfKuPlz.setText(plz);
		tfKuOrt.setText(ort);
		tfKuAnschrift.setText(anschrift);
		tfKuTelefon.setText(telefon);

		//Ändern des Kunden in der Datenbank Tabelle
		btnChangeKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfKuName.getText().equals("") || tfKuVorname.getText().equals("") || tfKuFirma.getText().equals("")
						|| tfKuPlz.getText().equals("") || tfKuOrt.getText().equals("")
						|| tfKuAnschrift.getText().equals("") || tfKuTelefon.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bitte alle Felder Füllen", "Fehlermeldung",
							JOptionPane.ERROR_MESSAGE);

				} else {
					row = table.getSelectedRow();
					customerList = CustomerModel.getAllCustomer();
					cust = customerList.get(table.convertRowIndexToModel(row));
					CustomerModel.changeCustomer(cust, tfKuName.getText(), tfKuVorname.getText(), tfKuFirma.getText(),
							tfKuPlz.getText(), tfKuOrt.getText(), tfKuAnschrift.getText(), tfKuTelefon.getText());
					dispose();
				}

			}
		});
	}

}
