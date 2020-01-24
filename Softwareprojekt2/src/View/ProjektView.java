package View;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.persistence.Query;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;



import Model.Activity;
import Model.ActivityModel;

import Model.DBConnector;
import Model.ProjectModel;
import Model.ProjectTableModel;
import Model.Projekt;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.List;
import java.awt.event.ActionEvent;

public class ProjektView extends JFrame {

	private JPanel contentPane;
	private JTable prjTable;
	private List<Projekt> projectList;
	private ProjectTableModel ptm;
	private Projekt prj;
	private int row;
	private List<Activity> activityList;

//Fenster zum verwalten von Projekten
	public ProjektView() {
		
		Icon iconAdd = new ImageIcon("resources/icons8-hinzufügen-24.png");
		Icon iconDel = new ImageIcon("resources/icons8-löschen-24.png");
		Icon iconChange = new ImageIcon("resources/icons8-bearbeiten-24.png");
		Icon iconInfo = new ImageIcon("resources/icons8-diagramm-bericht-24.png");
		
		setTitle("Projekte");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 628, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnAddPrj = new JButton();
		btnAddPrj.setIcon(iconAdd);
		btnAddPrj.setToolTipText("Hinzuf\u00FCgen");
		panel_1.add(btnAddPrj);

		//Öffnen des Fensters zum hinzufügen von Projekten
		btnAddPrj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				;
				AddProjektView addPrj = new AddProjektView();
				addPrj.setVisible(true);
			}
		});
//Löschen des ausgewählten Projektes
		JButton btnDelPrj = new JButton();
		btnDelPrj.setIcon(iconDel);
		btnDelPrj.setToolTipText("L\u00F6schen");
		panel_1.add(btnDelPrj);

		btnDelPrj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				row = prjTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Bitte wählen sie ein Projekt.", "Fehlermeldung",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int confirm = JOptionPane.showConfirmDialog(null,
							"Wollen sie dieses Projekt wirklich löschen? Alle Aktivitäten dieses Projektes werden ebenfalls gelöscht!",
							"Warnung", JOptionPane.YES_NO_OPTION);
					if (confirm == 0) {

						projectList = ProjectModel.getAllProjects();
						prj = projectList.get(prjTable.convertRowIndexToModel(row));

						Query q = DBConnector.getEM().createQuery("SELECT a From TBL_ACTIVITY a WHERE a.projekt = :prj",
								Activity.class);
						q.setParameter("prj", prj);
						activityList = q.getResultList();
						for (Activity a : activityList) {
							ActivityModel.deleteActivity(a);
						}
						ProjectModel.deleteProject(prj);
						prjTable.setModel(new ProjectTableModel(ProjectModel.getAllProjects()));
					} else {

					}
				}
			}
		});
//Öffnen des Fensters zum Ändern eines ausgewählten Projektes 
		JButton btnChangePrj = new JButton();
		btnChangePrj.setIcon(iconChange);
		btnChangePrj.setToolTipText("Bearbeiten");
		btnChangePrj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				row = prjTable.getSelectedRow();
				row = prjTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Bitte wählen sie ein Projekt.", "Fehlermeldung",
							JOptionPane.ERROR_MESSAGE);
				} else {
					ChangeProjectView changePrj = new ChangeProjectView(prjTable);
					changePrj.setVisible(true);

				}
			}
		});
		panel_1.add(btnChangePrj);

		JButton btnReload = new JButton();
		btnReload.setIcon(iconInfo);
		btnReload.setToolTipText("Information");
		panel_1.add(btnReload);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		ptm = new ProjectTableModel(ProjectModel.getAllProjects());
		prjTable = new JTable(ptm);
		prjTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(prjTable);

		//Öffnen des Fensters um Informationen zum ausgewählten Projektes zu erhalten
		btnReload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				row = prjTable.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Bitte wählen sie ein Projekt.", "Fehlermeldung",
							JOptionPane.ERROR_MESSAGE);
				} else {
					ProjektInfo prjInfo = new ProjektInfo(prjTable);
					prjInfo.setVisible(true);
				}
			}
		});

		//Aktualisieren der Tabelle wenn Fenster Fokus gewinnt
		addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowGainedFocus(WindowEvent e) {
				prjTable.setModel(new ProjectTableModel(ProjectModel.getAllProjects()));
			}
		});

		pack();
		setVisible(true);
	}

}
