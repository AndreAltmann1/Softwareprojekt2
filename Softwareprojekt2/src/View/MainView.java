package View;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;


import Model.Activity;
import Model.ActivityModel;
import Model.ActivityTableModel;




import java.awt.Color;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


//Hauptfenster mit Tabelle für Aktivitäten
public class MainView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ActivityTableModel atm;
	private List<Activity> activityList;
	private Activity act;
	private int row;


	public MainView() {
		
		Icon iconAdd = new ImageIcon("resources/icons8-hinzufügen-24.png");
		Icon iconDel = new ImageIcon("resources/icons8-löschen-24.png");
		Icon iconChange = new ImageIcon("resources/icons8-bearbeiten-24.png");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 451);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnProjekte = new JMenu("Projekte");
		menuBar.add(mnProjekte);

		JMenuItem mntmAddPrj = new JMenuItem("Verwalten");
		mnProjekte.add(mntmAddPrj);
//Öffnen der Projektverwaltung
		mntmAddPrj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				;
				ProjektView projekts = new ProjektView();
				projekts.setVisible(true);
			}
		});

		JMenu mnKunden = new JMenu("Kunden");
		menuBar.add(mnKunden);

		JMenuItem mntmAddKunde = new JMenuItem("Verwalten");
		mnKunden.add(mntmAddKunde);
//Öffnen der Kundenverwaltung
		mntmAddKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				;
				KundenView kunden = new KundenView();
				kunden.setVisible(true);
			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JButton btnHinzufügen = new JButton();
		btnHinzufügen.setToolTipText("Hinzuf\u00FCgen");
		btnHinzufügen.setIcon(iconAdd);
		panel.add(btnHinzufügen);
//Öffnen des Fensters zum hinzufügen von Aktivitäten
		btnHinzufügen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				;
				AddAktivität aktivitäten = new AddAktivität();
				aktivitäten.setVisible(true);

			}
		});

		JButton btnLöschen = new JButton();
		btnLöschen.setToolTipText("L\u00F6schen");
		btnLöschen.setIcon(iconDel);
		panel.add(btnLöschen);
//Aktivitäten Löschen
		btnLöschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Bitte wählen sie eine Aktivität.", "Fehlermeldung",
							JOptionPane.ERROR_MESSAGE);
				} else {
					activityList = ActivityModel.getAllActivities();
					act = activityList.get(table.convertRowIndexToModel(row));
					ActivityModel.deleteActivity(act);
					table.setModel(new ActivityTableModel(ActivityModel.getAllActivities()));
				}
			}
		});

		JButton btnÄndern = new JButton();
		btnÄndern.setToolTipText("Bearbeiten");
		btnÄndern.setIcon(iconChange);
		panel.add(btnÄndern);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		atm = new ActivityTableModel(ActivityModel.getAllActivities());
		table = new JTable(atm);

		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);

		//Öffnen des Fensters zum ändern einer Aktivität
		btnÄndern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Bitte wählen sie eine Aktivität.", "Fehlermeldung",
							JOptionPane.ERROR_MESSAGE);
				} else {
					ChangeActivityView changeAct = new ChangeActivityView(table);
					changeAct.setVisible(true);
				}
			}
		});
//Aktualisieren der Tabelle wenn Fenster Fokus gewinnt
		addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				table.setModel(new ActivityTableModel(ActivityModel.getAllActivities()));
			}
		});

		pack();
		setVisible(true);
	}

}
