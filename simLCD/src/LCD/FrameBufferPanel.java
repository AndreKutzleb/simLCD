package LCD;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import notdecided.Consts;
import Events.ScreenMetadata;
import Interfaces.FramebufferChangeListener;
import Interfaces.ScreenChangeListener;
import Interfaces.ScreenMetadataListener;

public class FrameBufferPanel extends AbstractDrawPanel implements FramebufferChangeListener {

	public FrameBufferPanel(LCDModel lcd, Color initialColor, Color penColor,
			int sizeFactor) {
		super(lcd, initialColor, penColor, sizeFactor);
		this.clear(this.backgroundColor);
		this.model.addFramebufferChangeListener(this);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1409298407493163986L;



	public void raster(Color firstColor, Color secondColor) {
		Graphics g = image.getGraphics();
		int shift = 0;

		for (int y = 0; y < this.drawHeight; y = y + this.sizeFactor) {
			for (int x = 0 + shift; x < this.drawWidth; x = x + this.sizeFactor
					* 2) {
				g.setColor(firstColor);
				g.fillRect(x, y, this.sizeFactor, this.sizeFactor);
			}
			if (shift == 0) {
				shift = this.sizeFactor;
			} else {
				shift = 0;
			}
		}
	}


	private void redrawFromModel() {
		boolean[][] frameBufferData = this.model.getFrameBuffer();
		Graphics g = image.getGraphics();
		int pixelSize = this.sizeFactor;

		for (int x = 0; x < frameBufferData.length; x++) {
			for (int y = 0; y < frameBufferData[0].length; y++) {
				if (frameBufferData[x][y]) {
					g.setColor(this.penColor);
				} else {
					g.setColor(this.backgroundColor);
				}
				g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
			}
		}
	}


	@Override
	public void notifyFramebufferChanged() {
		redrawFromModel();
		repaint();
		
	}

}
