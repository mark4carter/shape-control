package ui.panels;

import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;

public class ButtonPanel extends Panel {
    private Button btnClear;
    
    public ButtonPanel(final Model model) {
      
      class BtnResetter implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            model.resetComponents();
            model.repaint();
        }
      }
      
        BtnResetter btnResetter = new BtnResetter();        
        btnClear = new Button("Clear");        
        btnClear.addActionListener(btnResetter);
        add(btnClear);
    }
    

}
