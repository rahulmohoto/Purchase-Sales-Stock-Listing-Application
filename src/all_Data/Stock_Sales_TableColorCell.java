package all_Data;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class Stock_Sales_TableColorCell implements TableCellRenderer {

	private static final TableCellRenderer Renderer = new DefaultTableCellRenderer();
	
	int interval=2;
	
	int 	gray_range = 7, r_s_gray = 0, r_f_gray = gray_range+r_s_gray-1,
			pink_range = 7, r_s_pink = r_f_gray+interval, r_f_pink = pink_range+r_s_pink-1,
			green_range = 5, r_s_green = r_f_pink+interval, r_f_green = green_range+r_s_green-1,
			magenta_range = 3, r_s_magenta = r_f_green+interval, r_f_magenta = magenta_range+r_s_magenta-1,
			
			gray_range_2 = 4, r_s_gray2 = r_f_magenta+interval, r_f_gray2 = gray_range_2+r_s_gray2-1,
			
			cyan_range = 4, r_s_cyan = r_f_gray2+interval, r_f_cyan = cyan_range+r_s_cyan-1,
			orange_range = 1, r_s_orange = r_f_cyan+interval, r_f_orange = orange_range+r_s_orange-1,
			
			gray_range_3 = 8, r_s_gray3 = r_f_orange+interval, r_f_gray3 = gray_range_3+r_s_gray3-1,
			
			green_range_2 = 2, r_s_green2 = r_f_gray3+interval, r_f_green2 = green_range_2+r_s_green2-1,
			
			yellow_range_2 = 1, r_s_yellow2 = r_f_green2+interval, r_f_yellow2 = yellow_range_2+r_s_yellow2-1,
			
			my_color_range = 2, r_s_myclr = r_f_yellow2+interval, r_f_myclr =  my_color_range+r_s_myclr-1,
			
			pink_range_2 = 2, r_s_pink2 = r_f_myclr+interval, r_f_pink2 = pink_range_2+r_s_pink2-1,
			magenta_range_2 = 3, r_s_magenta2 = r_f_pink2+interval, r_f_magenta2 = magenta_range_2+r_s_magenta2-1,
			
			my_color_range_2 = 4, r_s_myclr2 = r_f_magenta2+interval, r_f_myclr2 =  my_color_range_2+r_s_myclr2-1,
			
			gray_range_4 = 9, r_s_gray4 = r_f_myclr2+interval, r_f_gray4 = gray_range_4+r_s_gray4-1,
			pink_range_3 = 32, r_s_pink3 = r_f_gray4+interval, r_f_pink3 = pink_range_3+r_s_pink3-1,
			green_range_3 = 6, r_s_green3 = r_f_pink3+interval, r_f_green3 = green_range_3+r_s_green3-1,
			my_color_range_3 = 11, r_s_myclr3 = r_f_green3+interval, r_f_myclr3 = my_color_range_3+r_s_myclr3-1;
			
			
			
			

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		Component c = Renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (column == 0) {
			if (row >= r_s_gray && row <= r_f_gray)
				c.setBackground(Color.LIGHT_GRAY);
			if (row >= r_s_pink && row <= r_f_pink)
				c.setBackground(Color.pink);
			if (row >= r_s_green && row <= r_f_green)
				c.setBackground(Color.GREEN);
			if (row >= r_s_magenta && row <= r_f_magenta)
				c.setBackground(Color.magenta);
			if (row >= r_s_gray2 && row <= r_f_gray2)
				c.setBackground(Color.LIGHT_GRAY);
			if (row >= r_s_cyan && row <= r_f_cyan)
				c.setBackground(Color.cyan);
			if (row == r_s_orange)
				c.setBackground(Color.ORANGE);
			if (row >= r_s_gray3 && row<=r_f_gray3)
				c.setBackground(Color.LIGHT_GRAY);
			if (row >= r_s_green2 && row <= r_f_green2)
				c.setBackground(Color.GREEN);
			if (row == r_s_yellow2)
				c.setBackground(Color.YELLOW);
			
			if (row >= r_s_myclr && row <= r_f_myclr) {
				Color myColor = new Color(225, 230, 225);
				c.setBackground(myColor);
			}
			if (row >= r_s_pink2 && row <= r_f_pink2)
				c.setBackground(Color.pink);
			if (row >= r_s_magenta2 && row <= r_f_magenta2)
				c.setBackground(Color.magenta);
			if (row >= r_s_myclr2 && row <= r_f_myclr2) {
				Color myColor = new Color(180, 240, 200);
				c.setBackground(myColor);
			}
			if (row >= r_s_gray4 && row <= r_f_gray4)
				c.setBackground(Color.LIGHT_GRAY);
			if (row >= r_s_pink3 && row <= r_f_pink3)
				c.setBackground(Color.pink);
			if (row >= r_s_green3 && row <= r_f_green3)
				c.setBackground(Color.GREEN);
			if (row >= r_s_myclr3 && row <= r_f_myclr3) {
				Color myColor = new Color(190, 240, 200);
				c.setBackground(myColor);
			}
		} else if (column == 2) {
			Color myColor = new Color(246, 248, 255);
			c.setBackground(myColor);
			c.setForeground(Color.BLACK);
		} else {
			c.setBackground(Color.WHITE);
			c.setForeground(Color.black);
		}

		return c;
	}

}
