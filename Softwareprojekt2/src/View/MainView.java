package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Model.DBConnector;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainView extends JFrame {
	
	

	private JPanel contentPane;
	private JTable table;

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
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		 
		    
		    
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);
		
		pack();
		setVisible(true);
	}

}
