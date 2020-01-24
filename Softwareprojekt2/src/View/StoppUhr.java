package View;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Font;


public class StoppUhr extends JFrame {

	private JPanel contentPane;
	private Timer t;
	private int countSec = 0;
	private int countMin = 0;

	public StoppUhr(JTextField textfield) {
		
		
		
		
		setResizable(false);

		setTitle("Stoppuhr");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 384, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnStart);

		JButton btnPause = new JButton("Pause");
		panel.add(btnPause);

		JButton btnStop = new JButton("Stop");
		panel.add(btnStop);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 33, 64, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		panel_1.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblMin = new JLabel("00");
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 45));
		panel_2.add(lblMin);

		JLabel lblPoints = new JLabel(":");
		lblPoints.setFont(new Font("Tahoma", Font.PLAIN, 45));
		panel_2.add(lblPoints);

		JLabel lblSec = new JLabel("00");
		lblSec.setFont(new Font("Tahoma", Font.PLAIN, 45));
		panel_2.add(lblSec);

		//Starten des Timers 
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//Timer wird erstellt und zählt jede sekunde hoch. Wenn die Sekunden 60 erreichen wird die vsriable für Minuten um 1 erhöht und Sekunden wider auf 0 gesetzt
				t = new Timer(1000, new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						countSec++;

						if (countSec >= 60) {
							countMin++;
							countSec = 0;

						}
						//Dartsellung der Zeit auf dem Fenster
						lblSec.setText(Integer.toString(countSec));
						lblMin.setText(Integer.toString(countMin));

					}

				});
				t.start();

			}

		});
//Pausiert das Zählen
		btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				t.stop();
			}

		});

//Timer wird angehalten und Zeit wird in das Textfield übergeben. Dabei wird auf volle Minuten gerundet		
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int min = Integer.parseInt(lblMin.getText());
				int sec = Integer.parseInt(lblSec.getText());
				if (sec >= 30) {
					min++;
				} else {

				}

				textfield.setText(Integer.toString(min));
				dispose();

			}
		});

	}

}
