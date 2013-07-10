package LCD;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import notdecided.Consts;
import Interfaces.ScreenChangeListener;

public class ScreenPanel extends AbstractDrawPanel implements ScreenChangeListener {

	public ScreenPanel(LCDModel lcd, Color initialColor, Color penColor,
			int sizeFactor) {
		super(lcd, initialColor, penColor, sizeFactor);
		this.backgroundColor = model.isBacklight() ? Consts.backLightColor : Consts.backgroundColor;
		this.clear(this.backgroundColor);
		this.model.addScreenChangeListener(this);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1409298407493163986L;


	@Override
	public void screenChanged() {
		updateMetaData();
		redrawFromModel();
		repaint();
	}

	private void updateMetaData() {
		if (model.isBacklight()) {
			this.backgroundColor = Consts.backLightColor;
		} else {
			this.backgroundColor = Consts.backgroundColor;
		}
		
	}

	protected void redrawFromModel() {
		boolean[][] screenData = this.model.getScreen();
		Graphics2D g = (Graphics2D)image.getGraphics();
		int pixelSize = this.sizeFactor;
		
		float alphaStrength = 0.00634f * this.model.getContrast();
		float baseContrast = 0.4f;
		float alpha = (alphaStrength + baseContrast);
		// TODO dynamic contrast values, dynamic alpha stepping
		if (alpha > .999f) {
			alpha = .999f;
		}

		// draw Background
		for (int x = 0; x < screenData.length; x++) {
			for (int y = 0; y < screenData[0].length; y++) {
				g.setColor(this.backgroundColor);
				g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
			}
		}
		
			g.setColor(this.penColor);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

		// draw pixels
		for (int x = 0; x < screenData.length; x++) {
			for (int y = 0; y < screenData[0].length; y++) {
				if (screenData[x][y]) {
					g.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
				}
			}
		}
	}


}
