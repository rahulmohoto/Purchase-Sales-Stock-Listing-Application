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
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;

public class PrintLayout implements Printable {

	private JFrame frmOutputTableLayout;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable OutputTable;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblShowlabel;
	private JLabel lblShowOil;
	private JLabel lblShowSalt;
	private JLabel lblShowVersatile;
	private JLabel lblShowSpice;
	private JLabel lblTotalWeight;
	private JPanel panel_4;
	private JLabel lblBlankLabelTotal;
	private JLabel lblOil;
	private JLabel lblSalt;
	private JLabel lblVersatile;
	private JLabel lblSpice;
	private JLabel lblTotal;
	private float oil_weight;
	private float salt_weight;
	private float versatile_weight;
	private float spice_weight;
	private float total_weight;
	private float poly_weight;
	private JPanel panel_5;
	private JButton btnPrint;
	private String date;
	private JLabel lblShowPoly;
	private JLabel lblPoly;

	public PrintLayout() {
		initialize();
	}

	private void initialize() {

		date = java.time.LocalDate.now().toString();

		frmOutputTableLayout = new JFrame();
		frmOutputTableLayout.setVisible(true);
		frmOutputTableLayout.setResizable(false);
		frmOutputTableLayout.setAutoRequestFocus(false);
		frmOutputTableLayout.setTitle("Print Layout" + " " + "Date: " + date);
		frmOutputTableLayout.setBounds(150, 150, 640, 480);
		frmOutputTableLayout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmOutputTableLayout.getContentPane().setLayout(new BorderLayout(0, 0));
		TableColorCellRenderer renderer = new TableColorCellRenderer();

		panel = new JPanel();
		frmOutputTableLayout.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		// panel.setSize(500,500);

		scrollPane = new JScrollPane();
		// scrollPane.setSize(500,500);
		panel.add(scrollPane);

		OutputTable = new JTable();
		OutputTable.setRowSelectionAllowed(false);
		OutputTable.setFillsViewportHeight(true);
		OutputTable.setForeground(Color.BLACK);
		OutputTable.setSurrendersFocusOnKeystroke(true);
		OutputTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		OutputTable.setFont(new Font("SutonnyMJ", Font.PLAIN, 17));
		OutputTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		OutputTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Type", "PRODUCT", "P/C", "CTN",
				"PEC", "N.P", "P/P", "TOTAL", "FREE", "Shop Copy", "Stock Copy" }));
		OutputTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		OutputTable.getColumnModel().getColumn(1).setPreferredWidth(80);
		OutputTable.getColumnModel().getColumn(2).setPreferredWidth(40);
		OutputTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		OutputTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		OutputTable.getColumnModel().getColumn(5).setPreferredWidth(40);
		OutputTable.getColumnModel().getColumn(6).setPreferredWidth(65);
		OutputTable.getColumnModel().getColumn(8).setPreferredWidth(45);
		OutputTable.getColumnModel().getColumn(9).setPreferredWidth(90);
		OutputTable.getColumnModel().getColumn(10).setPreferredWidth(90);
		DefaultTableModel model = (DefaultTableModel) OutputTable.getModel();
		OutputTable.setDefaultRenderer(Object.class, renderer);
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

		lblShowlabel = new JLabel("Total Sales Of");
		lblShowlabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(lblShowlabel);

		lblShowOil = new JLabel("Oil(Litre.)");
		lblShowOil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblShowOil);

		lblShowPoly = new JLabel("Poly(Litre.)");
		panel_3.add(lblShowPoly);

		lblShowSalt = new JLabel("Salt(Kg.)");
		lblShowSalt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblShowSalt);

		lblShowVersatile = new JLabel("Rice+Mushur+Aata+M.oil(Kg.)");
		lblShowVersatile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblShowVersatile);

		lblShowSpice = new JLabel("Spice(kg.)");
		lblShowSpice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblShowSpice);

		lblTotalWeight = new JLabel("Total Weight");
		lblTotalWeight.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_3.add(lblTotalWeight);

		panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));

		lblBlankLabelTotal = new JLabel("");
		lblBlankLabelTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblBlankLabelTotal);

		lblOil = new JLabel("");
		lblOil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblOil);

		lblPoly = new JLabel("");
		lblPoly.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblPoly);

		lblSalt = new JLabel("");
		lblSalt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblSalt);

		lblVersatile = new JLabel("");
		lblVersatile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblVersatile);

		lblSpice = new JLabel("");
		lblSpice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblSpice);

		lblTotal = new JLabel("");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_4.add(lblTotal);

		// fill data on the output table
		for (int i = 0; i <= 51; i++) {
			if (isFloat((String) Distribution_window.dwTable.getValueAt(i, 7)) != 0
					|| isInteger((String) Distribution_window.dwTable.getValueAt(i, 8)) != 0) {
				String s0 = (String) Distribution_window.dwTable.getValueAt(i, 0);
				String s1 = (String) Distribution_window.dwTable.getValueAt(i, 1);
				String s2 = (String) Distribution_window.dwTable.getValueAt(i, 2);
				String s3 = (String) Distribution_window.dwTable.getValueAt(i, 3);
				String s4 = (String) Distribution_window.dwTable.getValueAt(i, 4);
				String s5 = (String) Distribution_window.dwTable.getValueAt(i, 5);
				String s6 = (String) Distribution_window.dwTable.getValueAt(i, 6);
				String s7 = (String) Distribution_window.dwTable.getValueAt(i, 7);
				String s8 = (String) Distribution_window.dwTable.getValueAt(i, 8);
				int net_piece = Integer.parseInt(s5);
				String StockCopy = null, ShopCopy = null;
				if (i >= 0 && i <= 9) {
					if (isInteger((String) Distribution_window.dwTable.getValueAt(i, 3)) != 0) {
						StockCopy = (String) Distribution_window.dwTable.getValueAt(i, 0) + " "
								+ (String) Distribution_window.dwTable.getValueAt(i, 1) + " "
								+ Distribution_window.dwTable.getValueAt(i, 3);
						ShopCopy = (String) Distribution_window.dwTable.getValueAt(i, 0) + " "
								+ (String) Distribution_window.dwTable.getValueAt(i, 1) + " "
								+ Distribution_window.dwTable.getValueAt(i, 3);
					}
					fn_oil_weight(i, net_piece);
				}
				if (i >= 14 && i <= 20) {
					if (isInteger((String) Distribution_window.dwTable.getValueAt(i, 3)) != 0 && i != 16) {
						StockCopy = (String) Distribution_window.dwTable.getValueAt(i, 0) + " "
								+ (String) Distribution_window.dwTable.getValueAt(i, 1) + " "
								+ Distribution_window.dwTable.getValueAt(i, 3);
						ShopCopy = (String) Distribution_window.dwTable.getValueAt(i, 0) + " "
								+ (String) Distribution_window.dwTable.getValueAt(i, 1) + " "
								+ Distribution_window.dwTable.getValueAt(i, 3);
					}
				}
				model.addRow(new Object[] { s0, s1, s2, s3, s4, s5, s6, s7, s8, StockCopy, ShopCopy });

				fn_versatile_weight(i, net_piece);
				fn_salt_weight(i, net_piece);
				fn_spice_weight(i, net_piece);
				fn_poly_weight(i, net_piece);

			}

		}
		model.addRow(new Object[] { (String) Distribution_window.dwTable.getValueAt(54, 0),
				(String) Distribution_window.dwTable.getValueAt(54, 1),
				(String) Distribution_window.dwTable.getValueAt(54, 2),
				(String) Distribution_window.dwTable.getValueAt(54, 3),
				(String) Distribution_window.dwTable.getValueAt(54, 4),
				(String) Distribution_window.dwTable.getValueAt(54, 5),
				(String) Distribution_window.dwTable.getValueAt(54, 6),
				(String) Distribution_window.dwTable.getValueAt(54, 7),
				(String) Distribution_window.dwTable.getValueAt(54, 8) });

		lblOil.setText(Float.toString(oil_weight));
		lblPoly.setText(Float.toString(poly_weight));
		lblSalt.setText(Float.toString(salt_weight));
		lblVersatile.setText(Float.toString(versatile_weight));
		lblSpice.setText(Float.toString(spice_weight));
		total_weight = oil_weight + salt_weight + versatile_weight + spice_weight + poly_weight;
		lblTotal.setText(Float.toString(total_weight));

		panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnPrint = new JButton("");
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
				PrinterJob pj = PrinterJob.getPrinterJob();
				pj.setCopies(1);
				pj.setPrintable(PrintLayout.this);
				// pj.setPrintable(PrintTable.this, arg1);
				boolean ok = pj.printDialog();
				if (ok) {
					try {
						frmOutputTableLayout.dispose();
						pj.print();
					} catch (Exception PrintException) {
						System.out.println("error while printing");
					}
				}

			}
		});
		panel_5.add(btnPrint);
	}

	public int isInteger(String s) {
		int x;
		try {
			x = Integer.parseInt(s);
		} catch (Exception e) {
			x = 0;
		}
		return x;
	}

	public float isFloat(String s) {
		float x;
		try {
			x = Float.parseFloat(s);
		} catch (Exception e) {
			x = 0;
		}
		return x;
	}

	public void fn_oil_weight(int row, int net_piece) {
		if (row == 0)
			oil_weight += net_piece * 5;
		else if (row == 1)
			oil_weight += net_piece * 3;
		else if (row == 2)
			oil_weight += net_piece * 2;
		else if (row == 3)
			oil_weight += net_piece * 1;
		else if (row == 4)
			oil_weight += (float) (net_piece * 0.5);
		else if (row == 5)
			oil_weight = (float) (net_piece * 0.25);
		else if (row == 6)
			oil_weight += net_piece * 8;
		else if (row == 8)
			oil_weight += net_piece * 1;
		else
			oil_weight = (float) (net_piece * 0.5);
	}

	public void fn_versatile_weight(int row, int net_piece) {
		if (row == 17 || row == 20 || row == 22)
			versatile_weight += net_piece * 1;
		else if (row == 21 || row == 23)
			versatile_weight = (float) (net_piece * 0.5);
		else if (row == 24)
			versatile_weight = (float) (net_piece * 0.25);
	}

	public void fn_salt_weight(int row, int net_piece) {
		if (row == 14)
			salt_weight += net_piece * 25;
		else if (row == 15)
			salt_weight += net_piece * 25;
	}

	public void fn_spice_weight(int row, int net_piece) {
		if (row == 31 || row == 38)
			spice_weight += net_piece * 5;
		else if (row == 32 || row == 39)
			spice_weight += (float) (net_piece * 0.5);
		else if (row == 33 || row == 34 || row == 40 || row == 41)
			spice_weight += (float) (net_piece * 0.2);
		else if (row == 35 || row == 42)
			spice_weight += (float) (net_piece * 0.1);
		else if (row == 36 || row == 43)
			spice_weight += (float) (net_piece * 0.05);
	}

	public void fn_poly_weight(int row, int net_piece) {
		if (row == 11)
			poly_weight += net_piece * 1;
		if (row == 12)
			poly_weight += net_piece * 0.5;
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
		double tableWidthOnPage = tableWidth * scale;
		double oneRowHeight = (OutputTable.getRowHeight() + OutputTable.getRowMargin()) * scale;
		int numRowsOnAPage = (int) ((pageHeight - headerHeightOnPage) / oneRowHeight);
		double pageHeightForTable = oneRowHeight * numRowsOnAPage;
		int lastRowPrinted = numRowsOnAPage * pageIndex;
		int numRowsLeft = OutputTable.getRowCount() - lastRowPrinted;
		int totalNumPages = (int) Math.ceil(((double) OutputTable.getRowCount()) / numRowsOnAPage);
		if (pageIndex >= totalNumPages)
			return NO_SUCH_PAGE;

		g2.setColor(Color.GRAY);
		g2.drawRect(14, 11, 185, 69);
		g2.setFont(new Font("Segoe UI Light", Font.PLAIN, 10));
		g2.setColor(Color.DARK_GRAY);
		g2.drawString("Oil(Litre)      :  " + lblOil.getText().toString(), 15, 20);
		g2.drawString("Poly(Litre)    :  " + lblPoly.getText().toString(), 15, 31);
		g2.drawString("Salt (Kg)      :  " + lblSalt.getText().toString(), 15, 42);// 31//42
		g2.drawString("Rice+Mushur+Aata+M.oil (Kg)  :  " + lblVersatile.getText().toString(), 15, 53);// 42//53
		g2.drawString("Spice (Kg)     :  " + lblSpice.getText().toString(), 15, 64);// 53//64
		g2.drawString(date, 455, 77);//

		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Segoe UI Light", Font.BOLD, 10));
		g2.drawString("Total Weight of sales(Kg): " + lblTotal.getText().toString(), 15, 77);// 64

		g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY() + 81);

		g2.translate(0f, headerHeightOnPage);
		g2.translate(0f, -pageIndex * pageHeightForTable);
		if (pageIndex + 1 == totalNumPages) {
			g2.setClip(0, (int) (pageHeightForTable * pageIndex), (int) Math.ceil(tableWidthOnPage),
					(int) Math.ceil(oneRowHeight * numRowsLeft));
		} else {
			g2.setClip(0, (int) (pageHeightForTable * pageIndex), (int) Math.ceil(tableWidthOnPage),
					(int) Math.ceil(pageHeightForTable));
		}
		g2.scale(scale, scale);
		OutputTable.paint(g2);
		g2.scale(1 / scale, 1 / scale);
		g2.translate(0f, pageIndex * pageHeightForTable);
		g2.translate(0f, -headerHeightOnPage);
		g2.setClip(0, 0, (int) Math.ceil(tableWidthOnPage), (int) Math.ceil(headerHeightOnPage));
		g2.scale(scale, scale);
		OutputTable.getTableHeader().paint(g2);
		return Printable.PAGE_EXISTS;
	}

}
