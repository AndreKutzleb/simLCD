package LCD;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import notdecided.Consts;
import Events.ScreenMetadata;
import Interfaces.ScreenMetadataListener;

public abstract class AbstractDrawPanel extends JPanel implements MouseMotionListener, MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8530164157251958719L;
	
	protected BufferedImage image;
	protected Point drawCursorAt = null;
	protected Color backgroundColor;
	protected Color penColor;
	protected LCDModel model;
	protected final int sizeFactor;

	protected final int drawWidth;
	protected final int drawHeight;
	protected final int borderThickness = 2;
	
	BufferedImage cursorImg = new BufferedImage(16, 16,
			BufferedImage.TYPE_INT_ARGB);

	Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
			cursorImg, new Point(0, 0), "blank cursor");

	
	private List<ScreenMetadataListener> screenMetadataListener = new CopyOnWriteArrayList<>();

	
	public AbstractDrawPanel(LCDModel lcd, Color initialColor, Color penColor,
			int sizeFactor) {
		super();
		
		if (sizeFactor < 1 || sizeFactor > 100) {
			throw new IllegalArgumentException("sizeFactor must be 1..100");
		}
		this.model = lcd;
		this.sizeFactor = sizeFactor;
		this.drawWidth = lcd.getWidth() * sizeFactor;
		this.drawHeight = lcd.getHeight() * sizeFactor;
		this.penColor = penColor;

		setBorder(new LineBorder(Color.black, 2));
		this.image = new BufferedImage(this.drawWidth, this.drawHeight,
				ColorSpace.TYPE_RGB);
		
		Dimension fixedSize = new Dimension(this.drawWidth
				+ (borderThickness * 2), this.drawHeight
				+ (borderThickness * 2));
		
		this.setPreferredSize(fixedSize);
		this.setMinimumSize(fixedSize);
		this.setMaximumSize(fixedSize);
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);


	}
	
	
	
	@Override
	public void mouseMoved(MouseEvent e) {

		final int x = e.getX();
		final int y = e.getY();
		
		// hide cursor when over screen
		final Rectangle cellBounds = this.getVisibleRect();
		
		if (cellBounds == null) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		// cut away border
		int screenX = cellBounds.x + this.borderThickness;
		int screenY = cellBounds.y + this.borderThickness;
		
		int screenWidth = cellBounds.width - (2 * this.borderThickness);
		int screenHeight = cellBounds.height - (2 * this.borderThickness);
		
		final Rectangle screenBounds = new Rectangle(screenX, screenY, screenWidth, screenHeight);
		
		if (screenBounds.contains(x, y)) {

				int pixelPosX = e.getX() - (e.getX() % this.sizeFactor);
				int pixelPosY = e.getY() - (e.getY() % this.sizeFactor);
				drawCursorAt = new Point(pixelPosX, pixelPosY);
				setCursor(blankCursor);
				
				Point pointOnLCD = new Point(pixelPosX/this.sizeFactor,pixelPosY/this.sizeFactor);
				if (model.getBoundaries().contains(pointOnLCD)) {
					fireScreenMetadataEvent(new ScreenMetadata(pointOnLCD));					
				}
			
		} else {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		repaint();
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// dont care
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	public void clear(Color color) {
		Graphics g = image.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, this.drawWidth, this.drawHeight);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		g.drawImage(image, borderThickness, borderThickness, null);

		if (drawCursorAt != null) {
			g.setColor(Color.blue);
			g.drawRect(drawCursorAt.x + borderThickness, drawCursorAt.y
					+ borderThickness, this.sizeFactor - 1, this.sizeFactor - 1);
		}

	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		drawCursorAt = null;
		fireScreenMetadataEvent(new ScreenMetadata(new Point(0,0)));
		repaint();
		
	}

	public BufferedImage getImage() {
		return image;
	}

	public void addScreenMetadataListener(ScreenMetadataListener listener) {
		this.screenMetadataListener.add(listener);
	}

	public void removeScreenMetadataListener(ScreenMetadataListener listener) {
		this.screenMetadataListener.remove(listener);
	}
	
	private void fireScreenMetadataEvent(ScreenMetadata data) {
		for (ScreenMetadataListener listener : this.screenMetadataListener) {
			listener.screenMetadataChanged(data);
		}
	}
	

}
