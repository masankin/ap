import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class TimeSeriesChart {
	ChartPanel frame1;

	public TimeSeriesChart() {
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("折线图", "时间",
				"吞吐量", xydataset, true, true, true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		dateaxis.setDateFormatOverride(new SimpleDateFormat("hh"));
		frame1 = new ChartPanel(jfreechart, true);
		dateaxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		dateaxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = xyplot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		jfreechart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

	}

	private static XYDataset createDataset() { // 这个数据集有点多，但都不难理解
		TimeSeries timeseries = new TimeSeries("吞吐率",
				org.jfree.data.time.Hour.class);
		timeseries.add(new Hour(20, new Day()), 181.80000000000001D);
		timeseries.add(new Hour(21, new Day()), 167.30000000000001D);
		timeseries.add(new Hour(23, new Day()), 153.80000000000001D);
		timeseries.add(new Hour(24, new Day()), 167.59999999999999D);
		timeseries.add(new Hour(25, new Day()), 158.80000000000001D);

		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(timeseries);
		return timeseriescollection;
	}

	public ChartPanel getChartPanel() {
		frame1.setPreferredSize(new Dimension(200, 200));
		return frame1;

	}
}
