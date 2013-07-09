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
import LCD.LCDModel;
import LCD.ScreenModule;


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
		
		final FrameBufferModule frameBufferModule = new FrameBufferModule(model, Color.gray, Color.red, 4);
		panel.add(frameBufferModule);
		
		final JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		WrappingFlowLayout layout = new WrappingFlowLayout();
		toolBar.setLayout(layout);
		frame.getContentPane().add(toolBar, BorderLayout.EAST);
		
		
		frameBufferModule.setVisible(false);
		toolBar.setVisible(false);
		
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
					frameBufferModule.setVisible(chckbxmntmFramebuffer.isSelected());
					NewGUI.this.frame.pack();
				
			}
		});
		
		mnView.add(chckbxmntmFramebuffer);
		
		final JCheckBoxMenuItem chckbxmntmCommands = new JCheckBoxMenuItem("Commands");
		mnView.add(chckbxmntmCommands);
		chckbxmntmCommands.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				toolBar.setVisible(chckbxmntmCommands.isSelected());
				NewGUI.this.frame.pack();
				
			}
		});
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		

		JButton putPixel = new JButton("putPixel");
		toolBar.add(putPixel);
		
		putPixel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new CommandOption(NewGUI.this.frame, "putPixel", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, new String[] {"x", "y", "color"}) {

					@Override
					public void executeCommand() {
						int x = Integer.parseInt(inputFields[0]);
						int y = Integer.parseInt(inputFields[1]);
						int color = Integer.parseInt(inputFields[2]);
						model.putPixel(x, y, color);
					}
					
				};
			}
		});
		
		toolBar.addSeparator();
		
		JButton writeFrameBuffer = new JButton("writeFramebuffer");
		toolBar.add(writeFrameBuffer);
		
		writeFrameBuffer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.writeFramebuffer();
			}
		});
		
		
		JButton drawTests = new JButton("drawTests");
		drawTests.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.drawLine(new Random().nextInt(128),new Random().nextInt(64), new Random().nextInt(128),new Random().nextInt(64));
			}
		});
		toolBar.add(drawTests);
	}
	
	
}
