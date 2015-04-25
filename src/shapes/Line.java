package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
  
  int x2;
  int y2;
  
  public Line(int x, int y, Color borderColor, int x2, int y2) {
    super(x, y, borderColor);
    this.x2 = x2;
    this.y2 = y2;
  }
  
  public void draw(Graphics g) {
    Color oldColor = g.getColor();
    g.setColor(borderColor);
    g.drawLine(x, y, x2, y2);
    g.setColor(oldColor);
  }
  
  //creates a box around the line as the selectable area
  public boolean containsLocation(int x, int y) {
    int tempX, tempX2, tempY, tempY2; 
    if (getX() < getX2()) {
      tempX = getX();
      tempX2 = getX2();
    }
    else {
      tempX = getX2();
      tempX2 = getX();
    }
    if (getY() < getY2()) {
      tempY = getY();
      tempY2 = getY2();
    }
    else {
      tempY = getY2();
      tempY2 = getY();
    }
      
    if (tempX <= x && tempY <= y && tempX2 >= x && tempY2 >= y) {
      return true;
    }
    return false;
  }
  
  public int getX2() {
    return x2;
  }
  
  public void setX2 (int x2) {
    this.x2 = x2;
  }
  
  public int getY2() {
    return y2;
  }
  
  public void setY2 (int y2) {
    this.y2 = y2;
  }
  
  public int getWidth() {
    if (x > x2) 
     return x - x2;
    else
     return x2 - x;   
  }
  
  public int getHeight() {
    if (y > y2)
      return y - y2;
    else
      return y2 - y;
  }
  
  public int getMinX() {
    if (x < x2) 
      return x;
    else
      return x2;
  }
  
  public int getMinY() {
    if (y < y2)
      return y;
    else
      return y2;
  }
  
  public int getMaxX() {
    if (x < x2)
      return x2;
    else
      return x;
  }
  
  public int getMaxY() {
    if (y < y2)
      return y2;
    else
      return y;
  }
  
  public String toString() {
    return "Line: \n\tx = " + getX() + "\n\ty = " + getY() + "\n\tx2 = " + getX2()
        + "\n\ty2 = " + getY2();
  }
    
 
  

}
