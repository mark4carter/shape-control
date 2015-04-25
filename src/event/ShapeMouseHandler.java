package event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Model;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Line;

public class ShapeMouseHandler extends MouseAdapter {
  private Model model;
  private int startX;
  private int startY;
  private Shape shape;
  private int diffX;
  private int diffY;
  private int diffMinX;
  private int diffMinY;
  private int tempHeight;
  private int tempWidth;
  
  
  //Constructor
  
  public ShapeMouseHandler(Model model) {
    this.model = model;
  }
  
  public void mousePressed(MouseEvent e) {
    if (model.getAction() == Model.Action.DRAW) {    //Draw Shape
      startX = e.getX();
      startY = e.getY();
      shape = model.createShape();
      if (shape != null) {
        shape.setX(e.getX());
        shape.setY(e.getY());
      if (shape instanceof Rectangle) {
        ((Rectangle) shape).setWidth(50);
        ((Rectangle) shape).setHeight(50);
      }
      if (shape instanceof Line) {
        ((Line) shape).setX2(shape.getX() + 50);
        ((Line) shape).setY2(shape.getY() + 50);
      }
      }
    }
    
    //Select Shape to move
    if (model.getAction() == Model.Action.MOVE) {
      shape = null;
      model.setCurrentShape(null);
      shape = model.clickedShape(e.getX(), e.getY());
      if (shape != null) {
        diffX = e.getX() - shape.getX();      //variables so shape moves in relation to where shape was clicked
        diffY = e.getY() - shape.getY();
        if (shape instanceof Line) {
          diffMinX = e.getX() - ((Line) shape).getMinX();
          diffMinY = e.getY() - ((Line) shape).getMinY();
          tempWidth = ((Line) shape).getX2() - shape.getX();
          tempHeight = ((Line) shape).getY2() - shape.getY();
        }
      }
    }
    
    //Select shape to resize
    if (model.getAction() == Model.Action.RESIZE) {
      shape = null;
      model.setCurrentShape(null);
      shape = model.clickedShape(e.getX(), e.getY());
      if (shape != null) {
        startX = shape.getX();
        startY = shape.getY();
      }
    }
    
    //Select shape to move
    if (model.getAction() == Model.Action.REMOVE) {
      shape = null;
      model.setCurrentShape(null);
      shape = model.clickedShape(e.getX(), e.getY());              
    }
    
    //Select shape to change
    if (model.getAction() == Model.Action.CHANGE) {
      shape = null;
      model.setCurrentShape(null);
      shape = model.clickedShape(e.getX(), e.getY());      
    }
    model.repaint(); 
  }
  
  public void mouseDragged(MouseEvent e) {
    shape = model.getCurrentShape();
    if (shape != null) {
      
      //draw the size of the shape
      if (model.getAction() == Model.Action.DRAW) {
        if (!(shape instanceof Line)) {
        shape.setX(Math.min(startX, e.getX()));
        shape.setY(Math.min(startY, e.getY()));
      
      if (shape instanceof Rectangle) {
        ((Rectangle) shape).setWidth(Math.abs(startX-e.getX()));
        ((Rectangle) shape).setHeight(Math.abs(startY - e.getY()));
      }}
        if (shape instanceof Line) {
          ((Line) shape).setX2(e.getX());
          ((Line) shape).setY2(e.getY());
        }
      }
      
      //move shape to new location
      if (model.getAction() == Model.Action.MOVE) {
        shape.setX(e.getX() - diffX);
        shape.setY(e.getY() - diffY);
        if (shape instanceof Rectangle) {
        model.getHighlight().setX(e.getX() - diffX);
        model.getHighlight().setY(e.getY() - diffY);
        }
        if (shape instanceof Line) {
          model.getHighlight().setX(e.getX() - diffMinX);
          model.getHighlight().setY(e.getY() - diffMinY);
          ((Line) shape).setX2(shape.getX() + tempWidth);
          ((Line) shape).setY2(shape.getY() + tempHeight);
        }
      }
      
      //drag mouse to resize
      if (model.getAction() == Model.Action.RESIZE) {
        if (!(shape instanceof Line)) {
        shape.setX(Math.min(startX, e.getX()));
        shape.setY(Math.min(startY, e.getY()));
        model.getHighlight().setX(Math.min(startX, e.getX()));  
        model.getHighlight().setY(Math.min(startY, e.getY()));
      
      if (shape instanceof Rectangle) {
        ((Rectangle) shape).setWidth(Math.abs(startX-e.getX()));
        ((Rectangle) shape).setHeight(Math.abs(startY - e.getY()));
        ((Rectangle)model.getHighlight()).setWidth(Math.abs(startX-e.getX()));
        ((Rectangle) model.getHighlight()).setHeight(Math.abs(startY - e.getY()));
      }}
        
        if (shape instanceof Line) {
          ((Line) shape).setX2(e.getX());
          ((Line) shape).setY2(e.getY());
          model.getHighlight().setX(((Line) shape).getMinX());
          model.getHighlight().setY(((Line) shape).getMinY());
          ((Rectangle) model.getHighlight()).setWidth(((Line) shape).getMaxX() - ((Line) shape).getMinX());
          ((Rectangle) model.getHighlight()).setHeight(((Line) shape).getMaxY() - ((Line) shape).getMinY());
        }
      }
      
      //if mouse is on shape still
      if (model.getAction() == Model.Action.CHANGE) {
        if (!(shape.containsLocation(e.getX(), e.getY()))) {
          model.clearHighlight();          
        }
        if (shape.containsLocation(e.getX(), e.getY())) {
          shape = model.clickedShape(e.getX(), e.getY());
        }
      }
      
      //if mouse is on shape still
      if (model.getAction() == Model.Action.REMOVE) {
        if (!(shape.containsLocation(e.getX(), e.getY()))) {
          model.clearRmvLines();     
          model.clearHighlight();
        }
        if (shape.containsLocation(e.getX(), e.getY())) {
          shape = model.clickedShape(e.getX(), e.getY());
        }
      }
    }
    model.repaint();
  }
  
  
  public void mouseReleased(MouseEvent e) {
    if (shape != null) {
      
                //change if released on shape
      if (model.getAction() == Model.Action.CHANGE) {
        if ((shape.containsLocation(e.getX(), e.getY()))) {
            shape.setBorderColor(model.getCurrentColorBorder());
            if (shape instanceof Rectangle) {
              ((Rectangle) shape).setFill(model.isFill());
              ((Rectangle) shape).setFillColor(model.getCurrentColorFill());
            } 
        }
      }
      
              //change if released on shape
      if (model.getAction() == Model.Action.REMOVE) {
        if ((shape.containsLocation(e.getX(), e.getY()))) {
          model.removeClickedShape(e.getX(), e.getY(), shape); 
        }
      }
    }
    
    model.clearHighlight();
    model.clearRmvLines();
    model.repaint();
  }
  

}
