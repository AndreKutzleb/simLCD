package notdecided;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class RoundButton extends JButton {
  /**
	 * 
	 */
	private static final long serialVersionUID = 8794655202557457140L;
public RoundButton(String label, int diameter) {
    super(label);
    Dimension size = getPreferredSize();
    size.width = diameter;
    size.height = diameter;
    size.width = size.height = Math.max(size.width,size.height);
	this.setPreferredSize(new Dimension(diameter,diameter));		
	this.setMinimumSize(new Dimension(diameter,diameter));

    setContentAreaFilled(false);
  }

  protected void paintComponent(Graphics g) {
    if (getModel().isArmed()) {
      g.setColor(Color.orange);
    } else {
      g.setColor(Color.yellow);
    }
    g.fillOval(0, 0, getSize().width-1,getSize().height-1);

    super.paintComponent(g);
  }

  protected void paintBorder(Graphics g) {
    g.setColor(getForeground());
    g.drawOval(0, 0, getSize().width-1,     getSize().height-1);
  }

  Shape shape;
  public boolean contains(int x, int y) {
    if (shape == null || 
      !shape.getBounds().equals(getBounds())) {
      shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
    }
    return shape.contains(x, y);
  }

}