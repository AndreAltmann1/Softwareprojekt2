package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import java.awt.Color;

public class KundenView extends JFrame {

	private JPanel contentPane;
	private JTable KuTable;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KundenView frame = new KundenView();
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
	public KundenView() {
		setTitle("Kunden");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JButton btnAddKu = new JButton("Hinzuf\u00FCgen");
		panel_1.add(btnAddKu);
		
		btnAddKu.addActionListener(new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
		   {
			   ;
			   AddKuView addKunden = new AddKuView();
			   addKunden.setVisible(true);
		   }
		});
		
		JButton btnDelKu = new JButton("L\u00F6schen");
		panel_1.add(btnDelKu);
		
		JButton btnChangeKu = new JButton("\u00C4ndern");
		panel_1.add(btnChangeKu);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		
		
		
		
		KuTable = new JTable();
	
		KuTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(KuTable);
		
		
		
	}

}
