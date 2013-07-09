package notdecided;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import Events.ScreenMetadata;
import Interfaces.ScreenChangeListener;
import Interfaces.ScreenMetadataListener;
import LCD.LCDModel;


public class StatusBar extends JPanel implements ScreenMetadataListener, ScreenChangeListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4942410523704853090L;

	private JLabel lblPositionOn;
	private JLabel lblBacklight;
	private JLabel lblContrast;
	private final String POSLABELTEXT = "Mouse at x=%3s, y=%2s   "; 
	private final LCDModel model;
	JToggleButton lightButton;
	
	Timer increaseTimer = new Timer(100,this);
	Timer decreaseTimer = new Timer(100,this);
	
	
	/**
	 * Create the panel.
	 */
	public StatusBar( final LCDModel model) {
		
		
	
		super();
		
		this.increaseTimer.setInitialDelay(500);
		this.decreaseTimer.setInitialDelay(500);
		this.increaseTimer.setActionCommand("increase");
		this.decreaseTimer.setActionCommand("decrease");
		
		this.model = model;
		model.addScreenChangeListener(this);
		
		this.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setPreferredSize(new Dimension(-1, 24));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		lblPositionOn = new JLabel();
		lblPositionOn.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
		
		lblPositionOn.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblPositionOn.setText(String.format(POSLABELTEXT,0,0));
		add(lblPositionOn);
		
		lblBacklight = new JLabel("Backlight:");
		add(lblBacklight);
		
		lightButton = new JToggleButton();
		 if(model.isBacklight())
	        {
			 lightButton.setText("On");
	            lightButton.setSelected(true);
	        }
	        else
	        {
	        	lightButton.setText("Off");
	            lightButton.setSelected(false);
	        } 
		 
		 lightButton.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				 if(e.getStateChange() == ItemEvent.SELECTED)
			        {
			            model.setBacklight(true);
			        }
			        else
			        {
			           model.setBacklight(false);
			        } 
				
			}
		});
		add(lightButton);
		
		lblContrast = new JLabel("    Contrast: "+ model.getContrast() + "       ");
		add(lblContrast);
		
		JButton increaseContrast = new JButton("+");
		JButton decreaseContrast = new JButton("-");
		
		increaseContrast.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				increaseTimer.stop();			
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				model.increaseContrast();
				increaseTimer.start();
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {				
			}
		});
		
		decreaseContrast.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				decreaseTimer.stop();			
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				model.decreaseContrast();
				decreaseTimer.start();
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		add(increaseContrast);
		add(decreaseContrast);

	}

	@Override
	public void screenMetadataChanged(ScreenMetadata e) {
		Point currentCursorPos = e.getCursorPointingAt();
		String toPrint = String.format(POSLABELTEXT, currentCursorPos.x, currentCursorPos.y);
		lblPositionOn.setText(toPrint);
	}

	@Override
	public void screenChanged() {
		updateLabels();
	}
	
	private void updateLabels() {
		 if(model.isBacklight())
	        {
	            lightButton.setText("On");
	            lightButton.setSelected(true);
	        }
	        else
	        {
	            lightButton.setText("Off");
	            lightButton.setSelected(false);
	        } 
		 
			lblContrast.setText("    Contrast: "+model .getContrast() + "       ");

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "increase":
			model.increaseContrast();	
			break;
			
		case "decrease":
			model.decreaseContrast();
			break;
		default:
			break;
		}

	}

	
}
