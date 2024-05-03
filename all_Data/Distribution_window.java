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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
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

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Component;

public class Distribution_window {

	/**
	 * 
	 */
	static JTable dwTable;
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

	private JFrame frmDistribution;
	private JPanel Table_panel;
	private JButton btnResetTable;
	private JPanel Action_panel;
	private JButton btnCustomer;
	private JLabel lblDelivery;
	private JButton btnStockEntry;
	private JButton btnStockEntryBoroghor;
	private JButton btnStockRemove;
	private JButton btnStockRemoveBoroghor;
	private JButton btnStockHistory;
	private JButton btnDelivery;
	private JButton btnSaveTable;
	private JPanel panel;
	private JButton btnHome;
	private JLabel lblShowDate;
	private JLabel lblDate;
	private JButton btnGenerateTable;
	private JPanel Operation_panel;
	private String customer = "Customer\nPurchase";
	private String stock_senpara="Stock\nSENPARA";
	private String sales_senpara="Sales\nSENPARA";
	private String sales_boroghor="Sales\nBOROGHOR";
	private String stock_boroghor="Stock\nBOROGHOR";
	private String stock_history="Stock\nHistory";


	public Distribution_window() {
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

		frmDistribution = new JFrame();
		frmDistribution.setVisible(true);
		frmDistribution.setAutoRequestFocus(false);
		frmDistribution.setTitle("Distribution Page ");
		frmDistribution.setBounds(100, 100, 1263, 805);
		frmDistribution.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDistribution.setExtendedState(frmDistribution.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frmDistribution.getContentPane().setLayout(new BorderLayout(0, 0));

		Operation_panel = new JPanel();
		Operation_panel.setBackground(new Color(245, 255, 250));
		Operation_panel.setForeground(Color.BLACK);
		frmDistribution.getContentPane().add(Operation_panel, BorderLayout.NORTH);
		Operation_panel.setLayout(new BoxLayout(Operation_panel, BoxLayout.X_AXIS));

		lblDelivery = new JLabel("      Distribution Page     ");
		lblDelivery.setForeground(Color.RED);
		lblDelivery.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblDelivery.setHorizontalAlignment(SwingConstants.CENTER);
		Operation_panel.add(lblDelivery);

		btnGenerateTable = new JButton("Output Layout ");
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
				new PrintLayout();
			}
		});
		Operation_panel.add(btnGenerateTable);

		btnResetTable = new JButton("Refresh ");
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
				fn_fill_data();
				fn_render_panel();
			}
		});
		Operation_panel.add(btnResetTable);

		btnSaveTable = new JButton("Save Table ");
		btnSaveTable.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		btnSaveTable.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSaveTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileInputStream fsIP;
				try {
					fsIP = new FileInputStream(file);
					HSSFWorkbook wb = new HSSFWorkbook(fsIP);
					HSSFSheet worksheet = wb.getSheetAt(0);
					HSSFCell cell = null;
					for (int i = 0; i <= 53; i++) {
						cell = worksheet.getRow(i + 1).getCell(6);
						cell.setCellValue((String) dwTable.getValueAt(i, 6));
					}
					fsIP.close();
					// Open FileOutputStream to write updates
					FileOutputStream output_file = new FileOutputStream(file);
					// write changes
					wb.write(output_file);
					// close the stream
					output_file.close();
					wb.close();
					JOptionPane.showMessageDialog(frmDistribution, "Saving Successful!!");
				} catch (IOException evt) {
					// TODO Auto-generated catch block
					evt.printStackTrace();
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

		panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		Operation_panel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		btnHome = new JButton("");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainWindow();
				frmDistribution.dispose();
			}
		});
		btnHome.setMultiClickThreshhold(250);
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

		fn_fill_data();
		fn_render_panel();

		Action_panel = new JPanel();
		frmDistribution.getContentPane().add(Action_panel, BorderLayout.WEST);

		btnCustomer = new JButton("<html>" + customer.replaceAll("\\n", "<br>") + "</html>");
		btnCustomer.setMultiClickThreshhold(250);
		btnCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		btnCustomer.setIcon(new ImageIcon(Distribution_window.class.getResource("/all_Data/customer.png")));
		btnCustomer.setBorder(BorderFactory.createLineBorder(Color.white, 1));
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
		btnStockEntryBoroghor.setBorder(BorderFactory.createLineBorder(Color.white, 1));
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
		btnStockRemoveBoroghor.setBorder(BorderFactory.createLineBorder(Color.white, 1));
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

		btnDelivery = new JButton("Delivery");
		btnDelivery.setIcon(new ImageIcon(Distribution_window.class.getResource("/all_Data/delivery.png")));
		btnDelivery.setMultiClickThreshhold(250);
		btnDelivery.setHorizontalAlignment(SwingConstants.LEFT);
		btnDelivery.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		btnDelivery.setContentAreaFilled(false);
		btnDelivery.setBackground(Color.WHITE);
		btnDelivery.setForeground(Color.BLACK);
		btnDelivery.setOpaque(true);
		btnDelivery.setFont(new Font("Segoe UI Symbol", Font.BOLD, 13));
		Action_panel.add(btnDelivery);

	}

	Action action = new AbstractAction() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			int NoOfCtn = 0, NoOfPec = 0, total_pec, piece_per_cartoon;
			float totalAmt, netTotal = 0;
			TableCellListener tcl = (TableCellListener) e.getSource();
			try {
				NoOfCtn = Integer.parseInt((String) dwTable.getValueAt(tcl.getRow(), 3));
			} catch (Exception evt) {
				NoOfCtn = 0;
			}

			try {
				NoOfPec = Integer.parseInt((String) dwTable.getValueAt(tcl.getRow(), 4));
			} catch (Exception evt) {
				NoOfPec = 0;
			}

			try {
				piece_per_cartoon = Integer.parseInt((String) dwTable.getValueAt(tcl.getRow(), 2));
			} catch (Exception evt) {
				piece_per_cartoon = 1;
			}
			total_pec = NoOfCtn * piece_per_cartoon + NoOfPec;
			dwTable.setValueAt(Integer.toString(total_pec), tcl.getRow(), 5);
			float amt_per_piece = Float.parseFloat((String) dwTable.getValueAt(tcl.getRow(), 6));
			totalAmt = total_pec * amt_per_piece;
			dwTable.setValueAt(Float.toString(totalAmt), tcl.getRow(), 7);
			for (int i = 0; i < 53; i++) {
				float iTotal = 0;
				try {
					iTotal = Float.parseFloat((String) dwTable.getValueAt(i, 7));
				} catch (Exception evt) {
					iTotal = 0;
				}
				netTotal = netTotal + iTotal;
			}
			dwTable.setValueAt(Float.toString(netTotal), 54, 7);
		}

	};
	
	public void fn_render_panel() {

		Table_panel = new JPanel();
		Table_panel.setVisible(false);
		frmDistribution.getContentPane().add(Table_panel, BorderLayout.CENTER);
		Table_panel.setLayout(new GridLayout(0, 1, 0, 0));

		dwTable = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int vColIndex) { // for disabling a cell edit for user
				if (vColIndex == 0 || vColIndex == 1 || vColIndex == 2 || vColIndex == 5 || vColIndex == 7) {
					if (rowIndex > 44 && rowIndex <= 52 && vColIndex != 5
							|| vColIndex != 7 && rowIndex > 44 && rowIndex <= 52 && vColIndex != 5) {
						return true;
					}
					return false;
				} else {
					return true;
				}
			}
		};
		dwTable.setFillsViewportHeight(true);
		@SuppressWarnings("unused")
		TableCellListener tcl = new TableCellListener(dwTable, action);
		// table.setSurrendersFocusOnKeystroke(true);
		dwTable.setCellSelectionEnabled(true);

		dwTable.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.white));
		dwTable.setFont(new Font("SutonnyMJ", Font.BOLD, 19));
		dwTable.setForeground(Color.BLACK);

		dwTable.setModel(model);
		dwTable.getColumnModel().getColumn(3).setPreferredWidth(40);
		dwTable.getColumnModel().getColumn(4).setPreferredWidth(40);
		dwTable.setBackground(Color.WHITE);
		dwTable.setEnabled(true);
		dwTable.setRowHeight(25);
		dwTable.setRowMargin(2);

		dwTable.setGridColor(Color.LIGHT_GRAY); // color of the cell grid

		scroll = new JScrollPane(dwTable);
		Table_panel.add(scroll);
		scroll.setBounds(10, 49, 710, 465);
		scroll.setBackground(Color.white);
		scroll.setPreferredSize(new Dimension(300, 300));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		dwTable.setPreferredSize(new Dimension(tableWidth, tableHeight));

		dwTable.setModel(model);

		TableColorCell render = new TableColorCell();
		dwTable.setDefaultRenderer(Object.class, render);
		
		Table_panel.setVisible(true);

	}

	public void fn_fill_data() {

		file = new File("D:\\business_all_stock\\godownSheets.xls");
		workbook = null;
		try {
			try {
				workbook = Workbook.getWorkbook(file);
			} catch (IOException ex) {
				Logger.getLogger(Distribution_window.class.getName()).log(Level.SEVERE, null, ex);
			}
			sheet = workbook.getSheet(0);

			headers.clear();
			for (int i = 0; i < 9; i++) {
				Cell cell1 = sheet.getCell(i, 0);
				headers.add(cell1.getContents());
			}

			data.clear();
			for (int j = 1; j < sheet.getRows(); j++) {
				Vector<String> d = new Vector<String>();
				for (int i = 0; i < 9; i++) {
					Cell cell = sheet.getCell(i, j);
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
}
