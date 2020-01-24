package View;

import java.awt.BorderLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



import Model.Activity;
import Model.ActivityModel;

import Model.ProjectModel;
import Model.Projekt;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class ChangeActivityView extends JFrame implements PropertyChangeListener {

	private JPanel contentPane;
	private JTextField tfAktName;
	private JTextField tfAktDatum;
	private JTextField tfAktDauer;
	private JComboBox<Projekt> cbAktPrj;
	private List<Projekt> projectList;
	private int row;
	private Activity act;
	private List<Activity> activityList;
	private CalendarWindow calendarWindow = new CalendarWindow();
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	
	//Fenster zum ändern einer Aktivität
	public ChangeActivityView(JTable table) {
		
		Icon iconChange = new ImageIcon("resources/icons8-bearbeiten-24.png");
		Icon iconTimer = new ImageIcon("resources/icons8-timer-24.png");

		//Values der ausgewählten Aktivität
		String name = (String) table.getValueAt(table.getSelectedRow(), 0);
		Projekt project = (Projekt) table.getValueAt(table.getSelectedRow(), 1);
		String zeit = table.getValueAt(table.getSelectedRow(), 3).toString();
		String datum = (String) table.getValueAt(table.getSelectedRow(), 2);

		setTitle("Aktivität bearbeiten");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnChangeAktivität = new JButton();
		btnChangeAktivität.setVerticalAlignment(SwingConstants.TOP);
		btnChangeAktivität.setIcon(iconChange);
		panel.add(btnChangeAktivität);
		
		//Stoppuhr öffnen
//		JButton btnTimer = new JButton("");
//		btnTimer.setIcon(iconTimer);
//		btnTimer.setToolTipText("Stoppuhr Starten");
//		panel.add(btnTimer);
//		
//		btnTimer.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//
//				StoppUhr stoppuhr = new StoppUhr(tfAktDauer);
//				stoppuhr.setVisible(true);
//
//			}
//		});

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
		gbc_lblAktName.anchor = GridBagConstraints.WEST;
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
		gbc_lblAktProjekt.anchor = GridBagConstraints.WEST;
		gbc_lblAktProjekt.insets = new Insets(0, 0, 5, 5);
		gbc_lblAktProjekt.gridx = 0;
		gbc_lblAktProjekt.gridy = 1;
		panel_1.add(lblAktProjekt, gbc_lblAktProjekt);

		cbAktPrj = new JComboBox<Projekt>();
		GridBagConstraints gbc_cbAktPrj = new GridBagConstraints();
		gbc_cbAktPrj.gridwidth = 2;
		gbc_cbAktPrj.insets = new Insets(0, 0, 5, 0);
		gbc_cbAktPrj.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbAktPrj.gridx = 1;
		gbc_cbAktPrj.gridy = 1;
		panel_1.add(cbAktPrj, gbc_cbAktPrj);

		projectList = new ArrayList<Projekt>(ProjectModel.getAllProjects());
		for (Projekt prj : projectList) {
			cbAktPrj.addItem(prj);
		}

		JLabel lblAktDatum = new JLabel("Datum:");
		GridBagConstraints gbc_lblAktDatum = new GridBagConstraints();
		gbc_lblAktDatum.anchor = GridBagConstraints.WEST;
		gbc_lblAktDatum.insets = new Insets(0, 0, 5, 5);
		gbc_lblAktDatum.gridx = 0;
		gbc_lblAktDatum.gridy = 2;
		panel_1.add(lblAktDatum, gbc_lblAktDatum);

		tfAktDatum = new JTextField();
		tfAktDatum.setEditable(false);
		GridBagConstraints gbc_tfAktDatum = new GridBagConstraints();
		gbc_tfAktDatum.insets = new Insets(0, 0, 5, 5);
		gbc_tfAktDatum.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAktDatum.gridx = 1;
		gbc_tfAktDatum.gridy = 2;
		panel_1.add(tfAktDatum, gbc_tfAktDatum);
		tfAktDatum.setColumns(10);

		JButton btnDateChanger = new JButton("...");
		btnDateChanger.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_btnDateChanger = new GridBagConstraints();
		gbc_btnDateChanger.anchor = GridBagConstraints.WEST;
		gbc_btnDateChanger.insets = new Insets(0, 0, 5, 0);
		gbc_btnDateChanger.gridx = 2;
		gbc_btnDateChanger.gridy = 2;
		panel_1.add(btnDateChanger, gbc_btnDateChanger);

		calendarWindow.setUndecorated(true);
		calendarWindow.addPropertyChangeListener(this);

		btnDateChanger.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calendarWindow.setLocation(tfAktDatum.getLocationOnScreen().x,
						tfAktDatum.getLocationOnScreen().y + tfAktDatum.getHeight());
				calendarWindow.setVisible(true);

			}
		});

		JLabel lblAktDauer = new JLabel("Dauer in Min.:");
		GridBagConstraints gbc_lblAktDauer = new GridBagConstraints();
		gbc_lblAktDauer.anchor = GridBagConstraints.WEST;
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

		//Values der ausgeählten Aktivität in die Felder setzen
		tfAktName.setText(name);
		cbAktPrj.setSelectedItem(project);
		tfAktDauer.setText(zeit);
		tfAktDatum.setText(datum);
//Ändern der AKtivität
		btnChangeAktivität.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (tfAktName.getText().equals("") | tfAktDatum.equals("") | tfAktDauer.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Bitte alle Felder Füllen", "Fehlermeldung",
								JOptionPane.ERROR_MESSAGE);
					} else if (tfAktDauer.getText().chars().allMatch(Character::isDigit) == false) {
						JOptionPane.showMessageDialog(null, "Zeit bitte als Zahl in Min festlegen.", "Fehlermeldung",
								JOptionPane.ERROR_MESSAGE);
					} else {
						row = table.getSelectedRow();
						activityList = ActivityModel.getAllActivities();
						act = activityList.get(table.convertRowIndexToModel(row));
						ActivityModel.changeActivity(act, tfAktName.getText(), (Projekt) cbAktPrj.getSelectedItem(),
								format.parse(tfAktDatum.getText()), Integer.parseInt(tfAktDauer.getText()));
						dispose();
					}
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Bitte Datum angeben.", "Fehlermeldung",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		
		pack();

	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub
		if (event.getPropertyName().equals("selectedDate")) {
			java.util.Calendar cal = (java.util.Calendar) event.getNewValue();
			Date selDate = cal.getTime();

			tfAktDatum.setText(format.format(selDate));
		}
	}

}
