package ui.panels;

import interfaces.Resettable;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import model.Model;

public class MainPanel extends Panel implements Resettable {
  ActionPanel actionPanel;
  ControlsPanel controlsPanel;
  
  public MainPanel(Model model) {
      actionPanel = new ActionPanel(model);
      controlsPanel = new ControlsPanel(model);
      setLayout(new GridLayout(2,1));
      actionPanel.setBackground(Color.GREEN);
           
      add(controlsPanel);
      add(actionPanel);
      
      
  }

  @Override
  public void resetComponents() {    
    controlsPanel.resetComponents();
    actionPanel.resetComponents();
    // TODO Auto-generated method stub

  }

}
