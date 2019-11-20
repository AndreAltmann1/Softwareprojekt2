package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Model.Activity;
import Model.ActivityModel;
import Model.ActivityTableModel;
import Model.DBConnector;

import Model.ProjectModel;
import Model.ProjectTableModel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainView extends JFrame {
	
	

	private JPanel contentPane;
	private JTable table;
	private ActivityTableModel atm;
	private List<Activity> activityList;
	private Activity act;
	private int row;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		DBConnector.getEM();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
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
	public MainView() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 451);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnProjekte = new JMenu("Projekte");
		menuBar.add(mnProjekte);
		
		JMenuItem mntmAddPrj = new JMenuItem("Hinzuf\u00FCgen");
		mnProjekte.add(mntmAddPrj);
		
		mntmAddPrj.addActionListener(new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
		   {
			   ;
			   ProjektView projekts = new ProjektView();
			   projekts.setVisible(true);
		   }
		});
		
		JMenu mnKunden = new JMenu("Kunden");
		menuBar.add(mnKunden);
		
		JMenuItem mntmAddKunde = new JMenuItem("Hinzuf\u00FCgen");
		mnKunden.add(mntmAddKunde);
		
		mntmAddKunde.addActionListener(new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
		   {
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
		
		JButton btnHinzufügen = new JButton("Hinzuf\u00FCgen");
		panel.add(btnHinzufügen);
		
		btnHinzufügen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				;
				AddAktivität aktivitäten = new AddAktivität();
				aktivitäten.setVisible(true);
				
			}
		});
		
		JButton btnLöschen = new JButton("L\u00F6schen");
		panel.add(btnLöschen);
		
		btnLöschen.addActionListener(new ActionListener()
		{
			   public void actionPerformed(ActionEvent e)
			   {
				row = table.getSelectedRow();
				activityList = ActivityModel.getAllActivities();
				act = activityList.get(table.convertRowIndexToModel(row));
				ActivityModel.deleteActivity(act);
				table.setModel(new ActivityTableModel(ActivityModel.getAllActivities()));
			   }
			});
		
		JButton btnReload = new JButton("Reload Table");
		panel.add(btnReload);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		 
		    
		    
		atm = new ActivityTableModel(ActivityModel.getAllActivities());
		table = new JTable(atm);
		
		
		
		
		
		
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);
		
		btnReload.addActionListener(new ActionListener()
		{
			   public void actionPerformed(ActionEvent e)
			   {
				  table.setModel(new ActivityTableModel(ActivityModel.getAllActivities()));
			   }
			});
		
		addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				table.setModel(new ActivityTableModel(ActivityModel.getAllActivities()));
			}
		});
		
		pack();
		setVisible(true);
	}

}
