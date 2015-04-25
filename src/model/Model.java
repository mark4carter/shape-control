package model;

import java.awt.Color;
import java.util.*;

import shapes.Line;
import shapes.Oval;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Triangle;
import ui.applet.GUIDemo;

import java.awt.Container;

import interfaces.CompareableShape;
import interfaces.Resettable;

public class Model implements Resettable {
  private Container container;
  
  public enum Action {DRAW, MOVE, RESIZE, REMOVE, CHANGE, FILL}
  public enum ShapeChoices { LINE, RECTANGLE, OVAL, TRIANGLE }
  
  public final static String BLACK = "Black";
  public final static String RED = "Red";
  public final static String BLUE = "Blue";
  public final static String GREEN = "Green";
  public final static String YELLOW = "Yellow";
  public final static String ORANGE = "Orange";
  public final static String CYAN = "Cyan";
  public final static String GRAY = "Gray";
  
  private Action action = Action.DRAW;
  private ShapeChoices currentShapeType = ShapeChoices.RECTANGLE;
  private String colorBorder = ORANGE;
  private String colorFill = ORANGE;
  
  private boolean fill = false;
  
  private Shape currentShape;
  private Shape highlight;
  
  private Line[] rmvLines = new Line[4];
  
  public final static String[] shapeChoices = {"Rectangle", "Oval", "Triangle", "Line"};
  public final static String[] colorChoices = {ORANGE, BLACK, RED, BLUE, GREEN, YELLOW, CYAN, GRAY};
  public Vector <Shape> drawnShapes = new Vector <Shape>(1);
  
  Color currentColorBorder = Color.orange;
  Color currentColorFill = Color.orange;
  

  private Rectangle borderKey = new Rectangle(0,0,50,50, Color.black, currentColorBorder, true);
  private Rectangle fillKey =  new Rectangle (0,0,30,30, currentColorBorder, currentColorFill, true);
  
  
  
  //Builds an empty shape 
  public Shape createShape() {
    if(currentShapeType == ShapeChoices.RECTANGLE) {
      currentShape = new Rectangle(0,0,0,0, currentColorBorder, currentColorFill, fill);
    }
    if(currentShapeType == ShapeChoices.OVAL) {
      currentShape = new Oval(0,0,0,0, currentColorBorder, currentColorFill, fill);
    }
    if(currentShapeType == ShapeChoices.TRIANGLE) {
      currentShape = new Triangle(0,0,0,0, currentColorBorder, currentColorFill, fill);
    }
    if(currentShapeType == ShapeChoices.LINE) {
      currentShape = new Line(0,0, currentColorBorder, 0,0);
    }    
    drawnShapes.addElement(getCurrentShape());
    return drawnShapes.lastElement();
  }
  
  public Shape getArrayShape(int i) {
    return drawnShapes.elementAt(i);
  }
  
  public Shape getCurrentShape() {
    return currentShape;
  }
  
  public void setCurrentShape(Shape s) {
    currentShape = s;
  }
  
  public Shape getHighlight() {
    return highlight;
  }
  
  public Vector <Shape> getDrawnShapes() {
    return drawnShapes;
  }
  
  public ShapeChoices getCurrentShapeType() {
    return currentShapeType;
  }
  
  public void setCurrentShapeType(String shapeType) {
    if (shapeType.equals("Line")) {
      currentShapeType = ShapeChoices.LINE;
    }
    else if (shapeType.equals("Oval")) {
      currentShapeType = ShapeChoices.OVAL;
    }
    else if (shapeType.equals("Triangle")) {
      currentShapeType = ShapeChoices.TRIANGLE;
    }
    else {
      currentShapeType = ShapeChoices.RECTANGLE;
    }
  }
  
  public Model (Container container) {
    this.container = container;
  }
  
  public void repaint() {
    container.repaint();
  }

  @Override
  public void resetComponents() {   //Resets all variables to default variables
    action = Action.DRAW;
    currentShape = null;
    highlight = null;
    currentShapeType = ShapeChoices.RECTANGLE;
    fill = false;
    setColorFill(ORANGE);
    setColorBorder(ORANGE);
      drawnShapes.clear();
    borderKey.setFillColor(currentColorBorder);
    fillKey.setBorderColor(currentColorBorder);
    fillKey.setFillColor(currentColorFill);
    if(container instanceof Resettable) {
      ((Resettable)container).resetComponents();
    }
    // TODO Auto-generated method stub

  }
  
  public Action getAction() {
    return action;
  }
  
  public void setAction(Action action) {
    this.action = action;
  }
  
  public boolean isFill() {
    return fill;
  }
  
  public void setFill(boolean fill) {
    this.fill = fill;
    if (this.fill == true) {
      fillKey.setFillColor(currentColorFill);
    }
    repaint();
  }
  
  public void setColorBorder(String colorB) {
    this.colorBorder = colorB;
    if (colorBorder == ORANGE) {
      setCurrentColorBorder(Color.orange);
    }
    if (colorBorder == BLACK) {
      setCurrentColorBorder(Color.black);
    }
    if (colorBorder == RED) {
      setCurrentColorBorder(Color.red);
    }
    if (colorBorder == BLUE) {
      setCurrentColorBorder(Color.blue);
    }
    if (colorBorder == GREEN) {
      setCurrentColorBorder(Color.green);
    }
    if (colorBorder == YELLOW) {
      setCurrentColorBorder(Color.yellow);
    }
    if (colorBorder == CYAN) {
      setCurrentColorBorder(Color.cyan);
    }
    if (colorBorder == GRAY) {
      setCurrentColorBorder(Color.gray);
    }
  }
  
  public String getColorBorder() {
    return colorBorder;
  }
  
  public void setColorFill(String colorF) {
    this.colorFill = colorF;
    if (colorFill == ORANGE) {
      setCurrentColorFill(Color.orange);
    }
    if (colorFill == BLACK) {
      setCurrentColorFill(Color.black);
    }
    if (colorFill == RED) {
      setCurrentColorFill(Color.red);
    }
    if (colorFill == BLUE) {
      setCurrentColorFill(Color.blue);
    }
    if (colorFill == GREEN) {
      setCurrentColorFill(Color.green);
    }
    if (colorFill == YELLOW) {
      setCurrentColorFill(Color.yellow);
    }
    if (colorFill == CYAN) {
      setCurrentColorFill(Color.cyan);
    }
    if (colorFill == GRAY) {
      setCurrentColorFill(Color.gray);
    }
  }
  
  public String getColorFill() {
    return colorFill;
  }
  
  public void setCurrentColorBorder (Color color) {
    currentColorBorder = color;
  }
  
  public Color getCurrentColorBorder() {
    return currentColorBorder;
  }
  
  public void setCurrentColorFill (Color color) {
    currentColorFill = color;
  }
  
  public Color getCurrentColorFill(){
    return currentColorFill;
  }
  
  //Everytime model is modified a list of shapes and attributes are printed
  public void printShapeList() {
    System.out.println("Shape List:");
    for (int i = 0; i < drawnShapes.size(); i++) {
      if (drawnShapes.elementAt(i) instanceof Line)
        System.out.println("\tElement " + i + " is a line");
      else if (drawnShapes.elementAt(i) instanceof Oval)
        System.out.println("\tElement " + i + " is a oval");
      else if (drawnShapes.elementAt(i) instanceof Triangle)
        System.out.println("\tElement " + i + " is a triangle");
      else if (drawnShapes.elementAt(i) instanceof Rectangle)
        System.out.println("\tElement " + i + " is a rectangle");
    }
  }
  
  //what to do when a shape is clicked
  public Shape clickedShape(int clickX, int clickY) {    
    for (int i = drawnShapes.size() - 1; i >= 0; i--) {    //Search array
      if (drawnShapes.elementAt(i) instanceof Rectangle) {
        if (((Rectangle)drawnShapes.elementAt(i)).containsLocation(clickX, clickY)) {
          currentShape = drawnShapes.elementAt(i);
            
            //Creates a border around selected shape
          highlight = new Rectangle(currentShape.getX(),currentShape.getY(),((Rectangle) currentShape).getWidth(),
              ((Rectangle) currentShape).getHeight(), currentShape.getBorderColor().darker(), 
              ((Rectangle) currentShape).getFillColor().brighter(), false);
          if (currentShape.getBorderColor() == Color.black) 
            highlight.setBorderColor(Color.gray.brighter());
          if (currentShape.getBorderColor() == Color.blue) 
            highlight.setBorderColor(Color.cyan.brighter());
          if (currentShape.getBorderColor() == Color.red) 
            highlight.setBorderColor(Color.magenta.brighter());
          if (currentShape.getBorderColor() == Color.gray) 
            highlight.setBorderColor(Color.black);
          
          //In the case of removing an object, create a border and an X
          if (getAction()== Action.REMOVE) {
            highlight.setBorderColor(Color.magenta);
            rmvLines[0] = new Line (currentShape.getX(), currentShape.getY(), Color.magenta, currentShape.getX() + ((Rectangle)currentShape).getWidth(),
                currentShape.getY() + ((Rectangle)currentShape).getHeight());
            rmvLines[1] = new Line (currentShape.getX() + ((Rectangle)currentShape).getWidth(), currentShape.getY(), Color.magenta,
                currentShape.getX(), currentShape.getY() + ((Rectangle)currentShape).getHeight());
            rmvLines[2] = new Line (currentShape.getX(), currentShape.getY() + 1, Color.magenta, currentShape.getX() + ((Rectangle)currentShape).getWidth() - 1,
                currentShape.getY() + ((Rectangle)currentShape).getHeight());
            rmvLines[3] = new Line (currentShape.getX() - 1+ ((Rectangle)currentShape).getWidth(), currentShape.getY(), Color.magenta,
                currentShape.getX(), currentShape.getY() + ((Rectangle)currentShape).getHeight() - 1);
          }
                    
          return drawnShapes.elementAt(i);
        }
        }
      //same but with lines
      if (drawnShapes.elementAt(i) instanceof Line) {
        if (((Line)drawnShapes.elementAt(i)).containsLocation(clickX, clickY)){
          currentShape = drawnShapes.elementAt(i);          
          highlight = new Rectangle(((Line) currentShape).getMinX(),((Line) currentShape).getMinY(),((Line) currentShape).getWidth(),
              ((Line) currentShape).getHeight(), currentShape.getBorderColor().darker(), 
              Color.black, false);          
          if (getAction() == Action.REMOVE) {
            highlight.setBorderColor(Color.magenta);
          }          
          return drawnShapes.elementAt(i);
        }
      }
      }
    return null;    
  }
  
  public void removeClickedShape(int clickX, int clickY, Shape shape) {
    for (int i = drawnShapes.size() - 1; i >= 0; i--) {
      if (drawnShapes.elementAt(i) instanceof Rectangle) {
        if(shape instanceof Rectangle) {
          if (((Rectangle)drawnShapes.elementAt(i)).containsLocation(clickX, clickY)) {
            drawnShapes.removeElementAt(i);
            return;          
          }
        }
      }
      if (drawnShapes.elementAt(i) instanceof Line) {
        if (shape instanceof Line) {
          if (((Line)drawnShapes.elementAt(i)).containsLocation(clickX, clickY)) {
            drawnShapes.removeElementAt(i);
          }
        }
      }
    }
  }
  
  public void clearHighlight() {
    highlight = null;
  }
  
  public void clearRmvLines() {
    for (int i = 0; i < rmvLines.length; i++) {
      rmvLines[i] = null;
    }
  }
  
  public void setFillKey(String colorF) {
    this.colorFill = colorF;
    if (colorFill == ORANGE) {
      fillKey.setFillColor(Color.orange);
    }
    if (colorFill == BLACK) {
      fillKey.setFillColor(Color.black);
      
    }
    if (colorFill == RED) {
      fillKey.setFillColor(Color.red);

    }
    if (colorFill == BLUE) {
      fillKey.setFillColor(Color.blue);
    }
    if (colorFill == GREEN) {
      fillKey.setFillColor(Color.green);
    }
    if (colorFill == YELLOW) {
      fillKey.setFillColor(Color.yellow);

    }
    if (colorFill == CYAN) {
      fillKey.setFillColor(Color.cyan);
    }
    if (colorFill == GRAY) {
      fillKey.setFillColor(Color.gray);
    }
    repaint();
  }
  
  public void setBorderKey(String colorB) {
    this.colorBorder = colorB;
    if (colorBorder == ORANGE) {
      borderKey.setFillColor(Color.orange);
      fillKey.setBorderColor(Color.orange);
    }
    if (colorBorder == BLACK) {
      borderKey.setFillColor(Color.black);
      fillKey.setBorderColor(Color.black);
    }
    if (colorBorder == RED) {
      borderKey.setFillColor(Color.red);
      fillKey.setBorderColor(Color.red);
    }
    if (colorBorder == BLUE) {
      borderKey.setFillColor(Color.blue);
      fillKey.setBorderColor(Color.blue);
    }
    if (colorBorder == GREEN) {
      borderKey.setFillColor(Color.green);
      fillKey.setBorderColor(Color.green);
    }
    if (colorBorder == YELLOW) {
      borderKey.setFillColor(Color.yellow);
      fillKey.setBorderColor(Color.yellow);
    }
    if (colorBorder == CYAN) {
      borderKey.setFillColor(Color.cyan);
      fillKey.setBorderColor(Color.cyan);
    }
    if (colorBorder == GRAY) {
      borderKey.setFillColor(Color.gray);
      fillKey.setBorderColor(Color.gray);
    }
    repaint();
  }
  
  public Rectangle getBorderKey() {
    return borderKey;
  }
  
  public Rectangle getFillKey() {
    return fillKey;
  }
  
  public Line[] getRmvLines(){
    return rmvLines;
  }
  
  public Line getRmvLines(int loc) {
    return rmvLines[loc];
  }
  
  public void drawRmvLines() {
  }
  
  
  public String toString() {
    return "Model\n\tAction: " + action + "\n\tFill: " + fill + "\n\tShapeType: " + getCurrentShapeType()
        + "\n\tColor: " + colorBorder;
  }

}
