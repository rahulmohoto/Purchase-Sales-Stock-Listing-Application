package all_Data;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableColorCellRenderer implements TableCellRenderer{
	
	private static final TableCellRenderer Renderer = new DefaultTableCellRenderer();
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		// TODO Auto-generated method stub
		Component c = Renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if(column==0) {
			if(row>=0 && row<=6)
			c.setBackground(Color.LIGHT_GRAY);
			if(row>=8 && row<=14)
			c.setBackground(Color.pink);
			if(row>=16 && row<=20)
			c.setBackground(Color.GREEN);
			if(row>=22 && row<=24)
			c.setBackground(Color.magenta);
			if(row>=26 && row<=29)
			c.setBackground(Color.cyan);
			if(row>=31 && row<=34)
			c.setBackground(Color.ORANGE);
			if(row>=36 && row<=41)
			c.setBackground(Color.red);
			if(row>=43 && row<=44)
			c.setBackground(Color.YELLOW);
			if(row>=46 && row<=47)
			c.setBackground(Color.LIGHT_GRAY);
			if(row>=49 && row<=51)
			c.setBackground(Color.pink);
			if(row>=53 && row<=56)
			c.setBackground(Color.GREEN);
			if(row>=58 && row<=65)
			c.setBackground(Color.YELLOW);
			if(row>=67 && row<=79)
			c.setBackground(Color.ORANGE);
			if(row>=81 && row<=86)
			c.setBackground(Color.pink);
		}
		else if(column==2) {
			Color myColor = new Color (246, 248, 255);
			c.setBackground(myColor);
			c.setForeground(Color.BLACK);
		}
		else {
			c.setBackground(Color.WHITE);
			c.setForeground(Color.black);
		}
		return c;
	}

}
