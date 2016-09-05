package shotchart;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MakeGraph extends JFrame {
	public MakeGraph() {
		super("Shot Chart");
		
		JPanel chartPanel = createChartPanel();
		add(chartPanel, BorderLayout.CENTER);
		
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private JPanel createChartPanel() {
		String chartTitle = "Shot Chart";
		String xAxisLabel = "X";
		String yAxisLabel = "Y";
		
		XYDataset dataset = createDataset();
		
//		JFreeChart chart =ChartFactory.createScatterPlot(chartTitle, 
//				xAxisLabel, yAxisLabel, dataset);
		
		boolean showLegend = true;
		boolean createURL = false;
		boolean createTooltip = false;
		
		JFreeChart chart = ChartFactory.createScatterPlot(chartTitle, 
				xAxisLabel, yAxisLabel, dataset, 
				PlotOrientation.VERTICAL, showLegend, createTooltip, createURL);
		
		customizeChart(chart);
		
		// saves the chart as an image files
		//File imageFile = new File("XYLineChart.png");
		int width = 640;
		int height = 480;
		
		
		return new ChartPanel(chart);
	}

	private XYDataset createDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series1 = new XYSeries("Made Shots");
		XYSeries series2 = new XYSeries("Missed Shots");
		

		Data t = new Data();
		for(int i = 0; i < t.getMadeX().size(); i++) {
	    	Integer x = t.getMadeX().get(i);
	    	Integer y = t.getMadeY().get(i);
	    	series1.add(x,y);
	    }
		for(int j = 0; j < t.getMissedX().size(); j++) {
	    	Integer a = t.getMissedX().get(j);
	    	Integer b = t.getMissedY().get(j);
	    	series2.add(a,b);
	    }
//		System.out.println("madex contains: " + t.getMadeX().toString());
//	    System.out.println("madex size: " + t.getMadeX().size());
//	    System.out.println("madex contains: " + t.getMadeY().toString());
//	    System.out.println("madex size: " + t.getMadeY().size());
//	    System.out.println("missedx contains: " + t.getMissedX().toString());
//	    System.out.println("missedx size: " + t.getMissedX().size());
//	    System.out.println("missedy contains: " + t.getMissedY().toString());
//	    System.out.println("missedy size: " + t.getMissedY().size());
		
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		
		
		return dataset;
	}
	
	private void customizeChart(JFreeChart chart) {
		XYPlot plot = chart.getXYPlot();
		XYItemRenderer renderer = plot.getRenderer();

		// sets paint color for each series
		renderer.setSeriesPaint(0, Color.GREEN);
		renderer.setSeriesPaint(1, Color.RED);
		
		// sets paint color for plot outlines
		plot.setOutlinePaint(Color.BLUE);
		plot.setOutlineStroke(new BasicStroke(2.0f));
		
		// sets renderer for lines
		plot.setRenderer(renderer);
		
		// sets plot background
		plot.setBackgroundPaint(Color.DARK_GRAY);
		
		// sets paint color for the grid lines
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);
		
	    NumberAxis domain = (NumberAxis) plot.getDomainAxis();
	    domain.setRange(-300, 300);
	    domain.setTickUnit(new NumberTickUnit(100));
	    domain.setVerticalTickLabels(true);
	    NumberAxis range = (NumberAxis) plot.getRangeAxis();
	    range.setRange(-100, 500);
	    range.setTickUnit(new NumberTickUnit(100));
	}
}
