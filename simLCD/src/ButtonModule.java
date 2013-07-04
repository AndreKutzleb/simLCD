import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class ButtonModule extends JPanel {

	private static final int BUTTONSIZE = 50;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8693043730125783454L;

	/**
	 * Create the panel.
	 */
	public ButtonModule() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JButton btnNewButton_4 = new RoundButton("",BUTTONSIZE);
		add(btnNewButton_4, "4, 2");
		
		JButton btnNewButton_1 =  new RoundButton("",BUTTONSIZE);
		add(btnNewButton_1, "2, 4");
		
		JButton btnNewButton =  new RoundButton("",BUTTONSIZE);
		add(btnNewButton, "4, 4");
		
		JButton btnNewButton_3 =  new RoundButton("",BUTTONSIZE);
		add(btnNewButton_3, "6, 4");
		
		JButton btnNewButton_2 =  new RoundButton("",BUTTONSIZE);
		add(btnNewButton_2, "4, 6");

	}

}
