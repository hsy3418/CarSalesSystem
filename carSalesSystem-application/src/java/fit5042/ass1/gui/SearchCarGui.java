/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.gui;

import fit5042.ass1.entities.Car;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author Heaven
 */
public interface SearchCarGui {
    
    /**
     *
     * @return
     */
    public JComboBox getModelNumComboBox();
    
     public JComboBox getModelNameComboBox();
        
      public JComboBox getMakeComboBox();
      
      
       public JComboBox getTypeComboBox();
       
       public void displayCarDetails(Car car);
       
       public void displayCarDetails(List<Car> Cars);
       
       public void clearCarTable();
       
       public void clearInput();
       
       public void clearComboBoxes();
       
       public void displayMessageInDialog(String message);
       
       public void displayModelNum(List<Car> cars);
       
       public void displayModelName(List<Car> cars);
       
       public void displayMake(List<Car> cars);
       
       public void displayType();
       
       public int getSelectedModelNum();
       
       public String getSelectedModelName();
       
       public String getSelectedMake();
       
       public String getSelectedType();
       
       public JButton getSearchButton();
       
       public void displaySelectedCarDetails(Car car);
       
       public JTable getCarTable();
       
       public boolean isCarSelected();
       
        public int getSelectedRowModelNum();
       
}
