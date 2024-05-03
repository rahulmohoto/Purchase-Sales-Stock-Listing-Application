package all_Data;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.biff.EmptyCell;
import jxl.read.biff.BiffException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.GridLayout;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class Stock_Senpara implements Runnable{

	static JTable table;
	private JScrollPane scroll;
	// header is Vector contains table Column
	private Vector<String> headers = new Vector<String>();
	// Model is used to construct JTable
	private DefaultTableModel model = null;
	// data is Vector contains Data from Excel File
	private Vector<Vector<String>> data = new Vector<Vector<String>>();
	private int tableWidth = 0; // set the tableWidth
	private int tableHeight = 0; // set the tableHeight
	private File file;

	private Workbook workbook;
	private Sheet sheet;
	public String date;

	static JFrame frmStockSheetSenpara;
	private JPanel Table_panel;
	private JPanel Action_panel;
	private JButton btnCustomer;
	private JLabel lblDelivery;
	private JButton btnStockEntry;
	private JButton btnStockEntryBoroghor;
	private JButton btnStockRemove;
	private JButton btnStockRemoveBoroghor;
	private JButton btnStockHistory;
	private JPanel panel;
	private JButton btnHome;
	private JLabel lblDate;
	private JLabel lblShowDate;
	private JPanel Operation_panel;
	private JButton btnGenerateTable;
	private JButton btnSaveTable;
	private JButton btnResetTable;
	private String customer = "Customer\nPurchase";
	private String stock_senpara="Stock\nSENPARA";
	private String sales_senpara="Sales\nSENPARA";
	private String sales_boroghor="Sales\nBOROGHOR";
	private String stock_boroghor="Stock\nBOROGHOR";
	private String stock_history="Stock\nHistory";
	
	private boolean isWindowOverlayedStockSenpara = false;
	
//	private int numberOfRows = 128;
	private int numberOfRows = MainWindow.filesSenpara.size(); // Dynamic Loading. Changed on: 2023-06-21.

	public Stock_Senpara() {
		initialize();
	}

	// setting look and Feel of the application
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

		private void initialize() {
		
		date = java.time.LocalDate.now().toString();
		
		setLook();

		frmStockSheetSenpara = new JFrame();
		frmStockSheetSenpara.setAutoRequestFocus(false);
		frmStockSheetSenpara.setTitle("Senpara Stock Page ");
		frmStockSheetSenpara.setBounds(100, 100, 1260, 720);
		frmStockSheetSenpara.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmStockSheetSenpara.setExtendedState(frmStockSheetSenpara.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frmStockSheetSenpara.getContentPane().setLayout(new BorderLayout(0, 0));
		
		frmStockSheetSenpara.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				frame_windowStateChanged_HasFocus(e);
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				frame_windowStateChanged_LostFocus(e);
			}
			
		});

		Operation_panel = new JPanel();
		Operation_panel.setBackground(new Color(245, 255, 250));
		Operation_panel.setForeground(Color.BLACK);
		frmStockSheetSenpara.getContentPane().add(Operation_panel, BorderLayout.NORTH);
		Operation_panel.setLayout(new BoxLayout(Operation_panel, BoxLayout.X_AXIS));

		lblDelivery = new JLabel("     Senpara Stock Page    ");
		lblDelivery.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblDelivery.setForeground(Color.RED);
		lblDelivery.setHorizontalAlignment(SwingConstants.CENTER);
		Operation_panel.add(lblDelivery);

		btnGenerateTable = new JButton("Print ");
		btnGenerateTable.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		btnGenerateTable.setIcon(new ImageIcon(Distribution_window.class.getResource("/all_Data/generateTable.png")));
		btnGenerateTable.setBackground(new Color(135, 206, 235));
		btnGenerateTable.setForeground(Color.BLACK);
		btnGenerateTable.setMultiClickThreshhold(250);
		btnGenerateTable.setContentAreaFilled(false);
		btnGenerateTable.setBorder(new LineBorder(new Color(255, 248, 220)));
		btnGenerateTable.setOpaque(true);
		btnGenerateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String options[]= {"Yes","No"};
				
				if(MainWindow.isSaved==false) {
					int x = JOptionPane.showOptionDialog(null, "Do you want to save?",
			                "Make your Choice",
			                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			        if(x==0) {
			        	for (int i = 0; i <= numberOfRows; i++) { //loop runs till reaches to numberOfRows. Changed on: 2023-06-21.
							if (MainWindow.updatedRow[i] != 0) {
								writeFiles(i, MainWindow.updatedRow[i]);
							}
						}
			        	MainWindow.isSaved=true;
			        } 
				}
				new Print_Stock("stock_senpara");
				
				//New Updated
				isWindowOverlayedStockSenpara = false;
			}
		});
		Operation_panel.add(btnGenerateTable);
		
		btnSaveTable = new JButton("Save Stock   ");
		btnSaveTable.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		btnSaveTable.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSaveTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MainWindow.isSaved==false) {
				for (int i=0;i<=numberOfRows;i++) { //Loop runs till reaches to numberOfRows. Changed on: 2023-06-21.
					if(MainWindow.updatedRow[i]!=0) {
						writeFiles(i,MainWindow.updatedRow[i]);
					}
				}
				JOptionPane.showMessageDialog(frmStockSheetSenpara, "Saving Successful!!");
				MainWindow.isSaved=true;
				
				//New Updated
				isWindowOverlayedStockSenpara = false;
				}
			}
		});
		btnSaveTable.setIcon(new ImageIcon(Distribution_window.class.getResource("/all_Data/save.png")));
		btnSaveTable.setMultiClickThreshhold(250);
		btnSaveTable.setBackground(new Color(135, 206, 235));
		btnSaveTable.setForeground(Color.BLACK);
		btnSaveTable.setContentAreaFilled(false);
		btnSaveTable.setBorder(new LineBorder(Color.white));
		btnSaveTable.setOpaque(true);
		Operation_panel.add(btnSaveTable);
		
		btnResetTable = new JButton("Refresh  ");
		btnResetTable.setMultiClickThreshhold(250);
		btnResetTable.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		btnResetTable.setAlignmentX(0.5f);
		btnResetTable.setIcon(new ImageIcon(Distribution_window.class.getResource("/all_Data/reset.png")));
		btnResetTable.setMultiClickThreshhold(250);
		btnResetTable.setBackground(new Color(135, 206, 235));
		btnResetTable.setForeground(Color.BLACK);
		btnResetTable.setContentAreaFilled(false);
		btnResetTable.setBorder(new LineBorder(Color.white));
		btnResetTable.setOpaque(true);
		btnResetTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fn_RefreshTable();
			}
		});
		Operation_panel.add(btnResetTable);

		panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		Operation_panel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		btnHome = new JButton("");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainWindow();
				Arrays.fill(MainWindow.updatedRow, 0);
				frmStockSheetSenpara.dispose();
			}
		});
		btnHome.setMultiClickThreshhold(250);
		btnHome.setOpaque(true);
		btnHome.setMultiClickThreshhold(250);
		btnHome.setContentAreaFilled(false);
		btnHome.setIcon(new ImageIcon(Distribution_window.class.getResource("/all_Data/home.png")));
		panel.add(btnHome, BorderLayout.EAST);
		
		lblDate = new JLabel("");
		lblDate.setFont(new Font("Stencil", Font.PLAIN, 17));
		lblDate.setText(date);
		panel.add(lblDate, BorderLayout.CENTER);
		
		lblShowDate = new JLabel("  Today(YY-MM-DD) ::  ");
		lblShowDate.setFont(new Font("Stencil", Font.PLAIN, 15));
		panel.add(lblShowDate, BorderLayout.WEST);

		fn_fill_data();
		fn_render_panel();

		Action_panel = new JPanel();
		frmStockSheetSenpara.getContentPane().add(Action_panel, BorderLayout.WEST);

		btnCustomer = new JButton("<html>" + customer.replaceAll("\\n", "<br>") + "</html>");
		btnCustomer.setMultiClickThreshhold(250);
		btnCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		btnCustomer.setIcon(new ImageIcon(Distribution_window.class.getResource("/all_Data/customer.png")));
		btnCustomer.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		btnCustomer.setContentAreaFilled(false);
		btnCustomer.setBackground(new Color(211, 211, 211));
		btnCustomer.setForeground(Color.BLACK);
		btnCustomer.setOpaque(true);
		btnCustomer.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		Action_panel.setLayout(new GridLayout(0, 1, 0, 0));
		Action_panel.add(btnCustomer);

		btnStockEntry = new JButton("<html>" + stock_senpara.replaceAll("\\n", "<br>") + "</html>");
		btnStockEntry.setMultiClickThreshhold(250);
		btnStockEntry.setHorizontalAlignment(SwingConstants.LEFT);
		btnStockEntry.setIcon(new ImageIcon(Distribution_window.class.getResource("/all_Data/data_store.png")));
		btnStockEntry.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		btnStockEntry.setContentAreaFilled(false);
		btnStockEntry.setBackground(Color.WHITE);
		btnStockEntry.setForeground(Color.BLACK);
		btnStockEntry.setOpaque(true);
		btnStockEntry.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		Action_panel.add(btnStockEntry);
		
		btnStockRemove = new JButton("<html>" + sales_senpara.replaceAll("\\n", "<br>") + "</html>");
		btnStockRemove.setMultiClickThreshhold(250);
		btnStockRemove.setHorizontalAlignment(SwingConstants.LEFT);
		btnStockRemove.setIcon(new ImageIcon(Distribution_window.class.getResource("/all_Data/remove_data.png")));
		btnStockRemove.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		btnStockRemove.setContentAreaFilled(false);
		btnStockRemove.setBackground(new Color(211, 211, 211));
		btnStockRemove.setForeground(Color.BLACK);
		btnStockRemove.setOpaque(true);
		btnStockRemove.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		Action_panel.add(btnStockRemove);
		
		btnStockEntryBoroghor = new JButton("<html>" + stock_boroghor.replaceAll("\\n", "<br>") + "</html>");
		btnStockEntryBoroghor.setHorizontalAlignment(SwingConstants.LEFT);
		btnStockEntryBoroghor.setIcon(new ImageIcon(MainWindow.class.getResource("/all_Data/data_store_2.png")));
		btnStockEntryBoroghor.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		btnStockEntryBoroghor.setContentAreaFilled(false);
		btnStockEntryBoroghor.setBackground(new Color(211, 211, 211));
		btnStockEntryBoroghor.setForeground(Color.BLACK);
		btnStockEntryBoroghor.setOpaque(true);
		btnStockEntryBoroghor.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		Action_panel.add(btnStockEntryBoroghor);
		
		btnStockRemoveBoroghor = new JButton("<html>" + sales_boroghor.replaceAll("\\n", "<br>") + "</html>");
		btnStockRemoveBoroghor.setHorizontalAlignment(SwingConstants.LEFT);
		btnStockRemoveBoroghor.setIcon(new ImageIcon(MainWindow.class.getResource("/all_Data/remove_data_2.png")));
		btnStockRemoveBoroghor.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		btnStockRemoveBoroghor.setContentAreaFilled(false);
		btnStockRemoveBoroghor.setBackground(new Color(211, 211, 211));
		btnStockRemoveBoroghor.setForeground(Color.BLACK);
		btnStockRemoveBoroghor.setOpaque(true);
		btnStockRemoveBoroghor.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		Action_panel.add(btnStockRemoveBoroghor);

		btnStockHistory = new JButton("<html>" + stock_history.replaceAll("\\n", "<br>") + "</html>");
		btnStockHistory.setMultiClickThreshhold(250);
		btnStockHistory.setHorizontalAlignment(SwingConstants.LEFT);
		btnStockHistory.setIcon(new ImageIcon(Distribution_window.class.getResource("/all_Data/history.png")));
		btnStockHistory.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		btnStockHistory.setContentAreaFilled(false);
		btnStockHistory.setBackground(new Color(211, 211, 211));
		btnStockHistory.setForeground(Color.BLACK);
		btnStockHistory.setOpaque(true);
		btnStockHistory.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		Action_panel.add(btnStockHistory);
	}
		//New Window Status
		public void frame_windowStateChanged_HasFocus(WindowEvent e) {
			if(frmStockSheetSenpara.isFocused() && (!MainWindow.modifiedRowVectorSalesSenpara.isEmpty() || !isWindowOverlayedStockSenpara)) {
				Thread object = new Thread(this); 
		        object.start();
			}
		}
		
		public void frame_windowStateChanged_LostFocus(WindowEvent e) {
			if(!MainWindow.modifiedRowVectorSalesSenpara.isEmpty()) {
				isWindowOverlayedStockSenpara = false;
			}
			else
			isWindowOverlayedStockSenpara = true;
		}
		

	Action action = new AbstractAction() {
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
		int noOfEntry=0,remaining=0, numberOfRowsUsed=0;
			TableCellListener tcl = (TableCellListener) e.getSource();
			try {
				numberOfRowsUsed = getRemainingRows(tcl.getRow());
				if(numberOfRowsUsed>=MainWindow.threshold) {
					
					String options[]= {"Yes","No"};
					
					int x = JOptionPane.showOptionDialog(null, "You are reaching out-of-list. Want to Save?",
				                "Make your Choice",
				                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					if(x==0) {
						
						String lastBalance = patchFiles(tcl.getRow()).getContents().toString();
//						StockHistory.removeCellData("Senpara",MainWindow.filesSenpara[tcl.getRow()],lastBalance);
						StockHistory.removeCellData("Senpara",MainWindow.filesSenpara.get(tcl.getRow()),lastBalance); //Changed on: 2023-06-08
//						System.out.println("Okay!!");
				        isWindowOverlayedStockSenpara = true;
					}
					
					
				}
			} catch(Exception evt) {
//				System.out.println("Problem Occured");
			}
			try {
				noOfEntry = Integer.parseInt((String) table.getValueAt(tcl.getRow(), 2));
			} catch (Exception evt) {
				noOfEntry = 0;
			}
			MainWindow.updatedRow[tcl.getRow()]=noOfEntry;
			try {
				remaining = Integer.parseInt(patchFiles(tcl.getRow()).getContents());
			} catch (Exception evt) {
				remaining = 0;
			}
			
			remaining = remaining+noOfEntry;
			if(noOfEntry!=0)
				table.setValueAt(Integer.toString(remaining), tcl.getRow(), 3);
			else
				table.setValueAt(patchFiles(tcl.getRow()).getContents(), tcl.getRow(), 3);
			
			MainWindow.modifiedCellVectorStockSenpara.add(tcl.getRow());
			MainWindow.modifiedRowVectorStockSenpara.add(tcl.getRow());
		}

	};
	
	public void fn_render_panel() {

		Table_panel = new JPanel();
		frmStockSheetSenpara.getContentPane().add(Table_panel, BorderLayout.CENTER);
		Table_panel.setLayout(new GridLayout(0, 1, 0, 0));

		table = new JTable() {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int vColIndex) { // for disabling a cell edit for user
				if (vColIndex == 0 || vColIndex == 1 || vColIndex == 3) {
					return false;
				} else {
					return true;
				}
			}
		};
		table.setFillsViewportHeight(true);
		//style table header
		table.getTableHeader().setFont(new Font("SutonnyMJ", Font.BOLD, 21));
		
		@SuppressWarnings("unused")
		TableCellListener tcl = new TableCellListener(table, action);
		// table.setSurrendersFocusOnKeystroke(true);
		table.setCellSelectionEnabled(true);

		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.white));
		table.setFont(new Font("SutonnyMJ", Font.BOLD, 20));
		table.setForeground(Color.BLACK);

		table.setModel(model);
		table.setBackground(Color.WHITE);
		table.setEnabled(true);
		table.setRowHeight(30);
		table.setRowMargin(2);

		table.setGridColor(Color.LIGHT_GRAY); // color of the cell grid

		scroll = new JScrollPane(table);
		Table_panel.add(scroll);
		scroll.setBounds(10, 49, 710, 465);
		scroll.setBackground(Color.white);
		scroll.setPreferredSize(new Dimension(300, 300));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		table.setPreferredSize(new Dimension(tableWidth, tableHeight));

		table.setModel(model);
		
		Stock_Sales_TableColorCell render = new Stock_Sales_TableColorCell();
        table.setDefaultRenderer(Object.class, render);

	}

	public void fn_fill_data() {

		file = new File("D:\\business_all_stock\\Senpara\\Senpara_main_stock_sheet.xls");
		workbook = null;
		try {
			try {
				workbook = Workbook.getWorkbook(file);
			} catch (IOException ex) {
				Logger.getLogger(Distribution_window.class.getName()).log(Level.SEVERE, null, ex);
			}
			sheet = workbook.getSheet(0);

			headers.clear();
			for (int i = 0; i < 4; i++) {
				Cell cell1 = sheet.getCell(i, 0);
				headers.add(cell1.getContents());
			}

			data.clear();
			for (int j = 1; j <= numberOfRows; j++) { //loop runs till reaches to numberOfRows. Changed on: 2023-06-21.
				Vector<String> d = new Vector<String>();
				for (int i = 0; i < 3; i++) {

					Cell cell = sheet.getCell(i, j);

					d.add(cell.getContents());	
				}
				d.addElement(patchFiles(j-1).getContents());
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
	
	
	private Cell patchFiles(int row) {	
			
//			File tempFile=new File("D:\\business_all_stock\\Senpara\\"+MainWindow.filesSenpara[row]+".xls");
			File tempFile=new File("D:\\business_all_stock\\Senpara\\"+MainWindow.filesSenpara.get(row)+".xls"); //Changed on: 2023-06-08
			Workbook tempWorkbook=null;
			try {
				tempWorkbook = Workbook.getWorkbook(tempFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Sheet tempSheet=tempWorkbook.getSheet(0);
			Cell tempCell = tempSheet.getCell(3,500);
			if (tempCell instanceof EmptyCell) {
				return (EmptyCell) tempCell;
			}
			return tempCell;
			//closing operations
		}
		
		
	private int getRemainingRows(int row) {
			try {
//			File tempFile = new File("D:\\business_all_stock\\Senpara\\"+MainWindow.filesSenpara[row]+".xls");
			File tempFile = new File("D:\\business_all_stock\\Senpara\\"+MainWindow.filesSenpara.get(row)+".xls");	//Changed on: 2023-06-08.
			
			FileInputStream fsIP = new FileInputStream(tempFile);
			HSSFWorkbook wb = new HSSFWorkbook(fsIP);
			HSSFSheet worksheet = wb.getSheetAt(0);
			
			HSSFCell cellLastEdit = worksheet.getRow(1).getCell(4);
			int last_edit_row_number = (int)Float.parseFloat(cellLastEdit.toString());
			
			fsIP.close();
			wb.close();
			
			return last_edit_row_number;
			
			} catch(Exception evt) {
				return 0;
			}
		}
		
		
	private void writeFiles(int index,int value) {
			
			try {
//				File tempFile = new File("D:\\business_all_stock\\Senpara\\"+MainWindow.filesSenpara[index]+".xls");
				File tempFile = new File("D:\\business_all_stock\\Senpara\\"+MainWindow.filesSenpara.get(index)+".xls"); //Changed on 2023-06-08
				
				FileInputStream fsIP = new FileInputStream(tempFile);
				HSSFWorkbook wb = new HSSFWorkbook(fsIP);
				HSSFSheet worksheet = wb.getSheetAt(0);
				
				HSSFCell cellLastEdit = worksheet.getRow(1).getCell(4);
				int last_edit_row_number = (int)Float.parseFloat(cellLastEdit.toString());
				
				HSSFCell cellData = worksheet.getRow(last_edit_row_number+1).getCell(1);
				cellData.setCellValue(value);
				HSSFCell cellDate = worksheet.getRow(last_edit_row_number+1).getCell(0);
				cellDate.setCellValue(date);
				cellLastEdit.setCellValue(++last_edit_row_number);
				HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
				fsIP.close();
				// Open FileOutputStream to write updates
				FileOutputStream output_file = new FileOutputStream(tempFile);
				wb.write(output_file);
				output_file.close();
				wb.close();
				} catch (IOException evt) {
				// TODO Auto-generated catch block
				evt.printStackTrace();
			}
			
		}
		
		
	private void fn_RefreshTable() {
			//being ready
			MainWindow.isSaved=false;
			Arrays.fill(MainWindow.updatedRow, 0);
			frmStockSheetSenpara.setEnabled(false);
			
			//ready
			Iterator<Integer> it = MainWindow.modifiedCellVectorStockSenpara.iterator();
			while(it.hasNext()) {
				int j=it.next();
//				if(MainWindow.filesSenpara[j]=="blank") {
//					table.setValueAt("", j, 3);
//				}
				if(MainWindow.filesSenpara.get(j)=="blank") { //Changed on 2023-06-08.
					table.setValueAt("", j, 3);
				}
				else
				{
					table.setValueAt(patchFiles(j).getContents().toString(), j, 3);
					table.setValueAt("", j, 2);
				}
			}
			MainWindow.modifiedCellVectorStockSenpara.clear();
			
			//done
			frmStockSheetSenpara.setEnabled(true);
			
		}

		@Override
	public void run() {
			// TODO Auto-generated method stub1
			fn_RefreshTable();

			Iterator<Integer> i = MainWindow.modifiedRowVectorSalesSenpara.iterator();
			while(i.hasNext()) {
				int j = i.next();
				String s = patchFiles(j).getContents().toString();
				table.setValueAt(s, j, 3);
			}
			
			isWindowOverlayedStockSenpara = false;
			MainWindow.modifiedRowVectorSalesSenpara.clear();
		}
}
