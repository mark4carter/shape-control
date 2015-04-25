package ui.applet;

import interfaces.Resettable;
import java.applet.Applet;

import shapes.Shape;
import ui.panels.MainPanel;
import model.Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.*;

import event.ShapeMouseHandler;

public class GUIDemo extends Applet implements Resettable{
  Graphics bufferGraphics;
  Image doubleBuffer;
    MainPanel mainPanel;
    Model model;
  
    public void init(){
        resize(600,400);
        model = new Model(this);
        mainPanel = new MainPanel(model);
        add(mainPanel);
        ShapeMouseHandler mouseHandler = new ShapeMouseHandler(model);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        setBackground(Color.gray);        
    }
    
    public void update(Graphics g) {
      
      //buffer for a smoother program
      if (doubleBuffer == null)
      {
          doubleBuffer = createImage(this.getSize().width, this.getSize().height);
          bufferGraphics = doubleBuffer.getGraphics();
      }
      
      bufferGraphics.setColor(getBackground());
      bufferGraphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
      
      bufferGraphics.setColor(getForeground());
      

      g.drawImage(doubleBuffer, 0, 0, this);
      
      
      //draw shapes
      for (int i = 0; i < model.getDrawnShapes().size(); i++) {      
        if (model.getDrawnShapes().elementAt(i) != null) {
          model.getDrawnShapes().elementAt(i).draw(g);
        }
        //print shape list
        System.out.println(model);
        System.out.println(model.getDrawnShapes().elementAt(i));
        model.printShapeList();
      }
      
      //draw highlight if needed
      if (model.getHighlight() != null) 
        model.getHighlight().draw(g);
      
      //draw remove lines if needed
      if (model.getRmvLines(0) != null) {
        for (int z = 0; z < model.getRmvLines().length; z++) {
          model.getRmvLines(z).draw(g);
        }
      }
      
      //draw color key
      model.getBorderKey().setX(getWidth() - 100);
      model.getBorderKey().setY(getHeight() - 100);
      model.getFillKey().setX(getWidth() - 90);
      model.getFillKey().setY(getHeight() - 90);
      if (!(model.isFill())) {
        model.getFillKey().setFillColor(getBackground());
      }
      model.getBorderKey().draw(g);
      model.getFillKey().draw(g);
      
    }
    
    public void paint(Graphics g) {
      update(g);
    }
    
    public void resetComponents() {
      mainPanel.resetComponents();
    }
}