package all_Data;

import java.awt.Cursor;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.SystemColor;

public class ShowProgress implements PropertyChangeListener {

	private JFrame frame;
	//private JDialog d;
	private Task task;
	private JProgressBar progressBar;
	private JPanel panel;
	private String decide;

	class Task extends SwingWorker<Void, Void> {
		/*
		 * Main task. Executed in background thread.
		 */
		@Override
		public Void doInBackground() {
			Random random = new Random();
			int progress = 0;
			// Initialize progress property.
			setProgress(0);
			while (progress < 100) {
				// Sleep for up to one second.
				try {
					Thread.sleep(random.nextInt(400));
				} catch (InterruptedException ignore) {
				}
				// Make random progress.
				progress += random.nextInt(70);
				setProgress(Math.min(progress, 100));

			}
			return null;
		}

		/*
		 * Executed in event dispatching thread
		 */
		@Override
		public void done() {
			frame.setCursor(null); // turn off the wait cursor
			frame.dispose();
//			MainWindow.mainFrame.dispose();
			if (decide.equals("sales_senpara"))
				Sales_Senpara.frmSalesSheetSenpara.setVisible(true);
			if (decide.equals("stock_senpara"))
				Stock_Senpara.frmStockSheetSenpara.setVisible(true);
			if (decide.equals("sales_boroghor"))
				Sales_Boroghor.frmSalesSheetBoroghor.setVisible(true);
			if (decide.equals("stock_boroghor"))
				Stock_Boroghor.frmStockSheetBoroghor.setVisible(true);
		}
	}

	/**
	 * Create the application.
	 */
	public ShowProgress(String x) {
		decide = x;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();

		frame = new JFrame();
		frame.setSize(450, 127);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setAutoRequestFocus(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setAutoRequestFocus(false);
		frame.getContentPane().setBackground(SystemColor.info);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lblLoading = new JLabel("Loading.....");
		lblLoading.setBackground(SystemColor.info);
		lblLoading.setFont(new Font("Snap ITC", Font.PLAIN, 19));
		lblLoading.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblLoading, BorderLayout.NORTH);

		JLabel label = new JLabel("                 ");
		label.setEnabled(false);
		label.setBackground(SystemColor.info);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(label, BorderLayout.SOUTH);

		panel = new JPanel();
		panel.setForeground(SystemColor.info);
		panel.setBackground(SystemColor.info);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		progressBar = new JProgressBar(0, 100);
		progressBar.setBackground(SystemColor.info);
		progressBar.setFont(new Font("Sitka Small", Font.BOLD, 16));
		progressBar.setForeground(SystemColor.controlDkShadow);
		panel.add(progressBar, BorderLayout.CENTER);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
//			MainWindow.mainFrame.setEnabled(false);
			if (decide.equals("sales_senpara"))
				new Sales_Senpara();
			if (decide.equals("stock_senpara"))
				new Stock_Senpara();
			if (decide.equals("sales_boroghor"))
				new Sales_Boroghor();
			if (decide.equals("stock_boroghor"))
				new Stock_Boroghor();
		}

	}

}
