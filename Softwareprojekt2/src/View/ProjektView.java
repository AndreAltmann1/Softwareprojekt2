package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProjektView extends JFrame {

	private JPanel contentPane;
	private JTable prjTable;

	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the frame.
	 */
	public ProjektView() {
		setTitle("Projekte");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 628, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAddPrj = new JButton("Hinzuf\u00FCgen");
		panel_1.add(btnAddPrj);
		
		btnAddPrj.addActionListener(new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
		   {
			   ;
			   AddProjektView addPrj = new AddProjektView();
			   addPrj.setVisible(true);
		   }
		});
		
		JButton btnDelPrj = new JButton("L\u00F6schen");
		panel_1.add(btnDelPrj);
		
		JButton btnChangePrj = new JButton("\u00C4ndern");
		btnChangePrj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.add(btnChangePrj);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		prjTable = new JTable();
		prjTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(prjTable);
		
		pack();
		setVisible(true);
	}
	
	

}
