import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;



public class FunctionFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public FunctionFrame(LCD lcd) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); 
		
;
		initialize(lcd);
	}

	private void initialize(LCD lcd) {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 
		scrollPane.setViewportView(panel);
		
		FunctionLink functionLink = new FunctionLink("clearScreen", new String[] {}, lcd) {
			@Override
			public void action() {
				this.lcd.clearScreen();
			}};
			panel.add(functionLink);

		FunctionLink functionLink_1 = new FunctionLink("setPenColor", new String[] {"c"}, lcd){
			@Override
			public void action() {
				try {
					int pencolor = Integer.parseInt(inputFields[0].getText());
					lcd.setPenColor(pencolor);
				} catch  (Exception e) {
					e.printStackTrace();
				}			
			}};
			
		panel.add(functionLink_1);
		
		FunctionLink functionLink_2 = new FunctionLink("setFillColor", new String[] {"c"}, lcd){

			@Override
			public void action() {
				try {
					int fillcolor = Integer.parseInt(inputFields[0].getText());
					lcd.setFillColor(fillcolor);
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_2);
		
		FunctionLink functionLink_3 = new FunctionLink("setFont", new String[] {"f"}, lcd){

			@Override
			public void action() {
				try {
					int font = Integer.parseInt(inputFields[0].getText());
					lcd.setFont(font);
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_3);
		
		FunctionLink functionLink_4 = new FunctionLink("setContrast", new String[] {"contrast"}, lcd){

			@Override
			public void action() {
				try {
					int contrast = Integer.parseInt(inputFields[0].getText());
					lcd.setContrast(contrast);
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_4);
		
		FunctionLink functionLink_5 = new FunctionLink("putPixel", new String[] {"x", "y", "color"}, lcd){

			@Override
			public void action() {
				try {
					int x = Integer.parseInt(inputFields[0].getText());
					int y = Integer.parseInt(inputFields[1].getText());
					int color = Integer.parseInt(inputFields[2].getText());
					lcd.putPixel(x, y, color);
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_5);
		
		FunctionLink functionLink_6 = new FunctionLink("drawLine", new String[] {"x0", "y0", "x1", "y1"}, lcd){
			@Override
			public void action() {
				try {
					int x0 = Integer.parseInt(inputFields[0].getText());
					int y0 = Integer.parseInt(inputFields[1].getText());
					int x1 = Integer.parseInt(inputFields[2].getText());
					int y1 = Integer.parseInt(inputFields[3].getText());

					lcd.drawLine(x0, y0, x1, y1);
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_6);
		
		FunctionLink functionLink_7 = new FunctionLink("drawCircle", new String[] {"x0", "y0", "radius"}, lcd){
			@Override
			public void action() {
				try {
					int x0 = Integer.parseInt(inputFields[0].getText());
					int y0 = Integer.parseInt(inputFields[1].getText());
					int radius = Integer.parseInt(inputFields[2].getText());

					lcd.drawCircle(x0, y0, radius);
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_7);
		
		FunctionLink functionLink_8 = new FunctionLink("drawEllipse", new String[] {"xm", "ym", "a", "b"}, lcd){
			@Override
			public void action() {
				try {
					int xm = Integer.parseInt(inputFields[0].getText());
					int ym = Integer.parseInt(inputFields[1].getText());
					int a = Integer.parseInt(inputFields[2].getText());
					int b = Integer.parseInt(inputFields[3].getText());

					lcd.drawEllipse(xm, ym, a, b);
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_8);
		
		FunctionLink functionLink_9 = new FunctionLink("drawRect", new String[] {"x0", "y0", "x1", "y1", "line"}, lcd){
			@Override
			public void action() {
				try {
					int x0 = Integer.parseInt(inputFields[0].getText());
					int y0 = Integer.parseInt(inputFields[1].getText());
					int x1 = Integer.parseInt(inputFields[2].getText());
					int y1 = Integer.parseInt(inputFields[3].getText());
					int line = Integer.parseInt(inputFields[4].getText());

					lcd.drawRect(x0, y0, x1, y1, line);
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_9);
		
		FunctionLink functionLink_10 = new FunctionLink("printXY", new String[] {"x0", "y0", "s"}, lcd){
			@Override
			public void action() {
				try {
					int x0 = Integer.parseInt(inputFields[0].getText());
					int y0 = Integer.parseInt(inputFields[1].getText());
					String text = inputFields[2].getText();
					lcd.printXY(x0, y0, text);
					
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_10);
		
		FunctionLink functionLink_11 = new FunctionLink("drawBitmap", new String[] {"x0", "y0", "bmp (comma-separated ints)"}, lcd){
			@Override
			public void action() {
				try {
					int x0 = Integer.parseInt(inputFields[0].getText());
					int y0 = Integer.parseInt(inputFields[1].getText());
					String bitMapString = inputFields[2].getText();
					
					String[] split = bitMapString.split(",");
					int[] bmp = new int[split.length];
					for (int i = 0; i < bmp.length; i++) {
						bmp[i] = Integer.parseInt(split[i]);
					}

					lcd.drawBitmap(x0, y0, bmp);
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_11);

		
		FunctionLink functionLink_13 = new FunctionLink("writeFramebuffer", new String[] {}, lcd){
			@Override
			public void action() {
				try {
					this.lcd.writeFramebuffer();
					
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_13);
		
		FunctionLink functionLink_16 = new FunctionLink("getRaspberryHwRevision", new String[] {}, lcd){
			@Override
			public void action() {
				try {
					this.lcd.getRaspberryHwRevision(); // TODO
					
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_16);
		
		FunctionLink functionLink_17 = new FunctionLink("isBacklight", new String[] {}, lcd){
			@Override
			public void action() {
				try {
					this.lcd.isBacklight(); // TODO		
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_17);
		
		FunctionLink functionLink_18 = new FunctionLink("setBacklight", new String[] {"true/false"}, lcd){
			@Override
			public void action() {
				try {
					boolean a = Boolean.parseBoolean(inputFields[0].getText());
					lcd.setBacklight(a);
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_18);
		
		FunctionLink functionLink_19 = new FunctionLink("toggleBacklicht", new String[] {}, lcd){
			@Override
			public void action() {
				try {
					this.lcd.toggleBacklicht();
					
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_19);
		
		FunctionLink functionLink_20 = new FunctionLink("getContrast", new String[] {}, lcd){
			@Override
			public void action() {
				try {
					this.lcd.getContrast(); // TODO
					
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_20);		
	
		
		FunctionLink functionLink_21 = new FunctionLink("increaseContrast", new String[] {}, lcd){
			@Override
			public void action() {
				try {
					this.lcd.increaseContrast();
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_21);
	

		FunctionLink functionLink_22 = new FunctionLink("decreaseContrast", new String[] {}, lcd){
			@Override
			public void action() {
				try {
					this.lcd.decreaseContrast();		
				} catch  (Exception e) {
					e.printStackTrace();
				}		
				
			}};
		panel.add(functionLink_22);


	}

}
