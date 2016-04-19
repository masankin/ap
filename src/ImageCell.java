import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageCell extends DefaultTableCellRenderer {
    DefaultTableCellRenderer renderer=new DefaultTableCellRenderer(); 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,   
            boolean isSelected, boolean hasFocus, int row, int column) {   
        if(column==4){
        	//System.out.println(value);
        	ImageIcon img = new ImageIcon("image/"+value+".png");
        	//img.
        	JLabel jbl = new JLabel(img);
        	jbl.setPreferredSize(new Dimension(30,10));
            return jbl;
        }else{
            return super.getTableCellRendererComponent(table, value, isSelected,hasFocus, row, column);
        }
    }   
}
