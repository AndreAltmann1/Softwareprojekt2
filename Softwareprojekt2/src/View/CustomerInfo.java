package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import Model.Activity;

import Model.Customer;
import Model.CustomerModel;
import Model.CustomerProjektQuery;
import Model.DBConnector;

import Model.ProjectTableModel;
import Model.Projekt;

public class CustomerInfo extends JFrame {
	private JTable table;
	private ProjectTableModel ptm;
	private int row;
	private Customer cust;
	private List<Projekt> projectList;
	private List<Customer> customerList;

//Fenster Zeigt ein Diagramm und Informationen zu den Projekten des ausgewählten Kunden
	
	public CustomerInfo(JTable custTable) {

		setMinimumSize(new Dimension(1000, 1000));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 988, 864);

		JPanel panelDiagram = new JPanel();
		panelDiagram.setBorder(new LineBorder(Color.BLACK));
		getContentPane().add(panelDiagram, BorderLayout.CENTER);
		panelDiagram.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(25, 25));
		getContentPane().add(scrollPane, BorderLayout.SOUTH);

		
		//Tabelle füllen mit allen Projekten des ausgewählten Kunden
		row = custTable.getSelectedRow();
		customerList = CustomerModel.getAllCustomer();
		cust = customerList.get(custTable.convertRowIndexToModel(row));
		Query q = DBConnector.getEM().createQuery("SELECT a From TBL_PROJECT a WHERE a.customer = :cust",
				Projekt.class);
		q.setParameter("cust", cust);
		projectList = q.getResultList();

		ptm = new ProjectTableModel(projectList);

		table = new JTable(ptm);
		table.setPreferredScrollableViewportSize(new Dimension(450, 180));
		table.setRowSelectionAllowed(false);
		table.setBorder(new LineBorder(Color.BLACK));
		scrollPane.setViewportView(table);

		
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		//Datenbank anfrage um Name und gesamte zeit je Projekt zu Bekommen
		CriteriaBuilder cb = DBConnector.getEM().getCriteriaBuilder();
		CriteriaQuery<CustomerProjektQuery> q1 = cb.createQuery(CustomerProjektQuery.class);
		Root<Projekt> p = q1.from(Projekt.class);
		Root<Activity> c = q1.from(Activity.class);
		q1.multiselect(p.get("name"), cb.sum(c.get("zeit")));
		ParameterExpression<Customer> e = cb.parameter(Customer.class);

		q1.where(cb.equal(p.get("customer"), e), cb.equal(c.get("projekt"), p));
		q1.groupBy(p.get("prjNr"));

		TypedQuery<CustomerProjektQuery> query = DBConnector.getEM().createQuery(q1);
		query.setParameter(e, cust);
		
		List<CustomerProjektQuery> results = query.getResultList();

		//Ergebniss wird dem Dataset übergeben
		for (CustomerProjektQuery a : results) {
			dataset.addValue(a.getTime(), "Projekt", a.getName());
		}
//Erzeugen eines Diagrammes im Fesnter 
		JFreeChart jChart = ChartFactory.createBarChart(
				cust.getVorname() + " " + cust.getName() + " - " + cust.getFirma(), "Projekt", "Zeit in Min.", dataset,
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
