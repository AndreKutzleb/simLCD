package notdecided;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;


public class Consts {
	
	public enum FillColor {
		transparent(-1), 
		white(0),
		black(1);
		
		private final int colorCode;
		
		private FillColor(int color) {
			this.colorCode = color;
		}
		
		public int colorCode() { 
			return colorCode;
			}	
	}
	
	public enum PenColor {
		white(0), 
		black(1);
		
	private final int colorCode;
		
		private PenColor(int color) {
			this.colorCode = color;
		}
		
		public int colorCode() { 
			return colorCode;
			}	
	}
	
	public static final Color backgroundColor = new Color(5540692); // background
	public static final Color backLightColor = new Color(10157978); // background with light
	
	
	public static final int REAL_WIDTH = 128;
	public static final int REAL_HEIGHT = 64;

	public static final int SIZEFACTOR = 4;
	
	public static final int WIDTH = SIZEFACTOR * REAL_WIDTH;
	public static final int HEIGHT = SIZEFACTOR * REAL_HEIGHT;

	
}
