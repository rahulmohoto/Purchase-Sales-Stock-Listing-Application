package all_Data;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class Print_Stock implements Printable{

	private JFrame frmOutputTableLayout;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable OutputTable;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblBlankLabelTotal;
	private JPanel panel_5;
	private JButton btnPrint;
	private String dateTime;
	private String decide;
	private DefaultTableModel model;

	public Print_Stock(String decide) {
		this.decide=decide;
		initialize();
	}

	private void initialize() {
		
		LocalDateTime myObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		dateTime = myObj.format(myFormatObj);

		frmOutputTableLayout = new JFrame();
		frmOutputTableLayout.setVisible(true);
		frmOutputTableLayout.setResizable(false);
		frmOutputTableLayout.setAutoRequestFocus(false);
		frmOutputTableLayout.setTitle("Print Layout" + " " + dateTime);
		frmOutputTableLayout.setSize(640, 480);
		frmOutputTableLayout.setLocationRelativeTo(null);
		frmOutputTableLayout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmOutputTableLayout.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		frmOutputTableLayout.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);

		OutputTable = new JTable();
		OutputTable.setRowSelectionAllowed(false);
		OutputTable.setFillsViewportHeight(true);
		OutputTable.setForeground(Color.BLACK);
		OutputTable.setSurrendersFocusOnKeystroke(true);
		OutputTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		OutputTable.setFont(new Font("SutonnyMJ", Font.PLAIN, 17));
		OutputTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		OutputTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Shop Copy","Stock Copy"}));
		model = (DefaultTableModel) OutputTable.getModel();
		scrollPane.setViewportView(OutputTable);

		// table buildup finished

		panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));

		lblBlankLabelTotal = new JLabel("");
		lblBlankLabelTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblBlankLabelTotal);

		// fill data on the output table
		if(decide.equals("stock_senpara"))
			fill_data_stock();
		if(decide.equals("sales_senpara"))
			fill_data_sales();
		if(decide.equals("stock_boroghor"))
			fill_data_stock_boroghor();
		if(decide.equals("sales_boroghor"))
			fill_data_sales_boroghor();
		
		
		panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnPrint = new JButton();
		btnPrint.setIcon(new ImageIcon(PrintLayout.class.getResource("/all_Data/print.png")));
		btnPrint.setHorizontalAlignment(SwingConstants.LEFT);
		btnPrint.setBorder(BorderFactory.createLineBorder(Color.cyan, 1));
		btnPrint.setContentAreaFilled(false);
		btnPrint.setBackground(new Color(245, 255, 250));
		btnPrint.setForeground(Color.BLACK);
		btnPrint.setMultiClickThreshhold(250);
		btnPrint.setOpaque(true);
		btnPrint.setFont(new Font("Segoe UI Symbol", Font.BOLD, 13));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				PrinterJob job = PrinterJob.getPrinterJob();
			    PageFormat pf = new PageFormat();
			    Paper paper = new Paper();
			    paper.setSize(2.28346 * 72, 3 * 72);
			    paper.setImageableArea(0.0 * 72, 0.0 * 72, paper.getWidth(), paper.getHeight());
			    pf.setPaper(paper); 
			   
			    job.setPrintable(Print_Stock.this); 
			    job.validatePage(pf);
			    
			    boolean ok = job.printDialog();
			    
			    if (ok) {
			    	
			    	try {
						frmOutputTableLayout.dispose();
						job.setCopies(1);
						job.print();
					} catch (Exception PrintException) {
						job.setCopies(2);
						try {
							job.print();
							job.print();
						} catch (PrinterException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
			       
			    }				
				//try code			
			}
		});
		
			panel_5.add(btnPrint);
	}

	private void fill_data_stock() {
		// TODO Auto-generated method stub
		for(int i=0;i<MainWindow.updatedRow.length;i++) {
			if(MainWindow.updatedRow[i]!=0) {
				String s1 = (String) Stock_Senpara.table.getValueAt(i, 1);
				String s2 = (String) Stock_Senpara.table.getValueAt(i, 2);
				String StockCopy=null,ShopCopy=null;
				StockCopy = s1+" "+s2;
				ShopCopy = s1+" "+s2;
				model.addRow(new Object[] {StockCopy,ShopCopy});
			}
		}		
	}
	
	private void fill_data_sales() {
		// TODO Auto-generated method stub
		for(int i=0;i<MainWindow.updatedRow.length;i++) {
			if(MainWindow.updatedRow[i]!=0) {
				String s1 = (String) Sales_Senpara.table.getValueAt(i, 1);
				String s2 = (String) Sales_Senpara.table.getValueAt(i, 2);
				String StockCopy=null,ShopCopy=null;
				StockCopy = s1+" "+s2;
				ShopCopy = s1+" "+s2;
				model.addRow(new Object[] {StockCopy,ShopCopy});
			}
		}		
	}
	
	private void fill_data_stock_boroghor() {
		// TODO Auto-generated method stub
		for(int i=0;i<MainWindow.updatedRow.length;i++) {
			if(MainWindow.updatedRow[i]!=0) {
				String s1 = (String) Stock_Boroghor.table.getValueAt(i, 1);
				String s2 = (String) Stock_Boroghor.table.getValueAt(i, 2);
				String StockCopy=null,ShopCopy=null;
				StockCopy = s1+" "+s2;
				ShopCopy = s1+" "+s2;
				model.addRow(new Object[] {StockCopy,ShopCopy});
			}
		}		
	}
	
	private void fill_data_sales_boroghor() {
		// TODO Auto-generated method stub
		for(int i=0;i<MainWindow.updatedRow.length;i++) {
			if(MainWindow.updatedRow[i]!=0) {
				String s1 = (String) Sales_Boroghor.table.getValueAt(i, 1);
				String s2 = (String) Sales_Boroghor.table.getValueAt(i, 2);
				String StockCopy=null,ShopCopy=null;
				StockCopy = s1+" "+s2;
				ShopCopy = s1+" "+s2;
				model.addRow(new Object[] {StockCopy,ShopCopy});
			}
		}		
	}

	@Override
	public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		int fontHeight = g2.getFontMetrics().getHeight();

		// reserve spaces for page number
		double pageHeight = pageFormat.getImageableHeight() - fontHeight;
		double pageWidth = pageFormat.getImageableWidth();
		double tableWidth = (double) OutputTable.getColumnModel().getTotalColumnWidth();
		double scale = 1;
		if (tableWidth >= pageWidth) {
			scale = pageWidth / tableWidth;
		}
		double headerHeightOnPage = OutputTable.getTableHeader().getHeight() * scale;
		double oneRowHeight = (OutputTable.getRowHeight() + OutputTable.getRowMargin()) * scale;
		int numRowsOnAPage = (int) ((pageHeight - headerHeightOnPage) / oneRowHeight);
		int totalNumPages = (int) Math.ceil(((double) OutputTable.getRowCount()) / numRowsOnAPage);
		if (pageIndex >= totalNumPages)
			return NO_SUCH_PAGE;
		
		
		
		int spaceIterator=1;
		switch(decide) {
		
		case "sales_boroghor" :
			g2.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
			g2.drawString("---Boroghor Sales---", 10, 12);
			g2.setFont(new Font("Segoe UI Light", Font.BOLD, 9));
			g2.drawString("Printed:"+dateTime,9,26);
			g2.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
			g2.drawString("  -------------------", 10, 35);
			g2.setFont(new Font("SutonnyMJ", Font.PLAIN, 12));
			
		for(int i=0;i<OutputTable.getRowCount();i++) {
				String s0 = (String) OutputTable.getValueAt(i, 0);
				String Copy=null;
				Copy = s0;
				g2.drawString(Copy, 5, (35+spaceIterator++*12));
		}
		break;
		
		case "stock_boroghor" :
			g2.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
			g2.drawString("---Boroghor Stock---", 10, 12);
			g2.setFont(new Font("Segoe UI Light", Font.BOLD, 9));
			g2.drawString("Printed:"+dateTime,9,26);
			g2.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
			g2.drawString(" -------------------", 10, 35);
			g2.setFont(new Font("SutonnyMJ", Font.PLAIN, 12));
			
		for(int i=0;i<OutputTable.getRowCount();i++) {
					String s0 = (String) OutputTable.getValueAt(i, 0);
					String Copy=null;
					Copy = s0;
					g2.drawString(Copy, 5, (35+spaceIterator++*12));
			}
		break;
		
		case "stock_senpara" :
			g2.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
			g2.drawString("---Senpara Stock---", 10, 12);
			g2.setFont(new Font("Segoe UI Light", Font.BOLD, 9));
			g2.drawString("Printed:"+dateTime,9,26);
			g2.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
			g2.drawString(" -------------------", 10, 35);
			g2.setFont(new Font("SutonnyMJ", Font.PLAIN, 12));
		
		for(int i=0;i<OutputTable.getRowCount();i++) {
				String s0 = (String) OutputTable.getValueAt(i, 0);
				String Copy=null;
				Copy = s0;
				g2.drawString(Copy, 5, (35+spaceIterator++*12));
		}
		break;
		
		case "sales_senpara" :
			g2.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
			g2.drawString("---Senpara Sales---", 10, 12);
			g2.setFont(new Font("Segoe UI Light", Font.BOLD, 9));
			g2.drawString("Printed:"+dateTime,9,26);
			g2.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
			g2.drawString("  -------------------", 10, 35);
			g2.setFont(new Font("SutonnyMJ", Font.PLAIN, 12));
		
		for(int i=0;i<OutputTable.getRowCount();i++) {
				String s0 = (String) OutputTable.getValueAt(i, 0);
				String Copy=null;
				Copy = s0;
				g2.drawString(Copy, 5, (35+spaceIterator++*12));
		}
		break;
		}
		
		
		g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		
		return Printable.PAGE_EXISTS;
	}
	
		
}
