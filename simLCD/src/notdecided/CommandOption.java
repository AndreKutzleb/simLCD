package notdecided;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class CommandOption {
	
	
	private final List<JTextField> userInputFields = new ArrayList<JTextField>();
	
	protected String[] inputFields;
	
	public CommandOption(Component parentComponent, String 
			 title, int optionType, int messageType,String[] parameterNames) {
		
		JPanel panele = new JPanel(new GridLayout(0, 2));
		
		for (String parameter : parameterNames) {
			panele.add(new JLabel(parameter));
			
			JTextField userInput = new JTextField();
			userInputFields.add(userInput);
			panele.add(userInput);
		}
		
        int result = JOptionPane.showConfirmDialog(parentComponent, panele, title,optionType, messageType);
        if (result == JOptionPane.OK_OPTION) {
        	try {
        		copyUserInputToStringArray();
        		this.executeCommand();        		
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        } 

	}
	
	private void copyUserInputToStringArray() {
		this.inputFields = new String[userInputFields.size()];
		
		for (int i = 0; i < userInputFields.size(); i++) {
			inputFields[i] = userInputFields.get(i).getText();
		}
	}

	public abstract void executeCommand();

}
