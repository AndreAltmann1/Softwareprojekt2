package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;

public class AddAktivität extends JFrame {

	private JPanel contentPane;
	private JTextField tfAktName;
	private JTextField tfAktDatum;
	private JTextField tfAktDauer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAktivität frame = new AddAktivität();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddAktivität() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnAddAktivität = new JButton("Hinzuf\u00FCgen");
		panel.add(btnAddAktivität);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblAktName = new JLabel("Name:");
		GridBagConstraints gbc_lblAktName = new GridBagConstraints();
		gbc_lblAktName.insets = new Insets(0, 0, 5, 5);
		gbc_lblAktName.gridx = 0;
		gbc_lblAktName.gridy = 1;
		panel_1.add(lblAktName, gbc_lblAktName);
		
		tfAktName = new JTextField();
		GridBagConstraints gbc_tfAktName = new GridBagConstraints();
		gbc_tfAktName.insets = new Insets(0, 0, 5, 0);
		gbc_tfAktName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAktName.gridx = 2;
		gbc_tfAktName.gridy = 1;
		panel_1.add(tfAktName, gbc_tfAktName);
		tfAktName.setColumns(10);
		
		JLabel lblAktProjekt = new JLabel("Projekt:");
		GridBagConstraints gbc_lblAktProjekt = new GridBagConstraints();
		gbc_lblAktProjekt.insets = new Insets(0, 0, 5, 5);
		gbc_lblAktProjekt.gridx = 0;
		gbc_lblAktProjekt.gridy = 2;
		panel_1.add(lblAktProjekt, gbc_lblAktProjekt);
		
		JComboBox cbAktPrj = new JComboBox();
		GridBagConstraints gbc_cbAktPrj = new GridBagConstraints();
		gbc_cbAktPrj.insets = new Insets(0, 0, 5, 0);
		gbc_cbAktPrj.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbAktPrj.gridx = 2;
		gbc_cbAktPrj.gridy = 2;
		panel_1.add(cbAktPrj, gbc_cbAktPrj);
		
		JLabel lblAktDatum = new JLabel("Datum:");
		GridBagConstraints gbc_lblAktDatum = new GridBagConstraints();
		gbc_lblAktDatum.insets = new Insets(0, 0, 5, 5);
		gbc_lblAktDatum.gridx = 0;
		gbc_lblAktDatum.gridy = 3;
		panel_1.add(lblAktDatum, gbc_lblAktDatum);
		
		tfAktDatum = new JTextField();
		GridBagConstraints gbc_tfAktDatum = new GridBagConstraints();
		gbc_tfAktDatum.insets = new Insets(0, 0, 5, 0);
		gbc_tfAktDatum.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAktDatum.gridx = 2;
		gbc_tfAktDatum.gridy = 3;
		panel_1.add(tfAktDatum, gbc_tfAktDatum);
		tfAktDatum.setColumns(10);
		
		JLabel lblAktDauer = new JLabel("Dauer:");
		GridBagConstraints gbc_lblAktDauer = new GridBagConstraints();
		gbc_lblAktDauer.insets = new Insets(0, 0, 0, 5);
		gbc_lblAktDauer.gridx = 0;
		gbc_lblAktDauer.gridy = 4;
		panel_1.add(lblAktDauer, gbc_lblAktDauer);
		
		tfAktDauer = new JTextField();
		GridBagConstraints gbc_tfAktDauer = new GridBagConstraints();
		gbc_tfAktDauer.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAktDauer.gridx = 2;
		gbc_tfAktDauer.gridy = 4;
		panel_1.add(tfAktDauer, gbc_tfAktDauer);
		tfAktDauer.setColumns(10);
	}

}
