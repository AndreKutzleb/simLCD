package Interfaces;


public interface LCD {

	/**
	 * Get backlight status.
	 * 
	 * @return
	 */
	public abstract boolean isBacklight();

	/**
	 * Turn backlight on or off.
	 * 
	 * @param backlight
	 */
	public abstract void setBacklight(boolean backlight);

	/**
	 * Switch backlight to opposite status.
	 */
	public abstract void toggleBacklicht();

	/**
	 * Get the actual display contrast.
	 * 
	 * @return
	 */
	public abstract int getContrast();

	/**
	 * Set display cntrast.
	 * 
	 * @param contrast
	 *            (0..63)
	 */
	public abstract void setContrast(int contrast);

	/**
	 * Increase contrast by 1.
	 */
	public abstract void increaseContrast();

	/**
	 * Decrease contrast by 1.
	 */
	public abstract void decreaseContrast();

	/**
	 * Set font.
	 * 
	 * @param size
	 *            0..3 for Fonts
	 */
	public abstract void setFont(int size);

	/**
	 * Print text to screen using xy values and a string.
	 * 
	 * @param x
	 * @param y
	 * @param text
	 */
	public abstract void printXY(int x, int y, String text);

	/**
	 * Transfer Framebuffer (RAM) to display via SPI.
	 */
	public abstract void writeFramebuffer();

	/**
	 * Delete screen content.
	 */
	public abstract void clearScreen();

	/**
	 * Set pen color.
	 * 
	 * @param color
	 *            0=White 1=Black
	 */
	public abstract void setPenColor(int color);

	/**
	 * Set fill color.
	 * 
	 * @param color
	 *            -1=transparent, 0=white, 1=black
	 */
	public abstract void setFillColor(int color);

	/**
	 * Set single pixel at x,y.
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	public abstract void putPixel(int x, int y, int color);

	/**
	 * Draw line from xy to xy.
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 */
	public abstract void drawLine(int x0, int y0, int x1, int y1);

	/**
	 * Draw circle with centerpont an radius linewidth=1 pixel, no fill.
	 * 
	 * @param x
	 * @param y
	 * @param radius
	 */
	public abstract void drawCircle(int x, int y, int radius);

	/**
	 * Draw ellipse with centerpont an "radius" linewidth=1 pixel, no fill.
	 * 
	 * @param xm
	 * @param ym
	 * @param a
	 * @param b
	 */
	public abstract void drawEllipse(int xm, int ym, int a, int b);

	/**
	 * Draw rectangle, with pencolor and fillcolor. Two diagonal points and
	 * linewidth.
	 * 
	 * @param x0
	 * @param y0
	 * @param x1
	 * @param y1
	 * @param line
	 */
	public abstract void drawRect(int x0, int y0, int x1, int y1, int line);

	/**
	 * Startpoint xy, bitMapArray of image.
	 * 
	 * @param x
	 * @param y
	 * @param bitmaparray
	 */
	public abstract void drawBitmap(int x, int y, int[] bitMapArray);

	/**
	 * Get Raspberry Pi hardware version.
	 * 
	 * @return
	 */
	public abstract int getRaspberryHwRevision();

}