import java.util.Vector;

import javax.swing.table.AbstractTableModel;

class APTableModel extends AbstractTableModel {
    private static final long serialVersionUID = -3094977414157589758L;

    private Vector content = null;

    
    public APTableModel() {
        content = new Vector();
    }

    public APTableModel(int count) {
        content = new Vector(count);
    }

    /** 
     * 加入一空行 
     * @param row 行号 
     */
    public void addRow(int row) {
        Vector v = new Vector(AP.title_name.length);
        for(int i=0;i<AP.title_name.length;i++)
        	v.add(i, null);
        content.add(row, v);
    }

    /** 
     * 加入一行内容 
     */
    public void addRow(AP ap) {
        Vector v = new Vector(AP.title_name.length);
        int index = 0;
        for(Object s:ap.getAP()) {
        	v.add(index++,s);
        }
        content.add(v);
    }

    public void removeRow(int row) {
        content.remove(row);
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
       return false;
    }

    public void setValueAt(Object value, int row, int col) {
        ((Vector) content.get(row)).remove(col);
        ((Vector) content.get(row)).add(col, value);
        this.fireTableCellUpdated(row, col);
    }

    public String getColumnName(int col) {
        return AP.title_name[col];
    }

    public int getColumnCount() {
        return AP.title_name.length;
    }

    public int getRowCount() {
        return content.size();
    }

    public Object getValueAt(int row, int col) {
        return ((Vector) content.get(row)).get(col);
    }

    public Class getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }

}