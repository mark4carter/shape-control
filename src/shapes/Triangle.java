package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends Rectangle {

  public Triangle(int x, int y, int width, int height, Color borderColor, Color fillColor, boolean fill) {
    super(x, y, width, height, borderColor, fillColor, fill);
    // TODO Auto-generated constructor stub
  }
  
  public void draw(Graphics g) {
    Color oldColor = g.getColor();
    int xPoints[] = {x+(getWidth()/2), x, x + getWidth()};
    int yPoints[] = {y, y + getHeight(), y + getHeight()};
    if (isFill()) {
      g.setColor(getFillColor());
      g.fillPolygon(xPoints, yPoints, 3);
    }
    g.setColor(getBorderColor());
    g.drawPolygon(xPoints, yPoints, 3);
    g.setColor(oldColor);
  }
  
  public int getArea() {
    return (getWidth()*getHeight())/2;
  }
  

}
