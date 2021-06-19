package all_Data;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
import jxl.read.biff.BiffException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.JRadioButton;

public class StockHistory {

	static JTable historyTable;
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
	private String date;

	private JFrame frmStockHistory;
	private JButton btnResetTable;
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
	private JLabel lblShowDate;
	private JLabel lblDate;
	private String year[] = { "Select Year", "2020", "2021", "2022", "2023" };
	private String month[] = { "Select Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	private String day[] = { "Select Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" };
	private String boroghorCategory[] = { 
			"8Lfresh","5Lfresh","3Lfresh","2Lfresh","1Lfresh","500mLfresh","250mLfresh",
			"bodhuChinigura","25kgChinigura","notunChabiChinigura","puratonChabiChinigura","puratonKobutorChinigura","notunKobutorChinigura","Amin1KgChinigura",
			"shaplaRajbhog","rajaniRajbhog","golapRajbhog","kobutorRajbhog","25kgRajbhog",
			"appleKatari","khudi","25kgShiddhoKatari",
			"Kobutor50KgMiniket","Kobutor25KgMiniket","Amin50KgMiniket","Amin25KgMiniket",
			"Kobutor50Kg28","Kobutor25Kg28","Amin50Kg28","Amin25Kg28",
			"1lChaka","500mlChaka","250mlChaka","200mlChaka","100mlChaka","50mlChaka",
			"1kgChini","500gmChini",
			"1kgSalt","500gmSalt",
			"Aata","500gmSuji","200gmSuji",
			"chikonMug","25kgChikonMushur","50kgMotaMushur","25kgMotaMushur",
			"7pusti","18pusti","50pusti","100pusti","200pusti","325pusti","500pusti","1kgPusti",
			"Aararut","kaporSoda","khabarSoda","Aamonia","shabu","kismis","iraniJira","AaluBokhra","jotrik","darchini","shadaMorich","loong","dalda",
			"500dano","400dano","200dano","100dano","50dano","20dano"
			};
	private String senparaCategory[] = {
			"8Lfresh","5Lfresh","3Lfresh","2Lfresh","1Lfresh","500mLfresh","250mLfresh",
			"bodhuChinigura","25kgChinigura","puratonChabiChinigura","notunChabiChinigura","puratonKobutorChinigura","notunKobutorChinigura","Amin1KgChinigura",
			"shaplaRajbhog","rajaniRajbhog","golapRajbhog","kobutorRajbhog","25kgRajbhog",
			"Kobutor50KgMiniket","Kobutor25KgMiniket","Amin50KgMiniket","Amin25KgMiniket",
			"Kobutor50Kg28","Kobutor25Kg28","Amin50Kg28","Amin25Kg28",
			"appleKatari","50kgPaijamKatari","25kgShiddhoKatari",
			"pitharChal",
			"1lChaka","500mlChaka","250mlChaka","200mlChaka","100mlChaka","50mlChaka","8kgChaka","16kgChaka",
			"8kgChadghora","16kgChadghora",
			"8kgParachut",
			"1kgChini","500gmChini",
			"1kgSalt","500gmSalt",
			"Aata","500gmSuji","200gmSuji",
			"chikonMug","25kgChikonMushur","50kgMotaMushur","25kgMotaMushur",
			"7pusti","18pusti","50pusti","100pusti","200pusti","325pusti","500pusti","1kgPusti","2kgPusti",
			"Aararut","kaporSoda","khabarSoda","Aamonia","shabu","kismis","iraniJira","AaluBokhra","joyfol","jotrik","darchini","shadaMorich","kaloGolmorich","kalaJira","loong","dalda","chotoElach","boroElach","vedan",
			"100gmMasu","450gmMasu",
			"methi","muhuri","dhonia","khudi","missri","rong","neel","dhup","shutli","butter","baking",
			"500dano","400dano","200dano","100dano","50dano","20dano",
			"1Lpure","500mLpure","1Lpoly","500mLpoly","1KGmushur","500GMmushur",
			"500mLshorisha","250mLshorisha","100mLshorisha","80mLshorisha"
			};
	private JPanel pnlCustomPanel;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxDay;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxMonth;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxYear;
	private JPanel panel_2;
	private JComboBox<Object> comboBoxCategory;
	private JLabel lblSelectDateMonth;
	private JLabel lblSelectProductsTo;
	private JLabel lblBlankLabel;
	private JLabel lblBlankLabel2;
	private JLabel lblchecklist;
	private JLabel lblBlankLabel3;
	private JButton btnSearchButton;
	private String selectedProduct=null;
	private String selectedGodown=null;
	private JButton btnClearCells;
	private JRadioButton radiobtnSenpara;
	private JRadioButton radiobtnBoroghor;
	private ButtonGroup G1;
	private JPanel Operation_panel;
	private String customer = "Customer\nPurchase";
	private String stock_senpara="Stock\nSENPARA";
	private String sales_senpara="Sales\nSENPARA";
	private String sales_boroghor="Sales\nBOROGHOR";
	private String stock_boroghor="Stock\nBOROGHOR";
	private String stock_history="Stock\nHistory";
	private String selectedDay=null;
	private String selectedMonth=null;
	private String selectedYear=null;
	private  Vector<Integer> dateVector = new Vector<>();

	public StockHistory() {
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

		frmStockHistory = new JFrame();
		frmStockHistory.setVisible(true);
		frmStockHistory.setAutoRequestFocus(false);
		frmStockHistory.setTitle("Stock History Page ");
		frmStockHistory.setBounds(100, 100, 1260, 720);
		frmStockHistory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmStockHistory.setExtendedState(frmStockHistory.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frmStockHistory.getContentPane().setLayout(new BorderLayout(0, 0));

		Operation_panel = new JPanel();
		Operation_panel.setBackground(new Color(245, 255, 250));
		Operation_panel.setForeground(Color.BLACK);
		frmStockHistory.getContentPane().add(Operation_panel, BorderLayout.NORTH);
		Operation_panel.setLayout(new BoxLayout(Operation_panel, BoxLayout.X_AXIS));

		lblDelivery = new JLabel("    Stock  History      ");
		lblDelivery.setForeground(Color.RED);
		lblDelivery.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblDelivery.setHorizontalAlignment(SwingConstants.CENTER);
		Operation_panel.add(lblDelivery);

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
				new StockHistory();
				frmStockHistory.dispose();
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
				frmStockHistory.dispose();
			}
		});
		btnHome.setOpaque(true);
		btnHome.setMultiClickThreshhold(250);
		btnHome.setContentAreaFilled(false);
		btnHome.setIcon(new ImageIcon(Distribution_window.class.getResource("/all_Data/home.png")));
		panel.add(btnHome, BorderLayout.EAST);

		lblShowDate = new JLabel("  Today(YY-MM-DD) ::  ");
		lblShowDate.setFont(new Font("Stencil", Font.PLAIN, 15));
		panel.add(lblShowDate, BorderLayout.WEST);

		lblDate = new JLabel("");
		lblDate.setText(date);
		lblDate.setFont(new Font("Stencil", Font.PLAIN, 17));
		panel.add(lblDate, BorderLayout.CENTER);

		
		Action_panel = new JPanel();
		frmStockHistory.getContentPane().add(Action_panel, BorderLayout.WEST);

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
		btnStockEntry.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		btnStockEntry.setContentAreaFilled(false);
		btnStockEntry.setBackground(new Color(211, 211, 211));
		btnStockEntry.setForeground(Color.BLACK);
		btnStockEntry.setOpaque(true);
		btnStockEntry.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		Action_panel.add(btnStockEntry);

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
		btnStockHistory.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		btnStockHistory.setContentAreaFilled(false);
		btnStockHistory.setBackground(Color.WHITE);
		btnStockHistory.setForeground(Color.BLACK);
		btnStockHistory.setOpaque(true);
		btnStockHistory.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		Action_panel.add(btnStockHistory);
		
		btnClearCells = new JButton("");
		btnClearCells.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeCellData(selectedGodown,selectedProduct,null);
				fn_fill_data();
				fn_renderPanel();
			}
		});
		btnClearCells.setIcon(new ImageIcon(Sales_Senpara.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
		btnClearCells.setMultiClickThreshhold(250);
		btnClearCells.setHorizontalAlignment(SwingConstants.CENTER);
		btnClearCells.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		btnClearCells.setContentAreaFilled(false);
		btnClearCells.setBackground(new Color(255, 51, 51));
		btnClearCells.setForeground(Color.BLACK);
		btnClearCells.setOpaque(true);
		btnClearCells.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		Action_panel.add(btnClearCells);

		pnlCustomPanel = new JPanel();
		frmStockHistory.getContentPane().add(pnlCustomPanel, BorderLayout.CENTER);
		pnlCustomPanel.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		pnlCustomPanel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(0, 4, 0, 0));

		lblSelectDateMonth = new JLabel("Select date, month and year --->");
		lblSelectDateMonth.setBackground(SystemColor.activeCaption);
		lblSelectDateMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectDateMonth.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(lblSelectDateMonth);

		comboBoxDay = new JComboBox<Object>(day);
		comboBoxDay.setFont(new Font("Sylfaen", Font.BOLD, 13));
		comboBoxDay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (comboBoxDay.getSelectedItem() != null)
					selectedDay = comboBoxDay.getSelectedItem().toString(); 
			}
			
		});
		panel_2.add(comboBoxDay);

		comboBoxMonth = new JComboBox<Object>(month);
		comboBoxMonth.setFont(new Font("Sylfaen", Font.BOLD, 13));
		comboBoxMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (comboBoxMonth.getSelectedItem() != null)
					selectedMonth = comboBoxMonth.getSelectedItem().toString();
			}
			
		});
		panel_2.add(comboBoxMonth);

		comboBoxYear = new JComboBox<Object>(year);
		comboBoxYear.setFont(new Font("Sylfaen", Font.BOLD, 13));
		comboBoxYear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (comboBoxYear.getSelectedItem() != null)
					selectedYear = comboBoxYear.getSelectedItem().toString();
			}
			
		});
		panel_2.add(comboBoxYear);

		lblSelectProductsTo = new JLabel("Select products to search --->");
		lblSelectProductsTo.setBackground(SystemColor.activeCaption);
		lblSelectProductsTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectProductsTo.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(lblSelectProductsTo);
		
		comboBoxCategory = new JComboBox<Object>();
		comboBoxCategory.setFont(new Font("Sylfaen", Font.BOLD, 13));
		comboBoxCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	if (comboBoxCategory.getSelectedItem() != null) 
        			selectedProduct = comboBoxCategory.getSelectedItem().toString();
            }
        });
		panel_2.add(comboBoxCategory);
		
		lblBlankLabel = new JLabel("");
		panel_2.add(lblBlankLabel);
		
		lblBlankLabel2 = new JLabel("");
		panel_2.add(lblBlankLabel2);
		
		lblchecklist = new JLabel("Select one of them --->\r\n");
		lblchecklist.setBackground(SystemColor.activeCaption);
		lblchecklist.setHorizontalAlignment(SwingConstants.CENTER);
		lblchecklist.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_2.add(lblchecklist);
		
		radiobtnSenpara = new JRadioButton("Senpara");
		radiobtnSenpara.setFont(new Font("Tahoma", Font.BOLD, 13));
		radiobtnSenpara.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(radiobtnSenpara.isSelected()) {
					comboBoxCategory.removeAllItems();
					for(int index=0;index<senparaCategory.length;index++) {
						comboBoxCategory.addItem(senparaCategory[index]);
					}
					selectedGodown="Senpara";
				}
				
			}
	    	
	    });
		panel_2.add(radiobtnSenpara);
		
		radiobtnBoroghor = new JRadioButton("Boroghor");
		radiobtnBoroghor.setFont(new Font("Tahoma", Font.BOLD, 13));
		radiobtnBoroghor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(radiobtnBoroghor.isSelected()) {
					comboBoxCategory.removeAllItems();
					for(int index=0;index<boroghorCategory.length;index++) {
						comboBoxCategory.addItem(boroghorCategory[index]);
					}
					selectedGodown="Boroghor";
				}
				
			}
			
		});
		panel_2.add(radiobtnBoroghor);
		
		//button group
		G1 = new ButtonGroup();
		G1.add(radiobtnSenpara); 
	    G1.add(radiobtnBoroghor); 
	    
	  	lblBlankLabel3 = new JLabel("");
		panel_2.add(lblBlankLabel3);
		
		btnSearchButton = new JButton("Search");
		btnSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fn_fill_data();
				fn_renderPanel();
			}
		});
		btnSearchButton.setContentAreaFilled(false);
		btnSearchButton.setBackground(new Color(135, 206, 235));
		btnSearchButton.setForeground(Color.BLACK);
		btnSearchButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));	
		btnSearchButton.setOpaque(true);
		btnSearchButton.setIcon(new ImageIcon(StockHistory.class.getResource("/all_Data/search.png")));
		btnSearchButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSearchButton.setMultiClickThreshhold(250);	
		
		
		panel_2.add(btnSearchButton);
		
	}

	protected static void removeCellData(String godown,String product,String lastBalance) {
		// TODO Auto-generated method stub
		FileInputStream fsIP;
		File tempFile = new File("D:\\business_all_stock\\"+godown+"\\"+product+".xls");
		try {
			fsIP = new FileInputStream(tempFile);
//			System.out.println(fsIP);
			HSSFWorkbook wb = new HSSFWorkbook(fsIP);
			HSSFSheet worksheet = wb.getSheetAt(0);
			HSSFCell cell = null,cell2 = null,cell3 = null;
			int range=500;
//			if(godown.equals("Boroghor"))	range = 500;
//			else							range = 200;
			for (int i = 1; i <= range; i++) {
				cell = worksheet.getRow(i).getCell(0);
				cell2 = worksheet.getRow(i).getCell(1);
				cell3 = worksheet.getRow(i).getCell(2);
				
				if (cell == null)
					cell = worksheet.getRow(i).createCell(0);
				if (cell2 == null)
					cell2 = worksheet.getRow(i).createCell(1);
				if (cell3 == null)
					cell3 = worksheet.getRow(i).createCell(2);
				
				cell.setCellValue("");
				cell2.setCellValue(0);
				cell3.setCellValue(0);
			}
			cell = worksheet.getRow(1).getCell(4);
			cell.setCellValue(0);
			HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
			
			if(lastBalance!="null") {
			HSSFCell cellData = worksheet.getRow(1).getCell(1);
			int intLastBalance = Integer.parseInt(lastBalance);
			cellData.setCellValue(intLastBalance);
			HSSFCell cellDate = worksheet.getRow(1).getCell(0);
			String date = java.time.LocalDate.now().toString();
			cellDate.setCellValue(date);
			HSSFCell cellLastEdit = worksheet.getRow(1).getCell(4);
			cellLastEdit.setCellValue(1);
			HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
			}
			
			fsIP.close();
			// Open FileOutputStream to write updates
			FileOutputStream output_file = new FileOutputStream(tempFile);
			// write changes
			wb.write(output_file);
			// close the stream
			output_file.close();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public void fn_fill_data() {
		
		String zero="0";
		String date=selectedYear+"-"+selectedMonth+"-"+selectedDay;
		file = new File("D:\\business_all_stock\\"+selectedGodown+"\\"+selectedProduct+".xls");
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
			dateVector.clear();
			for (int j = 1; j < sheet.getRows(); j++) {
				Vector<String> d = new Vector<String>();
				for (int i = 0; i < 4; i++) {
					Cell cell = sheet.getCell(i, j);
					if(cell.getContents().equals(zero)) {
						d.add("");
					}
					else if(cell.getContents().contains(date) && date!=null)
						{
							d.add(cell.getContents());
							dateVector.add(cell.getRow());
						}
					else
							d.add(cell.getContents());
				}
				d.add("\n");
				data.add(d);
			}
		} catch (BiffException e) {
			e.printStackTrace();
		}

		model = new DefaultTableModel(data, headers);
		tableWidth = model.getColumnCount() * 150;
		tableHeight = model.getRowCount() * 25;

	}
	
	public void fn_renderPanel() {
		
		//setting reset button text
		btnClearCells.setText("Reset "+selectedGodown+" "+selectedProduct);
		
		historyTable = new JTable();
		historyTable.setFillsViewportHeight(true);
		historyTable.setCellSelectionEnabled(true);

		historyTable.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.white));
		historyTable.setFont(new Font("SutonnyMJ", Font.BOLD, 22));
		historyTable.setForeground(Color.BLACK);

		historyTable.setModel(model);
		historyTable.setBackground(Color.WHITE);
		historyTable.setEnabled(true);
		historyTable.setRowHeight(30);
		historyTable.setRowMargin(5);

		historyTable.setGridColor(Color.LIGHT_GRAY); // color of the cell grid

		scroll = new JScrollPane(historyTable);
		pnlCustomPanel.add(scroll,BorderLayout.CENTER);
		scroll.setBackground(Color.white);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		historyTable.setPreferredSize(new Dimension(tableWidth, tableHeight));
		
		historyTable.setModel(model);
		StockHistory_TableColorCell renderer = new StockHistory_TableColorCell(dateVector);
		historyTable.setDefaultRenderer(Object.class, renderer);
		
		frmStockHistory.revalidate();
		
		
	}
}
