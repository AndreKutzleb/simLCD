package LCD;

import javax.swing.JPanel;

import notdecided.StatusBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class ScreenModule extends JPanel {

	private final StatusBar statusBar;
	public ScreenPanel getPanel() {
		return panel;
	}

	private final ScreenPanel panel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7885028161277289543L;

	/**
	 * Create the panel.
	 */
	public ScreenModule(LCDModel lcd, Color initialColor, Color penColor,
			int sizeFactor) {
		setLayout(new BorderLayout(0, 0));
		// TODO nondummy parameters
		panel = new ScreenPanel(lcd, initialColor, penColor, sizeFactor);
		add(panel, BorderLayout.CENTER);
		
		statusBar = new StatusBar(lcd);
		add(statusBar, BorderLayout.SOUTH);
		
		panel.addScreenMetadataListener(statusBar);
		
		
	}

}
