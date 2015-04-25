package shapes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
  
  int x;
  int y;  
  Color borderColor;
  
  public Shape(int x, int y, Color borderColor) {
    this.x = x;
    this.y = y;
    this.borderColor = borderColor;
  }
  
  public abstract void draw(Graphics g);
  public abstract boolean containsLocation(int x, int y);
  
  public void setBorderColor(Color borderColor) {
    this.borderColor = borderColor;
  }
  
  public Color getBorderColor() {
    return borderColor;
  }
  
  
  public void setX (int x) {
    this.x = x;
  }
  
  public int getX() {
    return x;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public int getY() {
    return y;
  }
 
}
