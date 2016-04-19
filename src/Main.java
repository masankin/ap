import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class Main {
	JFrame frame = new JFrame();
	APTableModel model = new APTableModel();
	public void init() {
		// �˵�
		initMenu();
		// ��߱��
		initLeft();
		// �ұ�ͼ��
		initRight();
		frame.setTitle("AP");
		frame.setSize(800, 500);
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
	}
	// menu
	public void initMenu() {
		
		 Container content = frame.getContentPane();
		 JMenuBar menubar = new JMenuBar();
		 JMenu menu = new JMenu("�˵�");
		 // �˵���ť
		 JMenuItem item[] = new JMenuItem[]{new JMenuItem("����")};
		 for(JMenuItem it : item) {
			 menu.add(it);
		 }
		 menubar.add(menu);
		 // ���߰�ť
		 JMenu tools[] = new JMenu[]{new JMenu("�ŵ�ռ��"),new JMenu("������ʾ"),new JMenu("�˳�ɨ��")};
		 for(JMenu m : tools) {
			 menubar.add(m);
		 }
		 content.add(menubar,BorderLayout.NORTH);
	}
	// Left
	public void initLeft() {
		Container content = frame.getContentPane();
		final JTable table = new JTable(model);
		table.setRowHeight(50);
		content.add(new JScrollPane(table), BorderLayout.CENTER);
		// table data
		for(AP p:AP.getDatas()) {
			model.addRow(p);
		}
		// resize to middle
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
	       render.setHorizontalAlignment(SwingConstants.CENTER);
	    ImageCell imgcell = new ImageCell();
	    table.getTableHeader().setDefaultRenderer(render);
	    for(int i=0;i<table.getColumnModel().getColumnCount();i++) {
	    	if(i == 4)
	    		table.getColumnModel().getColumn(i).setCellRenderer(imgcell);
	    	else
	    		table.getColumnModel().getColumn(i).setCellRenderer(render);
	    }
	    // click table
	    table.addMouseListener(new MouseAdapter() {
	    	   /** *//**
	    	      * ��굥���¼�
	    	      * @param e �¼�Դ����
	    	      */
	    	   public void mouseClicked(MouseEvent e){
	    	    System.out.println("Single Clicked!");
	    	    int rowI  = table.rowAtPoint(e.getPoint());// �õ�table���к�
	    	    if (rowI > -1)
	    	        System.out.println("������� "+((APTableModel)table.getModel()).getValueAt(rowI, 0));
	    	     }  
	    	  });
		
	}
	// Right-jfreechart
	public void initRight() {
		Container content = frame.getContentPane();
		JPanel jp = new JPanel(new GridLayout(2,1));
		PieChart pie = new PieChart();
		TimeSeriesChart time = new TimeSeriesChart();
		jp.add(pie.getChartPanel());
		jp.add(time.getChartPanel());
		//jp.add(new JLabel(new ImageIcon("image/5.png")));
		content.add(jp, BorderLayout.EAST);
	}
	public static void main(String args[]) {
		Main m = new Main();
		m.init();
	}
}

