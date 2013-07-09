package notdecided;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import Events.ScreenMetadata;
import Interfaces.ScreenMetadataListener;
import LCD.LCDModel;


public class StatusBarPositionOnly extends JPanel implements ScreenMetadataListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4942410523704853090L;

	private JLabel lblPositionOn;
	private final String POSLABELTEXT = "Mouse at x=%3s, y=%2s   "; 

	/**
	 * Create the panel.
	 */
	public StatusBarPositionOnly() {

		this.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setPreferredSize(new Dimension(-1, 24));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		lblPositionOn = new JLabel();
		lblPositionOn.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		
		lblPositionOn.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblPositionOn.setText(String.format(POSLABELTEXT,0,0));
		add(lblPositionOn);

	}

	@Override
	public void screenMetadataChanged(ScreenMetadata e) {
		Point currentCursorPos = e.getCursorPointingAt();
		String toPrint = String.format(POSLABELTEXT, currentCursorPos.x, currentCursorPos.y);
		lblPositionOn.setText(toPrint);
	}
	
}
