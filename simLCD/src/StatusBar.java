import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;


public class StatusBar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4942410523704853090L;

	private JLabel lblPositionOn;
	private JLabel lblBacklight;
	private JLabel lblContrast;
	private final String POSLABELTEXT = "Mouse at x=%3s, y=%2s ";  
	
	/**
	 * Create the panel.
	 */
	public StatusBar() {
		super();
		this.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setPreferredSize(new Dimension(-1, 24));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		lblPositionOn = new JLabel();
		lblPositionOn.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		
		lblPositionOn.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblPositionOn);
		
		lblBacklight = new JLabel("Backlight: on       ");
		add(lblBacklight);
		
		lblContrast = new JLabel("Contrast: 16       ");
		add(lblContrast);

	}

	public void handleMouseEvent(MouseEvent e) {
		int lcdPosX = e.getX() / Consts.SIZEFACTOR;
		int lcdPosY = e.getY() / Consts.SIZEFACTOR;
		
		String toPrint = String.format(POSLABELTEXT, lcdPosX, lcdPosY);
		lblPositionOn.setText(toPrint); 
		
	}

}
