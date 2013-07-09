package LCD;

import javax.swing.JPanel;

import Events.ScreenMetadata;

import notdecided.StatusBar;
import notdecided.StatusBarPositionOnly;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class FrameBufferModule extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8239172089249358816L;
	private final StatusBarPositionOnly statusBar;
	public AbstractDrawPanel getPanel() {
		return panel;
	}

	private final AbstractDrawPanel panel;

	/**
	 * Create the panel.
	 */
	public FrameBufferModule(LCDModel lcd, Color initialColor, Color penColor,
			int sizeFactor) {
		setLayout(new BorderLayout(0, 0));

		panel = new FrameBufferPanel(lcd, initialColor, penColor, sizeFactor);
		add(panel, BorderLayout.CENTER);
		
		statusBar = new StatusBarPositionOnly();
		add(statusBar, BorderLayout.SOUTH);
		
		panel.addScreenMetadataListener(statusBar);
		
		
	}

}
