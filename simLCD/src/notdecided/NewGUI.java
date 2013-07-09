package notdecided;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import LCD.FrameBufferModule;
import LCD.LCDModel;
import LCD.ScreenModule;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class NewGUI {

	private JFrame frame;
	private LCDModel model = new LCDModel(128,64);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGUI window = new NewGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NewGUI() {
		initialize();
		frame.pack();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1314, 683);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		ScreenModule screenModule = new ScreenModule(model, Consts.backgroundColor, Color.black, 4);
		panel.add(screenModule);
		
//		FrameBufferModule frameBufferModule = new FrameBufferModule(model, Color.gray, Color.red, 4);
//		panel.add(frameBufferModule);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		
	}
	
}
