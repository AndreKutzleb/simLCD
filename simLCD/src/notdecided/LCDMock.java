package notdecided;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;

import Interfaces.LCD;
import LCD.LCDModel;
import LCD.ScreenPanel;

public class LCDMock implements LCD {

	private JFrame frame;

	private boolean backlight = true;
	private int contrast = 9;

	private Consts.FillColor fillColor = Consts.FillColor.transparent;
	private Consts.PenColor penColor = Consts.PenColor.black;
	private Fonts.type font = Fonts.type.TERMINAL_6x8;

	ScreenPanel screenPanel;
	ScreenPanel frameBufferPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LCDMock window = new LCDMock();
					window.frame.setVisible(true);
					// FunctionFrame functionFrame = new FunctionFrame();
					// functionFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LCDMock() {
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 726, 593);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 32, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JButton button = new JButton("^");
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				Graphics2D g = (Graphics2D) screenPanel.getGraphics();
				g.setColor(new Color(100, 200, 150, 100));
				// g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				// 0.8f));

				int x1 = new Random().nextInt(128);
				int y1 = new Random().nextInt(64);

				int x2 = new Random().nextInt(128);
				int y2 = new Random().nextInt(64);

				drawLine(x1, y1, x2, y2);

				drawCircle((x1 + 30) % 128, (y1 + 30) % 64,
						new Random().nextInt(20));

				frameBufferPanel.repaint();
			}

		});

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207,
				229)), "Screen", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridheight = 4;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		frame.getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 32, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		screenPanel = new ScreenPanel(new LCDModel(128, 64), Consts.backgroundColor, Color.black,contrast);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridheight = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel_2.add(screenPanel, gbc_panel);
		screenPanel.setBorder(null);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 1;
		frame.getContentPane().add(button, gbc_button);

		JButton btnNewButton = new JButton(" ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearScreen();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton(" ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleBacklicht();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 2;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton_2 = new JButton(" ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				writeFramebuffer();
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 2;
		frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);

		JFrame functions = new FunctionFrame(this);
		GridBagConstraints gbc2_panel = new GridBagConstraints();
		gbc2_panel.gridheight = 5;
		gbc2_panel.insets = new Insets(0, 0, 5, 5);
		gbc2_panel.fill = GridBagConstraints.BOTH;
		gbc2_panel.gridx = 4;
		gbc2_panel.gridy = 0;
		//frame.getContentPane().add(functions.getContentPane(), gbc2_panel);

		JButton btnNewButton_3 = new JButton(" ");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 2;
		gbc_btnNewButton_3.gridy = 3;
		frame.getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "FrameBuffer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel3 = new GridBagConstraints();
		gbc_panel3.fill = GridBagConstraints.BOTH;
		gbc_panel3.insets = new Insets(0, 0, 0, 5);
		gbc_panel3.gridx = 0;
		gbc_panel3.gridy = 4;
		frame.getContentPane().add(panel, gbc_panel3)
		;
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(Consts.WIDTH,Consts.HEIGHT));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		frameBufferPanel = new ScreenPanel(new LCDModel(128,64),Color.white, Color.black, contrast);
		panel_1.add(frameBufferPanel);
		frameBufferPanel.setLayout(new BoxLayout(frameBufferPanel, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new ButtonModule();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 2;
		gbc_panel_3.gridy = 4;
		frame.getContentPane().add(panel_3, gbc_panel_3);
		//frameBufferPanel.raster(new Color(225,225,225), Color.white);
		frameBufferPanel.repaint();

		setContrast(9);
		screenPanel.repaint();
		this.frame.pack();
	}

	@Override
	public boolean isBacklight() {
		return this.backlight;
	}

	@Override
	public void setBacklight(boolean backlight) {
		this.backlight = backlight;
		lights(this.backlight);

	}

	@Override
	public void toggleBacklicht() {
		this.backlight = !this.backlight;
		lights(this.backlight);
	}

	private void lights(boolean on) {
		BufferedImage screenImage = screenPanel.getImage();
		BufferedImage frameBufferImage = frameBufferPanel.getImage();

		Color newColor = on ? Consts.backLightColor : Consts.backgroundColor;

		for (int y = 0; y < frameBufferImage.getHeight(); y++) {
			for (int x = 0; x < frameBufferImage.getWidth(); x++) {
				if (isBackground(screenImage, x, y)) {
					screenImage.setRGB(x, y, newColor.getRGB());
				}
				if (isBackground(frameBufferImage, x, y)) {
					frameBufferImage.setRGB(x, y, newColor.getRGB());
				}
			}
		}
		screenPanel.repaint();
		frameBufferPanel.repaint();

	}

	private boolean isBackground(BufferedImage image, int x, int y) {

		int pixel = image.getRGB(x, y);

		return (pixel == Consts.backgroundColor.getRGB() || pixel == Consts.backLightColor
				.getRGB());
	}

	@Override
	public int getContrast() {
		return contrast;
	}

	@Override
	public void setContrast(int contrast) {

		if (contrast > 63) {
			this.contrast = 63;

		} else if (contrast < 0) {
			this.contrast = 0;

		} else {
			this.contrast = contrast;
		}

		// TODO GUI

	}

	@Override
	public void increaseContrast() {
		if (this.contrast < 63) {
			this.contrast++;
			// TODO GUI
		}

	}

	@Override
	public void decreaseContrast() {
		if (this.contrast > 0) {
			this.contrast--;
			// TODO GUI
		}

	}

	@Override
	public void setFont(int size) {
		switch (size) {
		case 1:  this.font = Fonts.type.FIXEDSYS_8x15;  break;
		case 2:  this.font = Fonts.type.LUCIDA_10x16;   break;
		case 3:  this.font = Fonts.type.TERMINAL_12x16; break;
		default: this.font = Fonts.type.TERMINAL_6x8;   break;
		}
	}

	@Override
	public void printXY(int x, int y, String text) {
		
		int caretPosX = 0;
		for (int i = 0; i < text.length(); i++) {
			
			int[] charToDraw = Fonts.getBytesToDraw(text.charAt(i), this.font);
			
			drawBitmap(x + caretPosX, y, charToDraw);

			caretPosX = caretPosX + charToDraw[0]; // Header field containing char width
			
		}

	}

	@Override
	public void writeFramebuffer() {
		BufferedImage screenImage = screenPanel.getImage();
		BufferedImage frameBufferImage = frameBufferPanel.getImage();

		for (int y = 0; y < frameBufferImage.getHeight(); y++) {
			for (int x = 0; x < frameBufferImage.getWidth(); x++) {
				int clr = frameBufferImage.getRGB(x, y);
				screenImage.setRGB(x, y, clr);
			}
		}

		screenPanel.repaint();

	}

	@Override
	public void clearScreen() {
		frameBufferPanel.clear(Consts.backgroundColor);
		frameBufferPanel.repaint();
	}

	@Override
	public void setPenColor(int color) {
		switch (color) {
		case 0:
			updatePenColor(Consts.PenColor.white);
			break;
		case 1:
			updatePenColor(Consts.PenColor.black);
			break;
		default:
			throw new IllegalArgumentException();
		}

	}

	private void updatePenColor(Consts.PenColor color) {
		this.penColor = color;
	}

	@Override
	public void setFillColor(int color) {
		switch (color) {
		case -1:
			updateFillColor(Consts.FillColor.transparent);
			break;
		case 0:
			updateFillColor(Consts.FillColor.white);
			break;
		case 1:
			updateFillColor(Consts.FillColor.black);
			break;
		default:
			throw new IllegalArgumentException();
		}

	}

	private void updateFillColor(Consts.FillColor color) {
		this.fillColor = color;

	}

	@Override
	public void putPixel(int x, int y, int color) {
		// TODO color
		if (positionEmpty(x, y)) {

			Graphics2D g = (Graphics2D) frameBufferPanel.getImage()
					.getGraphics();
			g.setColor(Color.black);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					0.5f));
			g.fillRect(x * Consts.SIZEFACTOR, y * Consts.SIZEFACTOR,
					Consts.SIZEFACTOR, Consts.SIZEFACTOR);
		}

	}

	private boolean positionEmpty(int x, int y) {
		BufferedImage frameBufferImage = frameBufferPanel.getImage();

		return (frameBufferImage.getRGB(x * Consts.SIZEFACTOR, y
				* Consts.SIZEFACTOR) == Consts.backgroundColor.getRGB() || frameBufferImage
				.getRGB(x * Consts.SIZEFACTOR, y * Consts.SIZEFACTOR) == Consts.backLightColor
				.getRGB());
	}

	public void putPixel(int x, int y) {
		// TODO color
		putPixel(x, y, 1);
		System.out.println("DEBUG: PUTPIXEL AT x = " + x + ", y = " + y);

	}

	@Override
	public void drawLine(int x0, int y0, int x1, int y1) {

		int dx, sx, dy, sy, err, e2; // for Bresenham
		int i;

		if (y0 == y1) // horizontale Linie
		{
			if (x0 > x1) {
				i = x0;
				x0 = x1;
				x1 = i;
			} // swap direction
			while (x0 <= x1)
				putPixel(x0++, y0, 1); // TODO COLOR
		} else if (x0 == x1) // vertikale Linie
		{
			if (y0 > y1) {
				i = y0;
				y0 = x1;
				y1 = i;
			} // swap direction
			while (y0 <= y1)
				putPixel(x0, y0++, 1); // TODO COLOR
		} else // Bresenham Algorithmus
		{
			dx = Math.abs(x1 - x0);
			sx = x0 < x1 ? 1 : -1;
			dy = -Math.abs(y1 - y0);
			sy = y0 < y1 ? 1 : -1;
			err = dx + dy;
			for (;;) {
				putPixel(x0, y0, 1); // TODO COLOR
				if (x0 == x1 && y0 == y1)
					break;
				e2 = 2 * err;
				if (e2 > dy) {
					err += dy;
					x0 += sx;
				} /* e_xy+e_x > 0 */
				if (e2 < dx) {
					err += dx;
					y0 += sy;
				} /* e_xy+e_y < 0 */

			}
		}

		frameBufferPanel.repaint();

	}

	@Override
	public void drawCircle(int x, int y, int radius) {

		if ((x + radius) >= Consts.REAL_WIDTH || ((x - radius) < 0)
				|| ((y + radius) >= Consts.REAL_HEIGHT) || (y - radius) < 0) {
			return; // TODO
		}

		int f = 1 - radius;
		int ddF_x = 0;
		int ddF_y = -2 * radius;
		int x1 = 0;
		int y1 = radius;
		int PenColor = 1;// TODO

		putPixel(x, y + radius, PenColor);
		putPixel(x, y - radius, PenColor);
		putPixel(x + radius, y, PenColor);
		putPixel(x - radius, y, PenColor);

		while (x1 < y1) {
			if (f >= 0) {
				y1--;
				ddF_y += 2;
				f += ddF_y;
			}
			x1++;
			ddF_x += 2;
			f += ddF_x + 1;

			putPixel(x + x1, y + y1, PenColor);
			putPixel(x - x1, y + y1, PenColor);
			putPixel(x + x1, y - y1, PenColor);
			putPixel(x - x1, y - y1, PenColor);
			putPixel(x + y1, y + x1, PenColor);
			putPixel(x - y1, y + x1, PenColor);
			putPixel(x + y1, y - x1, PenColor);
			putPixel(x - y1, y - x1, PenColor);
		}

	}

	@Override
	public void drawEllipse(int xm, int ym, int a, int b) {
		int dx = 0, dy = b; /* im I. Quadranten von links oben nach rechts unten */
		long a2 = a * a, b2 = b * b;
		long err = b2 - (2 * b - 1) * a2, e2; /* Fehler im 1. Schritt */

		int color = this.penColor.colorCode();

		do {
			putPixel(xm + dx, ym + dy, color); /* I. Quadrant */
			putPixel(xm - dx, ym + dy, color); /* II. Quadrant */
			putPixel(xm - dx, ym - dy, color); /* III. Quadrant */
			putPixel(xm + dx, ym - dy, color); /* IV. Quadrant */

			e2 = 2 * err;
			if (e2 < (2 * dx + 1) * b2) {
				dx++;
				err += (2 * dx + 1) * b2;
			}
			if (e2 > -(2 * dy - 1) * a2) {
				dy--;
				err -= (2 * dy - 1) * a2;
			}
		} while (dy >= 0);

		while (dx++ < a) { /* fehlerhafter Abbruch bei flachen Ellipsen (b=1) */
			putPixel(xm + dx, ym, color); /* -> Spitze der Ellipse vollenden */
			putPixel(xm - dx, ym, color);
		}

	}

	@Override
	public void drawRect(int x0, int y0, int x1, int y1, int line) {
		int x, y;

		int color = this.penColor.colorCode();
		int fillColor = this.fillColor.colorCode();

		y = y0;
		while (y <= y1) // zeilenweise durcharbeiten
		{
			x = x0;
			while (x <= x1) {
				if ((y < y0 + line) || (y > y1 - line) || (x < x0 + line)
						|| (x > x1 - line)) {
					putPixel(x, y, color);
				} else {
					if (fillColor == 0)
						putPixel(x, y, 0);
					else if (fillColor == 1)
						putPixel(x, y, 1);
				}
				x++;
			}
			y++;
		}

	}

	@Override
	public void drawBitmap(int x, int y, int[] bitMapArray) {
		int width, height;
		System.out.println("drawBitmap");
		width = bitMapArray[0];
		height = bitMapArray[1];
		System.out.println("width  = " + width);
		System.out.println("height = " + height);
		System.out.println("ArrayL = " + bitMapArray.length);
		int arrayPointer = 2;
		
		// from top to bottom of screen in byte-sized steps ( 8 bits)
		for (int zeile = 0; zeile < height; zeile++) {
			// from left to right in bitwise steps
			for (int spalte = 0; spalte < width; spalte++) {

				int currentByte = bitMapArray[arrayPointer++];

				// going bitwise through one byte
				for (int shift = 0; shift < 8; shift++) {
					
					if((currentByte & (1 << shift)) != 0) {
						putPixel(x + spalte, y + (zeile * 8) + shift); // TODO FILL
					}
					
				}

			}

		}
		frameBufferPanel.repaint();
	}

	@Override
	public int getRaspberryHwRevision() {
		return 0;
	}
}
