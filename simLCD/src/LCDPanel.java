import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class LCDPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1409298407493163986L;

	private BufferedImage image;
	private Point drawCursorAt = null;

	BufferedImage cursorImg = new BufferedImage(16, 16,
			BufferedImage.TYPE_INT_ARGB);
	Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
			cursorImg, new Point(0, 0), "blank cursor");

	public LCDPanel(Color initialColor) {
		super();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.image = new BufferedImage(Consts.WIDTH, Consts.HEIGHT,
				ColorSpace.TYPE_RGB);
		this.clear(initialColor);
		this.setPreferredSize(new Dimension(Consts.WIDTH, Consts.HEIGHT));
		this.setMinimumSize(new Dimension(Consts.WIDTH, Consts.HEIGHT));

	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		g.drawImage(image, 0, 0, null);

		if (drawCursorAt != null) {
			g.setColor(Color.white);
			g.drawRect(drawCursorAt.x, drawCursorAt.y, Consts.SIZEFACTOR,
					Consts.SIZEFACTOR);
		}

	}

	public BufferedImage getImage() {
		return image;
	}

	public void clear(Color color) {
		Graphics g = image.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, Consts.WIDTH, Consts.HEIGHT);
	}

	public void raster(Color firstColor, Color secondColor) {
		Graphics g = image.getGraphics();
		int shift = 0;

		for (int y = 0; y < Consts.HEIGHT; y = y + Consts.SIZEFACTOR) {
			for (int x = 0 + shift; x < Consts.WIDTH; x = x + Consts.SIZEFACTOR
					* 2) {
				g.setColor(firstColor);
				g.fillRect(x, y, Consts.SIZEFACTOR, Consts.SIZEFACTOR);
			}
			if (shift == 0) {
				shift = Consts.SIZEFACTOR;
			} else {
				shift = 0;
			}
		}
	}

	public void handleMouseEvent(MouseEvent e) {

		final int x = e.getX();
		final int y = e.getY();

		// only display a hand if the cursor is over the items
		final Rectangle cellBounds = this.getVisibleRect();
		if (cellBounds != null && cellBounds.contains(x, y)) {

			int pixelPosX = e.getX() - (e.getX() % Consts.SIZEFACTOR);
			int pixelPosY = e.getY() - (e.getY() % Consts.SIZEFACTOR);

			setCursor(blankCursor);
			drawCursorAt = new Point(pixelPosX, pixelPosY);
		} else {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		repaint();
	}

}
