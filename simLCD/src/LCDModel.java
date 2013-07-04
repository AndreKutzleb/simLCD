import java.awt.Rectangle;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Interfaces.FramebufferChangeListener;
import Interfaces.ScreenChangeListener;

/**
 * Represents an LCD Panel of variable size together with its framebuffer.
 * 
 * @author AndreKutzleb
 * @version 1.0
 *
 */
public class LCDModel extends Observable implements LCD {

	private final Logger logger;
	private final int width;
	private final int height;
	private final boolean[][] frameBuffer;
	private final boolean[][] screen;
	private final Rectangle boundaries;
	
	private boolean backLight;
	private int contrast;
	private Fonts.type font;
	private Consts.PenColor penColor = Consts.PenColor.black;
	private Consts.FillColor fillColor = Consts.FillColor.transparent;	
	
	private List<FramebufferChangeListener> framebufferChangeListener = new CopyOnWriteArrayList<>();
	private List<ScreenChangeListener> screenChangeListener = new CopyOnWriteArrayList<>();
	
	public LCDModel (final int width,final int height, boolean backlight, int initialcontrast) {
		this.logger = Logger.getLogger(LCDModel.class.getName());
		
		
		
		if (contrast < 0 || contrast > 63) {
			String errorMessage = "tried to set initial contrast to " + contrast + ", values between 0-63 allowed only. Instance construction stopped.";
			logger.log(Level.SEVERE, errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
		
		if(width <= 0) {
			String errorMessage = "width cannot be smaller than 1. Instance construction stopped.";
			logger.log(Level.SEVERE, errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
		
		if(height <= 0) {
			String errorMessage = "height must not be smaller than 0. Instance Construction stopped.";
			logger.log(Level.SEVERE, errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
				
		this.width = width;
		this.height = height;
		this.frameBuffer = new boolean[width][height];
		this.screen = new boolean[width][height];
		this.boundaries = new Rectangle(width, height);
		this.backLight = backlight;	
		this.contrast = initialcontrast;
	}
	
	/**
	 * Default RaspiLCD constructor with backlights on and contrast of 9.
	 * 
	 * @param width
	 * @param height
	 */
	public LCDModel (final int width,final int height) {
		this(width,height,true,9);
	}
	
	/**
	 * @param x
	 * @param y
	 * @return if the point is inside the bounds of the screen
	 */
	private boolean onScreen(int x, int y) {
		return boundaries.contains(x,y);
	}
	
	
	public void putPixel(int x, int y, int color) {
		if (!onScreen(x,y)) {
			logger.log(Level.WARNING, "tried to put Pixel at x=" +x + ", y="+y+" (out of screen bounds). Command Ignored.");
			return;
		}
		if (color == 0) {
			this.frameBuffer[x][y] = false;			
		} else {
			this.frameBuffer[x][y] = true;
		}
		fireFramebufferChangeEvent();

	}


	@Override
	public boolean isBacklight() {
		return this.backLight;
	}

	@Override
	public void setBacklight(boolean backlight) {
		if (backlight != this.backLight) {
			this.backLight = backlight;			
			fireScreenChangeEvent();
		}	
	}

	@Override
	public void toggleBacklicht() {
		this.backLight = !this.backLight;
		fireScreenChangeEvent();
		
	}

	@Override
	public int getContrast() {
		return this.contrast;
	}

	@Override
	public void setContrast(int contrast) {
		if (!(contrast >= 0 && contrast < 64)) {
			logger.log(Level.WARNING, "tried to set contrast to " + contrast + ", values between 0-63 allowed only. Command Ignored.");
			return;
		}
		if (contrast != this.contrast) {
			this.contrast = contrast;			
			fireScreenChangeEvent();
		}
		
	}

	@Override
	public void increaseContrast() {
		if(this.contrast >= 63) {
			logger.log(Level.WARNING, "tried to increase contrast above the value of 63. Command Ignored.");
			return;
		}
		this.contrast++;
		fireScreenChangeEvent();
		
	}

	@Override
	public void decreaseContrast() {
		if(this.contrast <= 0) {
			logger.log(Level.WARNING, "tried to decrease contrast below the value of 0. Command Ignored.");
			return;
		}
		this.contrast--;
		fireScreenChangeEvent();
		
	}

	@Override
	public void setFont(int size) {
		if(size < 0 || size > 3) {
			logger.log(Level.WARNING, "set Font to a value other than 0..3. Command Ignored.");
			return;
		}
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
		fireFramebufferChangeEvent();
		
	}

	@Override
	public void writeFramebuffer() {
		for (int x = 0; x < frameBuffer.length; x++) {
			for (int y = 0; y < frameBuffer[0].length; y++) {
				screen[x][y] = frameBuffer[x][y];
			}
		}
		fireScreenChangeEvent();
	}

	@Override
	public void clearScreen() {
		for (int x = 0; x < frameBuffer.length; x++) {
			for (int y = 0; y < frameBuffer[0].length; y++) {
				frameBuffer[x][y] = false;
			}
		}
		fireFramebufferChangeEvent();
	}

	@Override
	public void setPenColor(int color) {
			switch (color) {
			case 0:
				this.penColor = Consts.PenColor.white;
				break;
			case 1:
				this.penColor = Consts.PenColor.black;
				break;
			default:
				logger.log(Level.WARNING, "set penColor to a value other than 0..1. Command Ignored.");
				return;
			}	
	}

	@Override
	public void setFillColor(int color) {
		switch (color) {
		case -1:
			this.fillColor = Consts.FillColor.transparent;
			break;
		case 0:
			this.fillColor = Consts.FillColor.white;
			break;
		case 1:
			this.fillColor = Consts.FillColor.black;
			break;
		default:
			logger.log(Level.WARNING, "set fillColor to a value other than -1..1. Command Ignored.");
			return;
		}
		
	}

	@Override
	public void drawLine(int x0, int y0, int x1, int y1) {

		int dx, sx, dy, sy, err, e2; // for Bresenham
		int i;
		int penColor = this.penColor.colorCode();

		if (y0 == y1) // horizontale Linie
		{
			if (x0 > x1) {
				i = x0;
				x0 = x1;
				x1 = i;
			} // swap direction
			while (x0 <= x1)
				putPixel(x0++, y0, penColor);
		} else if (x0 == x1) // vertikale Linie
		{
			if (y0 > y1) {
				i = y0;
				y0 = x1;
				y1 = i;
			} // swap direction
			while (y0 <= y1)
				putPixel(x0, y0++, penColor);
		} else // Bresenham Algorithmus
		{
			dx = Math.abs(x1 - x0);
			sx = x0 < x1 ? 1 : -1;
			dy = -Math.abs(y1 - y0);
			sy = y0 < y1 ? 1 : -1;
			err = dx + dy;
			for (;;) {
				putPixel(x0, y0, penColor); 
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
		fireFramebufferChangeEvent();
		
	}

	@Override
	public void drawCircle(int x, int y, int radius) {

		int f = 1 - radius;
		int ddF_x = 0;
		int ddF_y = -2 * radius;
		int x1 = 0;
		int y1 = radius;
		int penColor = this.penColor.colorCode();

		putPixel(x, y + radius, penColor);
		putPixel(x, y - radius, penColor);
		putPixel(x + radius, y, penColor);
		putPixel(x - radius, y, penColor);

		while (x1 < y1) {
			if (f >= 0) {
				y1--;
				ddF_y += 2;
				f += ddF_y;
			}
			x1++;
			ddF_x += 2;
			f += ddF_x + 1;

			putPixel(x + x1, y + y1, penColor);
			putPixel(x - x1, y + y1, penColor);
			putPixel(x + x1, y - y1, penColor);
			putPixel(x - x1, y - y1, penColor);
			putPixel(x + y1, y + x1, penColor);
			putPixel(x - y1, y + x1, penColor);
			putPixel(x + y1, y - x1, penColor);
			putPixel(x - y1, y - x1, penColor);
		}
		fireFramebufferChangeEvent();

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
		fireFramebufferChangeEvent();

	}

	@Override
	public void drawRect(int x0, int y0, int x1, int y1, int line) {
		int x, y;

		int penColor = this.penColor.colorCode();
		int fillColor = this.fillColor.colorCode();

		y = y0;
		while (y <= y1) // zeilenweise durcharbeiten
		{
			x = x0;
			while (x <= x1) {
				if ((y < y0 + line) || (y > y1 - line) || (x < x0 + line)
						|| (x > x1 - line)) {
					putPixel(x, y, penColor);
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

		fireFramebufferChangeEvent();
		
	}

	@Override
	public void drawBitmap(int x, int y, int[] bitMapArray) {
		int width, height;
		
		if (bitMapArray.length < 3) {
			logger.log(Level.WARNING, "bitmap array would have to be at least 3 elements long. length is " + bitMapArray.length + ". Command Ignored.");
			return;
		}
		width = bitMapArray[0];
		height = bitMapArray[1];
		
		// Header length arguments do not match array length
		if (((width * height) + 2) != bitMapArray.length) {
			logger.log(Level.WARNING, "bitmap[0]=" + width +" (width in bits), bitmap[1]="+ height +" (height in bytes), does not match the array length("+ bitMapArray.length +", should be: " + (width*height) + ") no valid bitmap. Command Ignored.");
			return;
		}

		int arrayPointer = 2;
		
		// from top to bottom of screen in byte-sized steps ( 8 bits)
		for (int zeile = 0; zeile < height; zeile++) {
			// from left to right in bitwise steps
			for (int spalte = 0; spalte < width; spalte++) {

				int currentByte = bitMapArray[arrayPointer++];

				// going bitwise through one byte
				for (int shift = 0; shift < 8; shift++) {
					
					if((currentByte & (1 << shift)) != 0) {
						putPixel(x + spalte, y + (zeile * 8) + shift,1);
					} else {
						putPixel(x + spalte, y + (zeile * 8) + shift,0);
					}	
				}
			}
		}			
		fireFramebufferChangeEvent();

	}

	@Override
	public int getRaspberryHwRevision() {
		logger.log(Level.INFO, "RaspberryHWRevision was called on simulated screen, returning 0.");
		return 0;
	}
	
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean[][] getFrameBuffer() {
		return frameBuffer;
	}
	
	public boolean[][] getScreen() {
		return screen;
	}
	
	public void addFramebufferChangeListener(FramebufferChangeListener listener) {
		this.framebufferChangeListener.add(listener);
	}
	
	public void removeFramebufferChangeListener(FramebufferChangeListener listener) {
		this.framebufferChangeListener.remove(listener);
	}
	
	private void fireFramebufferChangeEvent() {
		for (FramebufferChangeListener listener : this.framebufferChangeListener) {
			listener.notifyFramebufferChanged();
		}
	}
	
	public void addScreenChangeListener(ScreenChangeListener listener) {
		this.screenChangeListener.add(listener);
	}

	public void removeScreenChangeListener(ScreenChangeListener listener) {
		this.screenChangeListener.remove(listener);
	}
	
	private void fireScreenChangeEvent() {
		for (ScreenChangeListener listener : this.screenChangeListener) {
			listener.notifyScreenChanged();
		}
	}
}
