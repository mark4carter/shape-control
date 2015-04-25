package shapes;

import interfaces.CompareableShape;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape implements CompareableShape{
  private Color fillColor;
  private int width, height;
  private boolean fill;
  
  public Rectangle(int x, int y, int width, int height, Color borderColor, Color fillColor, boolean fill) {
    super(x, y, borderColor);
    this.width = width;
    this.height = height;
    this.fillColor = fillColor;
    this.fill = fill;
  }
  
  public void draw(Graphics g) {
    //Be nice.  Save the state of the object
    Color oldColor = g.getColor();
    if (isFill()) {
      g.setColor(getFillColor());
      g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    g.setColor(getBorderColor());
    g.drawRect(getX(), getY(), getWidth(), getHeight());
    g.setColor(oldColor);
  }
  
  public boolean containsLocation(int x, int y) {
    if (getX() <= x && getY() <= y && getX() + getWidth() >= x && getY() + getHeight() >= y) {
      return true;
    }
    return false;
  }
  
  public Color getFillColor() {
    return fillColor;
  }
  
  public void setFillColor(Color fillColor) {
    this.fillColor = fillColor;
  }
  
  public int getWidth() {
    return width;
  }
  
  public void setWidth(int width) {
    this.width = width;
  }
  
  public void setHeight (int height) {
    this.height = height;
  }
  
  public int getHeight() {
    return height;
  }
  
  public void setFill(boolean fill) {
    this.fill = fill;
  }
  
  public boolean isFill() {
    return fill;
  }
  
  public int getArea() {
    int area = height * width;
    return area;
  }
  
  
  
  public String toString() {
    return "Rectangle: \n\tx = " + getX() + "\n\ty = " + getY() +
        "\n\tw = " + getWidth() + "\n\th = " + getHeight();
  }
}
