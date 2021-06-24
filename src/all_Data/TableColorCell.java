package all_Data;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableColorCell implements TableCellRenderer {

	private static final TableCellRenderer Renderer = new DefaultTableCellRenderer();

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		int interval = 2;
		
		int gray_range = 7, r_s_gray = 0, r_f_gray = gray_range+r_s_gray-1,
			pink_range = 6, r_s_pink = r_f_gray+interval, r_f_pink = pink_range+r_s_pink-1,
			green_range = 5, r_s_green = r_f_pink+interval, r_f_green = green_range+r_s_green-1,
			magenta_range = 3, r_s_magenta = r_f_green+interval, r_f_magenta = magenta_range+r_s_magenta-1,
			cyan_range = 6, r_s_cyan = r_f_magenta+interval, r_f_cyan = cyan_range+r_s_cyan-1,
			orange_range = 2, r_s_orange = r_f_cyan+interval, r_f_orange = orange_range+r_s_orange-1,
			red_range = 2, r_s_red = r_f_orange+interval, r_f_red = red_range+r_s_red-1,
			yellow_range = 3, r_s_yellow = r_f_red+interval, r_f_yellow = yellow_range+r_s_yellow-1,
			
			gray_range_2 = 4, r_s_gray2 = r_f_yellow+interval, r_f_gray2 = gray_range_2+r_s_gray2-1,
			pink_range_2 = 8, r_s_pink2 = r_f_gray2+interval, r_f_pink2 = pink_range_2+r_s_pink2-1,
			green_range_2 = 21, r_s_green2 = r_f_pink2+interval, r_f_green2 = green_range_2+r_s_green2-1,
			yellow_range_2 = 6, r_s_yellow2 = r_f_green2+interval, r_f_yellow2 = yellow_range_2+r_s_yellow2-1,
			
			gray_range_3 = 23, r_s_gray3 = r_f_yellow2+interval, r_f_gray3 = gray_range_3+r_s_gray3-1;
				
				
		Component c = Renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (column == 0) {
			if(row>=r_s_gray && row<=r_f_gray)
				c.setBackground(Color.LIGHT_GRAY);
				if(row>=r_s_pink && row<=r_f_pink)
				c.setBackground(Color.pink);
				if(row>=r_s_green && row<=r_f_green)
				c.setBackground(Color.GREEN);
				if(row>=r_s_magenta && row<=r_f_magenta)
				c.setBackground(Color.magenta);
				if(row>=r_s_cyan && row<=r_f_cyan)
				c.setBackground(Color.cyan);
				if(row>=r_s_orange && row<=r_f_orange)
				c.setBackground(Color.ORANGE);
				if(row>=r_s_red && row<=r_f_red)
				c.setBackground(Color.red);
				if(row>=r_s_yellow && row<=r_f_yellow)
				c.setBackground(Color.YELLOW);
				if(row>=r_s_gray2 && row<=r_f_gray2)
				c.setBackground(Color.LIGHT_GRAY);
				if(row>=r_s_pink2 && row<=r_f_pink2)
				c.setBackground(Color.pink);
				if(row>=r_s_green2 && row<=r_f_green2)
				c.setBackground(Color.GREEN);
				if(row>=r_s_yellow2 && row<=r_f_yellow2)
				c.setBackground(Color.YELLOW);
				if(row>=r_s_gray3 && row<=r_f_gray3)
				c.setBackground(Color.LIGHT_GRAY);
		} else if (column == 3) {
			Color myColor = new Color(246, 240, 255);
			c.setBackground(myColor);
			c.setForeground(Color.BLACK);
		} else if (column == 4) {
			Color myColor = new Color(246, 248, 255);
			c.setBackground(myColor);
			c.setForeground(Color.BLACK);
		} else if (column == 6) {
			Color myColor = new Color(246, 236, 255);
			c.setBackground(myColor);
			c.setForeground(Color.BLACK);
		} else {
			c.setBackground(Color.WHITE);
			c.setForeground(Color.black);
		}
		return c;
	}

}
