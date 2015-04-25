package ui.panels;

import java.awt.Choice;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import model.Model;

import interfaces.Resettable;

public class ChoicePanel extends Panel implements Resettable {
  
  Model model;
  private Choice shpChoice;
  
  public ChoicePanel(final Model model) {
    shpChoice = new Choice();
    for(String msg : Model.shapeChoices) {
      shpChoice.add(msg);
    }
    shpChoice.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
          model.setCurrentShapeType(shpChoice.getSelectedItem());
      }
    });
    
    this.add(new Label("Shape :"));
    this.add(shpChoice);
  }

  @Override
  public void resetComponents() {
    shpChoice.select(0);
  }

}
