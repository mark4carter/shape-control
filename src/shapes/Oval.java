package shapes;



import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Rectangle {
  /**
   * Constructor.  Just passes the params to the Rectangle constructor.
   */
  
  public Oval(int x, int y, int w, int h, Color lineColor, Color fillColor, boolean fill) {
    super(x, y, w, h, lineColor, fillColor, fill);
  }
  
  public void draw(Graphics g) {
    Color oldColor = g.getColor();
    if (isFill()) {
      g.setColor(getFillColor());
      g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
    g.setColor(getBorderColor());
    g.drawOval(getX(), getY(), getWidth(), getHeight());
    g.setColor(oldColor);
  }
  
  public int getArea() {
    int area = (int) (Math.PI * (getWidth())/2 * (getHeight())/2);
    return area;
  }
  
  public String toString() {
    return "Oval: \n\tx = " + getX() + "\n\ty = " + getY() + "\n\tw = " + getWidth() + "\n\th = " + getHeight();
  }

}
