package notdecided;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import LCD.FrameBufferModule;
import LCD.JAccordion;
import LCD.LCDModel;
import LCD.ScreenModule;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;


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
		frame.setBounds(100, 100, 802, 678);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Screen", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.add(panel_2);
		
		ScreenModule screenModule = new ScreenModule(model, Consts.backgroundColor, Color.black, 4);
		panel_2.add(screenModule);
		
		final JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Framebuffer", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.add(panel_3);
		
		final FrameBufferModule frameBufferModule = new FrameBufferModule(model, Color.gray, Color.black, 4);
		panel_3.add(frameBufferModule);
		
		
		panel_3.setVisible(false);
		
		
		JPanel drawing = new JPanel();
		drawing.setLayout(new WrappingFlowLayout());
		
		
		JPanel text = new JPanel();
		text.setLayout(new WrappingFlowLayout());
		
		JPanel info = new JPanel();
		info.setLayout(new WrappingFlowLayout());
		
		final JPanel commandBorder = new JPanel();
		commandBorder.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Commands", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		
		frame.getContentPane().add(commandBorder, BorderLayout.EAST);
		
		commandBorder.setVisible(false);
		
		JScrollPane scrollPane = new CommandPane(model);
		commandBorder.add(scrollPane);
		
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
		
		final JCheckBoxMenuItem chckbxmntmFramebuffer = new JCheckBoxMenuItem("Framebuffer");
		chckbxmntmFramebuffer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					panel_3.setVisible(chckbxmntmFramebuffer.isSelected());
					NewGUI.this.frame.pack();
				
			}
		});
		
		mnView.add(chckbxmntmFramebuffer);
		
		final JCheckBoxMenuItem chckbxmntmCommands = new JCheckBoxMenuItem("Commands");
		
		chckbxmntmCommands.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				commandBorder.setVisible(chckbxmntmCommands.isSelected());
				NewGUI.this.frame.pack();
				
			}
		});
		
		mnView.add(chckbxmntmCommands);
		
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	}
	
	
}
