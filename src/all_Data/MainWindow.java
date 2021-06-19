package all_Data;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow {

	public static JFrame mainFrame;
	private JPanel Action_panel;
	private JButton btnCustomer;
	private JButton btnStockEntry;
	private JButton btnStockEntryBoroghor;
	private JButton btnStockRemove;
	private JButton btnStockRemoveBoroghor;
	private JButton btnStockHistory;
	private JPanel panel;
	private JLabel lblStartLabel;
	public static int updatedRow[]=new int[140];
	
	public static Vector<Integer> modifiedCellVectorStockSenpara = new Vector<Integer>();
	public static Vector<Integer> modifiedCellVectorSalesSenpara = new Vector<Integer>();
	public static Vector<Integer> modifiedCellVectorStockBoroghor = new Vector<Integer>();
	public static Vector<Integer> modifiedCellVectorSalesBoroghor = new Vector<Integer>();
	
	
	public static Vector<Integer> modifiedRowVectorStockSenpara = new Vector<Integer>();
	public static Vector<Integer> modifiedRowVectorSalesSenpara = new Vector<Integer>();
	public static Vector<Integer> modifiedRowVectorStockBoroghor = new Vector<Integer>();
	public static Vector<Integer> modifiedRowVectorSalesBoroghor = new Vector<Integer>();
	
	public static boolean isSaved = false;
	private String customer = "Customer\nPurchase";
	private String stock_senpara="Stock\nSENPARA";
	private String sales_senpara="Sales\nSENPARA";
	private String sales_boroghor="Sales\nBOROGHOR";
	private String stock_boroghor="Stock\nBOROGHOR";
	private String stock_history="Stock\nHistory";
	
	public static int threshold = 450;
	
	static String[] filesSenpara={			
			"8Lfresh","5Lfresh","3Lfresh","2Lfresh","1Lfresh","500mLfresh","250mLfresh","blank",
			"bodhuChinigura","25kgChinigura","puratonChabiChinigura","notunChabiChinigura","puratonKobutorChinigura","notunKobutorChinigura","Amin1KgChinigura","blank",
			"shaplaRajbhog","rajaniRajbhog","golapRajbhog","kobutorRajbhog","25kgRajbhog","blank",
			"appleKatari","50kgPaijamKatari","25kgShiddhoKatari","blank",
			"Kobutor50KgMiniket","Kobutor25KgMiniket","Amin50KgMiniket","Amin25KgMiniket","blank",
			"Kobutor50Kg28","Kobutor25Kg28","Amin50Kg28","Amin25Kg28","blank",
			"pitharChal","blank",
			"1lChaka","500mlChaka","250mlChaka","200mlChaka","100mlChaka","50mlChaka","8kgChaka","16kgChaka","blank",
			"8kgChadghora","16kgChadghora","blank",
			"8kgParachut","blank",
			"1kgChini","500gmChini","blank",
			"1kgSalt","500gmSalt","blank",
			"Aata","500gmSuji","200gmSuji","blank",
			"chikonMug","25kgChikonMushur","50kgMotaMushur","25kgMotaMushur","blank",
			"7pusti","18pusti","50pusti","100pusti","200pusti","325pusti","500pusti","1kgPusti","2kgPusti","blank",
			"Aararut","kaporSoda","khabarSoda","Aamonia","shabu","kismis","iraniJira","AaluBokhra","joyfol","jotrik","darchini","shadaMorich","kaloGolmorich","kalaJira","loong","dalda","chotoElach","boroElach","vedan",
			"100gmMasu","450gmMasu",
			"methi","muhuri","dhonia","khudi","missri","rong","neel","dhup","shutli","butter","baking","blank",
			"500dano","400dano","200dano","100dano","50dano","20dano","blank",
			"1Lpure","500mLpure","1Lpoly","500mLpoly","1KGmushur","500GMmushur",
			"500mLshorisha","250mLshorisha","100mLshorisha","80mLshorisha"
	};
	
	static String[] filesBoroghor={			
			"8Lfresh","5Lfresh","3Lfresh","2Lfresh","1Lfresh","500mLfresh","250mLfresh","blank",
			"bodhuChinigura","25kgChinigura","notunChabiChinigura","puratonChabiChinigura","puratonKobutorChinigura","notunKobutorChinigura","Amin1KgChinigura","blank",
			"shaplaRajbhog","rajaniRajbhog","golapRajbhog","kobutorRajbhog","25kgRajbhog","blank",
			"appleKatari","khudi","25kgShiddhoKatari","blank",
			"Kobutor50KgMiniket","Kobutor25KgMiniket","Amin50KgMiniket","Amin25KgMiniket","blank",
			"Kobutor50Kg28","Kobutor25Kg28","Amin50Kg28","Amin25Kg28","blank",
			"1lChaka","500mlChaka","250mlChaka","200mlChaka","100mlChaka","50mlChaka","blank",
			"1kgChini","500gmChini","blank",
			"1kgSalt","500gmSalt","blank",
			"Aata","500gmSuji","200gmSuji","blank",
			"chikonMug","25kgChikonMushur","50kgMotaMushur","25kgMotaMushur","blank",
			"7pusti","18pusti","50pusti","100pusti","200pusti","325pusti","500pusti","1kgPusti","blank",
			"Aararut","kaporSoda","khabarSoda","Aamonia","shabu","kismis","iraniJira","AaluBokhra","jotrik","darchini","shadaMorich","loong","dalda","blank",
			"500dano","400dano","200dano","100dano","50dano","20dano"	
};
	
	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		initialize();
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setVisible(true);
		mainFrame.setBounds(100, 100, 450, 300);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setAutoRequestFocus(false);
		mainFrame.setTitle("Home Page");
		mainFrame.setBounds(100, 100, 1260, 720);
		mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));

		setLook();

		Action_panel = new JPanel();
		mainFrame.getContentPane().add(Action_panel, BorderLayout.WEST);
		
		btnCustomer = new JButton("<html>" + customer.replaceAll("\\n", "<br>") + "</html>");
		btnCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		btnCustomer.setIcon(new ImageIcon(MainWindow.class.getResource("/all_Data/customer.png")));
		btnCustomer.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		btnCustomer.setContentAreaFilled(false);
		btnCustomer.setBackground(Color.CYAN);
		btnCustomer.setForeground(Color.BLACK);
		btnCustomer.setOpaque(true);
		btnCustomer.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		btnCustomer.setMultiClickThreshhold(250);

		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerPurchase();
				mainFrame.dispose();
			}
		});

		Action_panel.setLayout(new GridLayout(0, 1, 0, 0));
		Action_panel.add(btnCustomer);

		btnStockEntry = new JButton("<html>" + stock_senpara.replaceAll("\\n", "<br>") + "</html>");
		btnStockEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowProgress("stock_senpara");
			}
		});
		btnStockEntry.setHorizontalAlignment(SwingConstants.LEFT);
		btnStockEntry.setIcon(new ImageIcon(MainWindow.class.getResource("/all_Data/data_store.png")));
		btnStockEntry.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		btnStockEntry.setContentAreaFilled(false);
		btnStockEntry.setBackground(Color.lightGray);
		btnStockEntry.setForeground(Color.BLACK);
		btnStockEntry.setOpaque(true);
		btnStockEntry.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		btnStockEntry.setMultiClickThreshhold(250);
		Action_panel.add(btnStockEntry);
		
		btnStockRemove = new JButton("<html>" + sales_senpara.replaceAll("\\n", "<br>") + "</html>");
		btnStockRemove.setHorizontalAlignment(SwingConstants.LEFT);
		btnStockRemove.setIcon(new ImageIcon(MainWindow.class.getResource("/all_Data/remove_data.png")));
		btnStockRemove.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		btnStockRemove.setContentAreaFilled(false);
		btnStockRemove.setBackground(Color.LIGHT_GRAY);
		btnStockRemove.setForeground(Color.BLACK);
		btnStockRemove.setOpaque(true);
		btnStockRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowProgress("sales_senpara");
			}
		});
		btnStockRemove.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		btnStockRemove.setMultiClickThreshhold(250);
		Action_panel.add(btnStockRemove);
		
		btnStockRemoveBoroghor = new JButton("<html>" + sales_boroghor.replaceAll("\\n", "<br>") + "</html>");
		btnStockRemoveBoroghor.setHorizontalAlignment(SwingConstants.LEFT);
		btnStockRemoveBoroghor.setIcon(new ImageIcon(MainWindow.class.getResource("/all_Data/remove_data_2.png")));
		btnStockRemoveBoroghor.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		btnStockRemoveBoroghor.setContentAreaFilled(false);
		btnStockRemoveBoroghor.setBackground(Color.CYAN);
		btnStockRemoveBoroghor.setForeground(Color.BLACK);
		btnStockRemoveBoroghor.setOpaque(true);
		btnStockRemoveBoroghor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowProgress("sales_boroghor");
			}
		});
		
		btnStockEntryBoroghor = new JButton("<html>" + stock_boroghor.replaceAll("\\n", "<br>") + "</html>");
		btnStockEntryBoroghor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowProgress("stock_boroghor");
			}
		});
		btnStockEntryBoroghor.setHorizontalAlignment(SwingConstants.LEFT);
		btnStockEntryBoroghor.setIcon(new ImageIcon(MainWindow.class.getResource("/all_Data/data_store_2.png")));
		btnStockEntryBoroghor.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		btnStockEntryBoroghor.setContentAreaFilled(false);
		btnStockEntryBoroghor.setBackground(Color.cyan);
		btnStockEntryBoroghor.setForeground(Color.BLACK);
		btnStockEntryBoroghor.setOpaque(true);
		btnStockEntryBoroghor.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		btnStockEntryBoroghor.setMultiClickThreshhold(250);
		Action_panel.add(btnStockEntryBoroghor);
		btnStockRemoveBoroghor.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		btnStockRemoveBoroghor.setMultiClickThreshhold(250);
		Action_panel.add(btnStockRemoveBoroghor);
		

		btnStockHistory = new JButton("<html>" + stock_history.replaceAll("\\n", "<br>") + "</html>");
		btnStockHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StockHistory();
				mainFrame.dispose();
			}
		});
		btnStockHistory.setHorizontalAlignment(SwingConstants.LEFT);
		btnStockHistory.setIcon(new ImageIcon(MainWindow.class.getResource("/all_Data/history.png")));
		btnStockHistory.setBorder(BorderFactory.createLineBorder(Color.white, 1));
		btnStockHistory.setContentAreaFilled(false);
		btnStockHistory.setBackground(Color.lightGray);
		btnStockHistory.setForeground(Color.BLACK);
		btnStockHistory.setOpaque(true);
		btnStockHistory.setFont(new Font("Segoe UI Symbol", Font.BOLD, 11));
		btnStockHistory.setMultiClickThreshhold(250);
		Action_panel.add(btnStockHistory);

		panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		mainFrame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		lblStartLabel = new JLabel("Let's Start");
		lblStartLabel.setFont(new Font("Snap ITC", Font.PLAIN, 17));
		lblStartLabel.setForeground(Color.RED);
		lblStartLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/all_Data/icon-get-started.png")));
		panel.add(lblStartLabel, BorderLayout.CENTER);
		
	}
}
