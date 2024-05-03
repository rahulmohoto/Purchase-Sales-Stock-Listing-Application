package all_Data;


import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
//import jxl.biff.EmptyCell;
import jxl.read.biff.BiffException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.JScrollPane;
//import javax.swing.JPanel;
//import java.awt.FlowLayout;
//import javax.swing.JButton;
import javax.swing.SwingConstants;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import javax.swing.ImageIcon;

public class SeeHistory {

	private JFrame frmOriginalPrices;
	private JTable tblShowHistory;
	private JScrollPane scroll;
	private Vector<String> headers = new Vector<String>();
	private DefaultTableModel model = null;
	private Vector<Vector<String>> data = new Vector<Vector<String>>();
	private int tableWidth = 0; // set the tableWidth
	private int tableHeight = 0; // set the tableHeight
	private File file;
	private Workbook workbook;
	private Sheet sheet;
//	private JPanel actionPanel;
	private JSONArray products;
	private JLabel noResultLabel;
//	private JButton btnSaveButton;
	

	
	public SeeHistory(String selectedDate) {
//		System.out.println(selectedDate);
		products = readJsonFile(selectedDate);
		String formattedDate = selectedDate.substring(0,4)+"-"+selectedDate.substring(4,6)+"-"+selectedDate.substring(6,8);
//		System.out.println(formattedDate);
		initialize(formattedDate);
	}

	private void initialize(String selectedDate) {
		
		setLook();
		
		frmOriginalPrices = new JFrame();
		frmOriginalPrices.setTitle("History of "+selectedDate);
		frmOriginalPrices.setAlwaysOnTop(true);
		frmOriginalPrices.setAutoRequestFocus(false);
		frmOriginalPrices.setBounds(100, 100, 905, 462);
		frmOriginalPrices.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmOriginalPrices.setVisible(true);
		frmOriginalPrices.getContentPane().setLayout(new BorderLayout(0, 0));
		
		if(products.size() > 0) {
			fn_fill_data();
			fn_render_panel();
		} else {
			noResultLabel = new JLabel("No History Found.", SwingConstants.CENTER);
			noResultLabel.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
			frmOriginalPrices.getContentPane().add(noResultLabel);
		}
		
//		actionPanel = new JPanel();
//		frmOriginalPrices.getContentPane().add(actionPanel, BorderLayout.NORTH);
//		actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
//		btnSaveButton = new JButton("Save Changes");
//		btnSaveButton.setIcon(new ImageIcon(SeeHistory.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
//		btnSaveButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//					FileInputStream fsIP;
//				try {
//					fsIP = new FileInputStream(file);
//					HSSFWorkbook wb = new HSSFWorkbook(fsIP);
//					HSSFSheet worksheet = wb.getSheetAt(0);
//					HSSFCell cell = null;
//					for (int i = 0; i < 109; i++) {
//						cell = worksheet.getRow(i + 1).getCell(3);
//						if(cell!=null) 
//						cell.setCellValue((String) tblShowHistory.getValueAt(i, 3));
//						
//					}
//					fsIP.close();
//					// Open FileOutputStream to write updates
//					FileOutputStream output_file = new FileOutputStream(file);
//					// write changes
//					wb.write(output_file);
//					// close the stream
//					output_file.close();
//					wb.close();
//					JOptionPane.showMessageDialog(tblShowHistory, "Saving Successful!!");
//				} catch (IOException evt) {
//					// TODO Auto-generated catch block
//					evt.printStackTrace();
//				}
//			}
//		});
//		btnSaveButton.setVerticalAlignment(SwingConstants.TOP);
//		btnSaveButton.setHorizontalAlignment(SwingConstants.LEADING);
//		actionPanel.add(btnSaveButton);

	}
	
	void setLook() {
		try {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fn_render_panel() {

		tblShowHistory = new JTable() {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int vColIndex) { 
				if (vColIndex == 0 || vColIndex == 1 || vColIndex == 2 || vColIndex == 3) {
					return false;
				} else {
					return true;
				}
			}
		};
		tblShowHistory.setFillsViewportHeight(true);
		tblShowHistory.setCellSelectionEnabled(true);
		
		tblShowHistory.getTableHeader().setFont(new Font("SutonnyMJ", Font.BOLD, 21));

		tblShowHistory.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.white));
		tblShowHistory.setFont(new Font("SutonnyMJ", Font.BOLD, 19));
		tblShowHistory.setForeground(Color.BLACK);

		tblShowHistory.setModel(model);
		tblShowHistory.setBackground(Color.WHITE);
		tblShowHistory.setRowHeight(25);
		tblShowHistory.setRowMargin(5);

		tblShowHistory.setGridColor(Color.LIGHT_GRAY); // color of the cell grid

		scroll = new JScrollPane(tblShowHistory);
		scroll.setBounds(10, 49, 710, 465);
		scroll.setBackground(Color.white);
		scroll.setPreferredSize(new Dimension(300, 300));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frmOriginalPrices.getContentPane().add(scroll, BorderLayout.CENTER);

		tblShowHistory.setPreferredSize(new Dimension(tableWidth, tableHeight));

		tblShowHistory.setModel(model);
		
//		TableColorCell renderer = new TableColorCell();
//		tblShowHistory.setDefaultRenderer(Object.class, renderer);
		
//		Stock_Sales_TableColorCell render = new Stock_Sales_TableColorCell();
//		tblShowHistory.setDefaultRenderer(Object.class, render);

	}
	
	public void fn_fill_data() {
		
		file = new File("D:\\business_all_stock\\Senpara\\Senpara_main_sales_sheet-History.xls");
		workbook = null;
		int numberOfRows = products.size();
//		System.out.println(numberOfRows);
		try {
			try {
				workbook = Workbook.getWorkbook(file);
			} catch (IOException ex) {
				Logger.getLogger(Distribution_window.class.getName()).log(Level.SEVERE, null, ex);
			}
			sheet = workbook.getSheet(0);

			headers.clear();
			for (int i = 0; i < 3; i++) {
				Cell cell1 = sheet.getCell(i, 0);
				headers.add(cell1.getContents());
			}
			data.clear();
			for (int j = 1; j <= numberOfRows; j++) { //Changed on: 2023-06-21.
				Vector<String> d = new Vector<String>();
				for (int i = 0; i < 2; i++) {

					Cell cell = sheet.getCell(i, j);

					d.add(cell.getContents());	
				}
//				d.addElement(patchFiles(j-1).getContents());
//				d.addElement(patchFiles(j-1));
				JSONObject selectedObject = (JSONObject) products.get(j-1);
				String remaining = (String) selectedObject.get("Remaining");
				d.addElement(remaining);
				
				d.add("\n");
				data.add(d);
			}
		} catch (BiffException e) {
			e.printStackTrace();
		}

		model = new DefaultTableModel(data, headers);
		tableWidth = model.getColumnCount() * 150;
		tableHeight = model.getRowCount() * 30;
	}
	
	public JSONArray readJsonFile(String param) {
//		List<String> fileNames = new ArrayList<String>();
		
		JSONParser parser = new JSONParser();
		JSONArray products = new JSONArray();
	      try {
	    	  String directory = "D:/business_all_stock/Senpara/history/"+param+".json"; //"D:/business_all_stock/Senpara/files.json";
//	    	  System.out.println(directory);
	    	  
	    	  JSONObject object = (JSONObject) parser.parse(new FileReader(directory));
	    	  products = (JSONArray) object.get("summary");

//	    	 System.out.println(products);
	        
	      } catch(Exception e) {
//	         e.printStackTrace();
	    	  System.out.println("File Not Found");
	      }
	      
	   return products;
	}

}
