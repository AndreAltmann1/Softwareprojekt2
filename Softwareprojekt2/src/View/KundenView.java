package View;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.List;

import javax.persistence.Query;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;


import Model.Activity;
import Model.ActivityModel;
import Model.Customer;
import Model.CustomerModel;
import Model.CustomerTableModel;
import Model.DBConnector;
import Model.ProjectModel;
import Model.Projekt;

import java.awt.Color;

public class KundenView extends JFrame {

	private JPanel contentPane;
	private JTable KuTable;
	private List<Customer> customerList;
	private CustomerTableModel ctm;
	private Customer cust;
	private int row;
	private List<Projekt> projectList;
	private List<Activity> activityList;

	//Fenster zum Verwalten von Kunden
	public KundenView() {
		
		Icon iconAdd = new ImageIcon("resources/icons8-hinzufügen-24.png");
		Icon iconDel = new ImageIcon("resources/icons8-löschen-24.png");
		Icon iconChange = new ImageIcon("resources/icons8-bearbeiten-24.png");
		Icon iconInfo = new ImageIcon("resources/icons8-diagramm-bericht-24.png");
		
		setTitle("Kunden");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 626, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnAddKu = new JButton();
		btnAddKu.setIcon(iconAdd);
		btnAddKu.setToolTipText("Hinzuf\u00FCgen");
		panel_1.add(btnAddKu);

		//Öffnen Des Fensters zum hinzufügen von Kunden
		btnAddKu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				;
				AddKuView addKunden = new AddKuView();
				addKunden.setVisible(true);
			}
		});

		JButton btnDelKu = new JButton();
		btnDelKu.setIcon(iconDel);
		btnDelKu.setToolTipText("L\u00F6schen");
		panel_1.add(btnDelKu);

		//Kunden Löschen
		btnDelKu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				row = KuTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Bitte wählen sie einen Kunden.", "Fehlermeldung",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int confirm = JOptionPane.showConfirmDialog(null,
							"Wollen sie dieses Projekt wirklich löschen? Alle Aktivitäten und Projekte dieses Kunden werden ebenfalls gelöscht!",
							"Warnung", JOptionPane.YES_NO_OPTION);
					if (confirm == 0) {

						customerList = CustomerModel.getAllCustomer();
						cust = customerList.get(KuTable.convertRowIndexToModel(row));

						Query q = DBConnector.getEM()
								.createQuery("SELECT p From TBL_PROJECT p WHERE p.customer = :cust");
						q.setParameter("cust", cust);
						projectList = q.getResultList();
						for (Projekt p : projectList) {
							Query act = DBConnector.getEM()
									.createQuery("SELECT a From TBL_ACTIVITY a WHERE a.projekt = :prj");
							act.setParameter("prj", p);
							activityList = act.getResultList();
							for (Activity a : activityList) {
								ActivityModel.deleteActivity(a);
							}

						}
						projectList = q.getResultList();
						for (Projekt pr : projectList) {
							ProjectModel.deleteProject(pr);
						}

						CustomerModel.deleteCustomer(cust);

						KuTable.setModel(new CustomerTableModel(CustomerModel.getAllCustomer()));
					} else {

					}
				}
			}
		});

		JButton btnChangeKu = new JButton();
		btnChangeKu.setIcon(iconChange);
		btnChangeKu.setToolTipText("Bearbeiten");
		panel_1.add(btnChangeKu);

		
		//Öffnen das Fensters zum Ändern von Kunden
		btnChangeKu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				row = KuTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Bitte wählen sie einen Kunden.", "Fehlermeldung",
							JOptionPane.ERROR_MESSAGE);
				} else {
					ChangeCustView changeKu = new ChangeCustView(KuTable);
					changeKu.setVisible(true);
				}
			}
		});

		JButton btnreload = new JButton();
		btnreload.setIcon(iconInfo);
		btnreload.setToolTipText("Information");
		panel_1.add(btnreload);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		ctm = new CustomerTableModel(CustomerModel.getAllCustomer());
		KuTable = new JTable(ctm);

		KuTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(KuTable);

		//Öffnen der Informationen zum ausgewählten kunden
		btnreload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				row = KuTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Bitte wählen sie einen Kunden.", "Fehlermeldung",
							JOptionPane.ERROR_MESSAGE);
				} else {
					CustomerInfo info = new CustomerInfo(KuTable);
					info.setVisible(true);
				}

			}
		});
//Aktualisieren der Tabelle wenn Fenster Focus gewinnt
		addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowGainedFocus(WindowEvent e) {
				KuTable.setModel(new CustomerTableModel(CustomerModel.getAllCustomer()));
			}
		});

	}

}
