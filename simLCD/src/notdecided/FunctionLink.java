package notdecided;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaces.LCD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public abstract class FunctionLink extends JPanel{

	protected final JTextField[] inputFields;
	protected final LCD lcd;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3560197699563058552L;
	private JTextField textField;

	public FunctionLink(String functionName, String[] parameterNames, LCD lcd) {
		super();
		
		this.lcd = lcd;
		this.inputFields = new JTextField[parameterNames.length];
		
		
//		this.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
//				229)), "", TitledBorder.LEADING, TitledBorder.TOP, null,
//				null));
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
		JLabel title = new JLabel(functionName + ": ");
		add(title);
		
		for (int i = 0; i < parameterNames.length; i++) {
			JLabel lblNewLabel = new JLabel(parameterNames[i]);
			add(lblNewLabel);
			
			textField = new JTextField();
			add(textField);
			inputFields[i] = textField;
			textField.setColumns(5);
			
		}
		
		JButton btnExecute = new JButton("go");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				action();
			}
		});
		add(btnExecute);		
	}
	
	public abstract void action();

}
