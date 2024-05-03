package all_Data;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class StockHistory_TableColorCell implements TableCellRenderer{
	
	int firstElement=0,lastElement=0;

	private static final TableCellRenderer Renderer = new DefaultTableCellRenderer();

	
	public StockHistory_TableColorCell(Vector<Integer> vector) {
		try {
		firstElement = vector.firstElement()-1;
		lastElement = vector.lastElement();
		System.out.println(firstElement);
		}
		catch(Exception Evt) {
		//System.out.println("error");	
		}
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		Component c = Renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if(column==0) {
			Color myColor = new Color (246, 255, 255);
			c.setBackground(myColor);
			if(row>=firstElement && row<lastElement) {
				c.setBackground(Color.red);
			}
		}
		else if(column==1) {
			Color myColor = new Color (246, 248, 255);
			c.setBackground(myColor);
			if(row>=firstElement && row<lastElement) {
				c.setBackground(Color.red);
			}
		}
		else if(column==2) {
			Color myColor = new Color (255, 248, 255);
			c.setBackground(myColor);
			if(row>=firstElement && row<lastElement) {
				c.setBackground(Color.red);
			}
		}
		else {
			c.setBackground(Color.WHITE);
			if(row>=firstElement && row<lastElement) {
				c.setBackground(Color.red);
			}
		}
		c.setForeground(Color.BLACK);
		return c;
	}

}
