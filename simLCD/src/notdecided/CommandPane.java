package notdecided;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import LCD.LCDModel;

public class CommandPane extends JScrollPane{

	LCDModel model;
	
	private JPanel commands = new JPanel();

	private JPanel drawing = new JPanel();

	private JPanel text = new JPanel();

	private JPanel info = new JPanel();


	/**
	 * 
	 */
	private static final long serialVersionUID = 4943759008547380835L;

	/**
	 * Create the application.
	 */
	public CommandPane(LCDModel model) {
		super();
		this.model = model;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JPanel panel = new JPanel();
	
		panel.setLayout(new GridLayout(0,1));
		setViewportView(panel);
		
		commands.setLayout(new BoxLayout(commands, BoxLayout.Y_AXIS));
		commands.setBorder(new TitledBorder(null, "commands", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		drawing.setLayout(new BoxLayout(drawing, BoxLayout.Y_AXIS));
		drawing.setBorder(new TitledBorder(null, "drawing", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
		text.setBorder(new TitledBorder(null, "text", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		info.setBorder(new TitledBorder(null, "info", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
	
		panel.add(commands);
		panel.add(drawing);
		panel.add(text);
		panel.add(info);
		
		
		
		
		
		
		
		
		
		
		addButtons();
		centerButtons();

	}

	private void addButtons() {
		addButtonsCommand();
		addButtonsDrawing();
		addButtonsText();
		addButtonsInfo();
		
	}


	private void addButtonsCommand() {	
		
		JButton writeFrameBuffer = new JButton("writeFramebuffer");
		commands.add(writeFrameBuffer);
		
		writeFrameBuffer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.writeFramebuffer();
			}
		});	
		
	}

	private void addButtonsDrawing() {
		
		JButton putPixel = new JButton("putPixel");
		drawing.add(putPixel);
		
		putPixel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new CommandOption(CommandPane.this, "putPixel", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, new String[] {"x", "y", "color"}) {

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
		
		JButton drawTests = new JButton("drawRandomLine");
		drawTests.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.drawLine(new Random().nextInt(128),new Random().nextInt(64), new Random().nextInt(128),new Random().nextInt(64));
			}
		});
		drawing.add(drawTests);
		
	}
	
	private void addButtonsText() {
		// TODO Auto-generated method stub
		
	}
	
	private void addButtonsInfo() {
		// TODO Auto-generated method stub
		
	}
	
	
	private void centerButtons() {
		for (Component comp : drawing.getComponents()) {
			if (comp instanceof JButton) {
				((JButton)comp).setAlignmentX(Component.CENTER_ALIGNMENT);
			}
		}
		
		for (Component comp : commands.getComponents()) {
			if (comp instanceof JButton) {
				((JButton)comp).setAlignmentX(Component.CENTER_ALIGNMENT);
			}
		}
		
		for (Component comp : text.getComponents()) {
			if (comp instanceof JButton) {
				((JButton)comp).setAlignmentX(Component.CENTER_ALIGNMENT);
			}
		}
		
		for (Component comp : info.getComponents()) {
			if (comp instanceof JButton) {
				((JButton)comp).setAlignmentX(Component.CENTER_ALIGNMENT);
			}
		}
	}
		
		
	

}
