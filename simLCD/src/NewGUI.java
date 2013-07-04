import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JToolBar;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JToggleButton;


public class NewGUI {

	private JFrame frame;
	StatusBar panel_1;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 799, 347);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		final LCDPanel screen = new LCDPanel(new Color(84, 139, 84));
		
		screen.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				panel_1.handleMouseEvent(e);
				screen.handleMouseEvent(e);}
		});
		
		
		panel.add(screen, "2, 2, right, center");
		
		ButtonModule buttons = new ButtonModule();
		panel.add(buttons, "4, 2, left, center");
		
		panel_1 = new StatusBar();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton btnAdvanced = new JButton("Open Framebuffer");
		toolBar.add(btnAdvanced);
		
		toolBar.addSeparator();
		
		JButton btnResetEverything = new JButton("Reset");
		toolBar.add(btnResetEverything);

		toolBar.addSeparator();
		
		JToggleButton tglbtnBacklight = new JToggleButton("Backlight");
		toolBar.add(tglbtnBacklight);

		toolBar.addSeparator();

		JLabel lblContrast = new JLabel("Contrast:");
		toolBar.add(lblContrast);

		toolBar.addSeparator();

		JSlider slider = new JSlider();
		Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(8);
		labelTable.put( new Integer( 0 ), new JLabel("0") );
		labelTable.put( new Integer( 15), new JLabel("15") );
		labelTable.put( new Integer( 31 ), new JLabel("31") );
		labelTable.put( new Integer( 47 ), new JLabel("47") );
		labelTable.put( new Integer( 63 ), new JLabel("63") );
		slider.setLabelTable(labelTable);

		slider.setMinimum(0);
		slider.setMaximum(63);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		toolBar.add(slider);
	}
	
	

}
