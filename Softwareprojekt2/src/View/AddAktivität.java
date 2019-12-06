package View;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import Model.Activity;
import Model.ActivityModel;

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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AddAktivität extends JFrame implements PropertyChangeListener {

	private JPanel contentPane;
	private JTextField tfAktName;
	private JTextField tfAktDauer;
	private JComboBox<Projekt> cbAktPrj;
	private List<Projekt> projectList;
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	private CalendarWindow calendarWindow = new CalendarWindow();
	private JTextField tfDatum;
	Date now = new Date();

	// Fenster für Hinzufügen von Aktivitäten
	
	public AddAktivität() {

		Icon iconTimer = new ImageIcon("resources/icons8-timer-24.png");
		Icon iconAdd = new ImageIcon("resources/icons8-hinzufügen-24.png");

		setTitle("Aktivität hinzufügen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 393, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
				//Stoppuhr öffnen
				JButton btnStoppuhr = new JButton();
				btnStoppuhr.setHorizontalAlignment(SwingConstants.RIGHT);
				btnStoppuhr.setIcon(iconTimer);
				btnStoppuhr.setToolTipText("Stoppuhr Starten");
				panel.add(btnStoppuhr);
				
				btnStoppuhr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						StoppUhr stoppuhr = new StoppUhr(tfAktDauer);
						stoppuhr.setVisible(true);

					}
				});

		JButton btnAddAktivität = new JButton();
		btnAddAktivität.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAddAktivität.setIcon(iconAdd);
		panel.add(btnAddAktivität);

		//Hinzufügen von Aktivitäten
		btnAddAktivität.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if (tfAktName.getText().equals("") || tfDatum.equals("")) {
						JOptionPane.showMessageDialog(null, "Bitte alle Felder Füllen", "Fehlermeldung",
								JOptionPane.ERROR_MESSAGE);
					} else if (cbAktPrj.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Bitte vorher ein Projekt anlegen", "Fehlermeldung",
								JOptionPane.ERROR_MESSAGE);

					} else if (tfAktDauer.getText().chars().allMatch(Character::isDigit) == false) {
						JOptionPane.showMessageDialog(null, "Zeit bitte als Zahl in Min festlegen.", "Fehlermeldung",
								JOptionPane.ERROR_MESSAGE);
					} else {
						String name = tfAktName.getText();
						Projekt projekt = (Projekt) cbAktPrj.getSelectedItem();

						Date datum = format.parse(tfDatum.getText());
						int zeit = 0;
						if (tfAktDauer.getText().equals("")) {
							zeit = 0;
						} else {
							zeit = Integer.parseInt(tfAktDauer.getText());
						}
						Activity activity = new Activity(name, projekt, datum, zeit);
						ActivityModel.addActivity(activity);
						dispose();
					}
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Bitte Datum angeben.", "Fehlermeldung",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblAktName = new JLabel("Name:");
		GridBagConstraints gbc_lblAktName = new GridBagConstraints();
		gbc_lblAktName.anchor = GridBagConstraints.EAST;
		gbc_lblAktName.insets = new Insets(0, 0, 5, 5);
		gbc_lblAktName.gridx = 0;
		gbc_lblAktName.gridy = 0;
		panel_1.add(lblAktName, gbc_lblAktName);

		tfAktName = new JTextField();
		GridBagConstraints gbc_tfAktName = new GridBagConstraints();
		gbc_tfAktName.gridwidth = 2;
		gbc_tfAktName.insets = new Insets(0, 0, 5, 0);
		gbc_tfAktName.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAktName.gridx = 1;
		gbc_tfAktName.gridy = 0;
		panel_1.add(tfAktName, gbc_tfAktName);
		tfAktName.setColumns(10);

		JLabel lblAktProjekt = new JLabel("Projekt:");
		GridBagConstraints gbc_lblAktProjekt = new GridBagConstraints();
		gbc_lblAktProjekt.anchor = GridBagConstraints.EAST;
		gbc_lblAktProjekt.insets = new Insets(0, 0, 5, 5);
		gbc_lblAktProjekt.gridx = 0;
		gbc_lblAktProjekt.gridy = 1;
		panel_1.add(lblAktProjekt, gbc_lblAktProjekt);

		cbAktPrj = new JComboBox<Projekt>();
		GridBagConstraints gbc_cbAktPrj = new GridBagConstraints();
		gbc_cbAktPrj.gridwidth = 2;
		gbc_cbAktPrj.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbAktPrj.insets = new Insets(0, 0, 5, 0);
		gbc_cbAktPrj.gridx = 1;
		gbc_cbAktPrj.gridy = 1;
		panel_1.add(cbAktPrj, gbc_cbAktPrj);

		//Hinzufügen der Projekte in die Combobox
		
		projectList = new ArrayList<Projekt>(ProjectModel.getAllProjects());
		for (Projekt prj : projectList) {
			cbAktPrj.addItem(prj);
		}

		JLabel lblAktDatum = new JLabel("Datum:");
		GridBagConstraints gbc_lblAktDatum = new GridBagConstraints();
		gbc_lblAktDatum.anchor = GridBagConstraints.EAST;
		gbc_lblAktDatum.insets = new Insets(0, 0, 5, 5);
		gbc_lblAktDatum.gridx = 0;
		gbc_lblAktDatum.gridy = 2;
		panel_1.add(lblAktDatum, gbc_lblAktDatum);

		tfDatum = new JTextField();
		tfDatum.setEditable(false);
		GridBagConstraints gbc_tfDatum = new GridBagConstraints();
		gbc_tfDatum.insets = new Insets(0, 0, 5, 5);
		gbc_tfDatum.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDatum.gridx = 1;
		gbc_tfDatum.gridy = 2;
		panel_1.add(tfDatum, gbc_tfDatum);
		tfDatum.setColumns(10);

		//Heutiges Datum setzten
		tfDatum.setText(format.format(now));

		JButton btnDatePicker = new JButton("...");
		btnDatePicker.setToolTipText("Datum wählen");
		btnDatePicker.setFont(new Font("Tahoma", Font.PLAIN, 8));

		GridBagConstraints gbc_btnDatePicker = new GridBagConstraints();
		gbc_btnDatePicker.anchor = GridBagConstraints.WEST;
		gbc_btnDatePicker.insets = new Insets(0, 0, 5, 0);
		gbc_btnDatePicker.gridx = 2;
		gbc_btnDatePicker.gridy = 2;
		panel_1.add(btnDatePicker, gbc_btnDatePicker);

		calendarWindow.setUndecorated(true);
		calendarWindow.addPropertyChangeListener(this);

		//Kalendar öffnen
		btnDatePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendarWindow.setLocation(tfDatum.getLocationOnScreen().x,
						tfDatum.getLocationOnScreen().y + tfDatum.getHeight());
				calendarWindow.setVisible(true);

			}
		});

		JLabel lblAktDauer = new JLabel("Dauer in Min.:");
		GridBagConstraints gbc_lblAktDauer = new GridBagConstraints();
		gbc_lblAktDauer.anchor = GridBagConstraints.EAST;
		gbc_lblAktDauer.insets = new Insets(0, 0, 0, 5);
		gbc_lblAktDauer.gridx = 0;
		gbc_lblAktDauer.gridy = 3;
		panel_1.add(lblAktDauer, gbc_lblAktDauer);

		tfAktDauer = new JTextField();
		GridBagConstraints gbc_tfAktDauer = new GridBagConstraints();
		gbc_tfAktDauer.gridwidth = 2;
		gbc_tfAktDauer.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAktDauer.gridx = 1;
		gbc_tfAktDauer.gridy = 3;
		panel_1.add(tfAktDauer, gbc_tfAktDauer);
		tfAktDauer.setColumns(10);

		pack();

	}

	//Datum in Textfield setzen nachdem es im Kalender ausgewählt wurde
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub
		if (event.getPropertyName().equals("selectedDate")) {
			java.util.Calendar cal = (java.util.Calendar) event.getNewValue();
			Date selDate = cal.getTime();

			tfDatum.setText(format.format(selDate));
		}

	}

}
