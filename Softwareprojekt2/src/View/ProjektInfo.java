package View;

import java.awt.BorderLayout;


import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.swing.JFrame;

import javax.swing.JPanel;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import Model.Activity;
import Model.ActivitySumQuery;
import Model.ActivityTableModel;
import Model.DBConnector;
import Model.ProjectModel;

import Model.Projekt;

import java.awt.Color;

import java.awt.Dimension;

//Fenster mit Diagram und Informationen zum ausgewählten Projekt
public class ProjektInfo extends JFrame {
	private JTable table;
	private ActivityTableModel atm;
	private int row;
	private Projekt prj;
	private List<Projekt> projectList;
	private List<Activity> activityList;
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	
	public ProjektInfo(JTable prjTable) {

		setMinimumSize(new Dimension(1000, 1000));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 988, 864);

		JPanel panelDiagram = new JPanel();
		panelDiagram.setBorder(new LineBorder(Color.BLACK));
		getContentPane().add(panelDiagram, BorderLayout.CENTER);
		panelDiagram.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(25, 25));
		getContentPane().add(scrollPane, BorderLayout.SOUTH);

		//Datenbank Anfrage für alle Aktivitäten des ausgewählten Projektes 
		row = prjTable.getSelectedRow();
		projectList = ProjectModel.getAllProjects();
		prj = projectList.get(prjTable.convertRowIndexToModel(row));
		Query q = DBConnector.getEM().createQuery("SELECT a From TBL_ACTIVITY a WHERE a.projekt = :prj ORDER BY a.date",
				Activity.class);
		q.setParameter("prj", prj);
		activityList = q.getResultList();

		atm = new ActivityTableModel(activityList);

		table = new JTable(atm);
		table.setPreferredScrollableViewportSize(new Dimension(450, 180));
		table.setRowSelectionAllowed(false);
		table.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setViewportView(table);

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		
		//Datenbank Anfrage um Datum und die genutzte Zeit am jeweiligen Datum zu bekommen
		CriteriaBuilder cb = DBConnector.getEM().getCriteriaBuilder();
		CriteriaQuery<ActivitySumQuery> q1 = cb.createQuery(ActivitySumQuery.class);
		Root<Activity> c = q1.from(Activity.class);
		q1.multiselect(c.get("date"), cb.sum(c.get("zeit")));
		ParameterExpression<Projekt> p = cb.parameter(Projekt.class);
		q1.where(cb.equal(c.get("projekt"), p));
		q1.groupBy(c.get("date"));
		q1.orderBy(cb.asc(c.get("date")));

		TypedQuery<ActivitySumQuery> query = DBConnector.getEM().createQuery(q1);
		query.setParameter(p, prj);
		// ((org.eclipse.persistence.jpa.JpaQuery)
		// query).getDatabaseQuery().dontMaintainCache();
		List<ActivitySumQuery> results = query.getResultList();

		//Resultete dem Dataset hinzufügen
		for (ActivitySumQuery a : results) {
			dataset.addValue(a.getZeit(), "Projekt", format.format(a.getDate()));
		}

		//Erzeugen eines Diagramms im Fenster
		JFreeChart jChart = ChartFactory.createLineChart(prj.getName(), "Datum", "Zeit in Min.", dataset,
				PlotOrientation.VERTICAL, false, false, false);
		BarRenderer renderer = null;
		CategoryPlot plot = null;
		renderer = new BarRenderer();

		ChartPanel chartPanel = new ChartPanel(jChart);
		panelDiagram.removeAll();
		panelDiagram.add(chartPanel, BorderLayout.CENTER);
		panelDiagram.validate();

		pack();

	}

}
