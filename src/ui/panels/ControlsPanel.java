package ui.panels;

import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import interfaces.Resettable;
import model.Model;
import ui.panels.ButtonPanel;

public class ControlsPanel extends Panel implements Resettable{
  private ButtonPanel btnPanel;
  private ChoicePanel choicePanel;
  
  static class ColorPanel extends Panel {
    private Choice borderChoice;
    private Choice fillChoice;
    Model model;
    
    public ColorPanel (final Model model) {
      borderChoice = new Choice();
      fillChoice = new Choice();
      
      for(String msg1 : Model.colorChoices) {
        borderChoice.add(msg1);
      }
      for(String msg : Model.colorChoices) {
        fillChoice.add(msg);
      }
      
      borderChoice.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            model.setColorBorder(borderChoice.getSelectedItem());
            model.setBorderKey(borderChoice.getSelectedItem());
          }
      });
      
      fillChoice.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            model.setColorFill(fillChoice.getSelectedItem());
            model.setFillKey(fillChoice.getSelectedItem());
        }
      });
      
     this.add(new Label("Border Color:"));
     this.add(borderChoice);
     this.add(new Label("Fill Color:"));
     this.add(fillChoice);    
    }    
  }
  
  private ColorPanel colorPanel;
  
  public ControlsPanel (final Model model) {
    
    colorPanel = new ColorPanel(model);
    add(colorPanel);
    choicePanel = new ChoicePanel(model);
    add(choicePanel);
    btnPanel = new ButtonPanel(model);
    add(btnPanel);  
    
    
  }
  
  public void resetComponents() {
    colorPanel.borderChoice.select(0);
    colorPanel.fillChoice.select(0);
    choicePanel.resetComponents();
  }
  
}
