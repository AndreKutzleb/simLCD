import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LCDModel implements LCD {

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
	
	
	public LCDModel (final int width,final int height, boolean backlight, int initialcontrast) {
		this.logger = Logger.getLogger(LCDModel.class.getName());
		
		
		
		if (contrast < 0 || contrast > 63) {
			String errorMessage = "tried to set initial contrast to " + contrast + ", values between 0-63 allowed only. Construction stopped.";
			logger.log(Level.SEVERE, errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
		
		if(width <= 0) {
			String errorMessage = "width cannot be smaller than 1. Construction stopped.";
			logger.log(Level.SEVERE, errorMessage);
			throw new IllegalArgumentException(errorMessage);
		}
		
		if(height <= 0) {
			String errorMessage = "height must not be smaller than 0. Construction stopped.";
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
			logger.log(Level.WARNING, "tried to put Pixel at x=" +x + ", y="+y+" (out of screen bounds). Command Denied.");
			return;
		}
		if (color == 0) {
			this.frameBuffer[x][y] = false;			
		} else {
			this.frameBuffer[x][y] = true;
		}

	}


	@Override
	public boolean isBacklight() {
		return this.backLight;
	}

	@Override
	public void setBacklight(boolean backlight) {
		this.backLight = backlight;
		
	}

	@Override
	public void toggleBacklicht() {
		this.backLight = !this.backLight;
		
	}

	@Override
	public int getContrast() {
		return this.contrast;
	}

	@Override
	public void setContrast(int contrast) {
		if (!(contrast >= 0 && contrast < 64)) {
			logger.log(Level.WARNING, "tried to set contrast to " + contrast + ", values between 0-63 allowed only. Command Denied.");
			return;
		}
		this.contrast = contrast;
		
	}

	@Override
	public void increaseContrast() {
		if(this.contrast >= 63) {
			logger.log(Level.WARNING, "tried to increase contrast above the value of 63. Command Denied.");
			return;
		}
		this.contrast++;
		
	}

	@Override
	public void decreaseContrast() {
		if(this.contrast <= 0) {
			logger.log(Level.WARNING, "tried to decrease contrast below the value of 0. Command Denied.");
			return;
		}
		this.contrast--;
		
	}

	@Override
	public void setFont(int size) {
		if(size < 0 || size > 3) {
			logger.log(Level.WARNING, "set Font to a value other than 0..3. Command Denied.");
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

		
	}

	@Override
	public void writeFramebuffer() {
		for (int x = 0; x < frameBuffer.length; x++) {
			for (int y = 0; y < frameBuffer[0].length; y++) {
				screen[x][y] = frameBuffer[x][y];
			}
			
		}
		
	}

	@Override
	public void clearScreen() {
		
		
	}

	@Override
	public void setPenColor(int color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFillColor(int color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawLine(int x0, int y0, int x1, int y1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawCircle(int x, int y, int radius) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawEllipse(int xm, int ym, int a, int b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawRect(int x0, int y0, int x1, int y1, int line) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawBitmap(int x, int y, int[] bitMapArray) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRaspberryHwRevision() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
