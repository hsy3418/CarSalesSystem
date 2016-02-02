/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.main;


import fit5042.ass1.gui.SearchCarGui;
import fit5042.ass1.gui.SearchCarGuiImpl;
import fit5042.ass1.carsCatelogue.CarsCatelogue;
import fit5042.ass1.entities.Car;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Heaven
 */
public class SearchCar implements ActionListener, ListSelectionListener {
    private SearchCarGui gui;
    
    
    @EJB
    private static CarsCatelogue carsCatelogue;
    
    public SearchCar() {
        
    }

    public void initView() {
        this.gui = new SearchCarGuiImpl(this, this) {};
        
    }

    public void setCombox() throws Exception{
        
        List<Car> cars = carsCatelogue.findAll();
         System.out.println("p");
    
            gui.displayModelNum(cars);
            gui.displayModelName(cars);
            gui.displayMake(cars);
            gui.displayType();
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == gui.getSearchButton()) {
            this.searchCar();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if ((event.getSource() == this.gui.getCarTable().getSelectionModel())
            && (! event.getValueIsAdjusting()))
        {
            try
            {
                if (this.gui.isCarSelected()) {
                    int modelNum = this.gui.getSelectedRowModelNum();  
                    Car car = this.carsCatelogue.find(modelNum);
                    this.gui.displaySelectedCarDetails(car);
                }               
            }
            catch (Exception e)
            {
                gui.displayMessageInDialog(e.getMessage());
            }
        }
    }
    
    public void searchCar(){
    
//        int modelNumSelection= this.gui.getModelNumComboBox().getSelectedIndex();
//        int modelNameSelection = this.gui.getModelNameComboBox().getSelectedIndex();
//        int makeSelection = this.gui.getMakeComboBox().getSelectedIndex();
//        int typeSelection = this.gui.getTypeComboBox().getSelectedIndex();
        
        int modelNum = gui.getSelectedModelNum();
        String modelName = gui.getSelectedModelName();
        String make = gui.getSelectedMake();
        String type = gui.getSelectedType();
        
        if (modelNum != - 1){
               this.searchCarByModelNum(modelNum);
        }else if(modelName !=null){
              if(make!=null&&type!=null){
                   this.searchCarByNameAndMakeAndType(modelName,make,type);
              }else if(make==null&&type!=null){
                  this.searchCarByNameAndType(modelName, type);
              }else if(make!=null&&type==null){
                  this.searchCarByNameAndMake(modelName, make);
              }else if(make==null&&type==null){
                  this.searchCarByName(modelName);
              }
        }else if(modelName == null){
            if(make!=null&&type!=null){
                this.searchCarByMakeAndType(make,type);
            }else if(make==null&&type!=null){
                this.searchCarByType(type);
            }else if(make!=null&&type==null){
                this.searchCarByMake(make);
            }else if(make==null&&type==null){
                this.searchAllCar();
            }
        }else{
             this.gui.displayMessageInDialog("Please search by an option");
        }
            
       

    }
                
                
public void searchAllCar(){
       try {
            
            List<Car> cars = carsCatelogue.findAll();
            
            if (cars != null && !cars.isEmpty()) {
                this.gui.displayCarDetails(cars);
            } else {
                this.gui.displayMessageInDialog("No matched cars found");
                this.gui.clearCarTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search property by ID: " + ex.getMessage());
           this.gui.clearCarTable();
        } finally {
            this.gui.clearInput();
        }
    }
    
    
    public void searchCarByModelNum(int modelNum){
        try {
            
            Car car = carsCatelogue.find(modelNum);
            
            if (car!= null) {
                this.gui.displayCarDetails(car);
            } else {
                this.gui.displayMessageInDialog("No matched cars found");
                this.gui.clearCarTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search property by ID: " + ex.getMessage());
            this.gui.clearCarTable();
        } finally {
            this.gui.clearInput();
        }
    
    }
    
    public void searchCarByNameAndMakeAndType(String name,String make,String type){
        try {
            
            List<Car> cars = carsCatelogue.findByNameAndMakeAndType(name, make, type);
            
            if (cars != null && !cars.isEmpty()) {
                this.gui.displayCarDetails(cars);
            } else {
                this.gui.displayMessageInDialog("No matched cars found");
                this.gui.clearCarTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search property by ID: " + ex.getMessage());
           this.gui.clearCarTable();
        } finally {
            this.gui.clearInput();
        }
    
    }
    
    public void searchCarByNameAndMake(String name,String make){
        try {
            
            List<Car> cars = carsCatelogue.findByNameAndMake(name, make);
            
            if (cars != null && !cars.isEmpty()) {
                this.gui.displayCarDetails(cars);
            } else {
                this.gui.displayMessageInDialog("No matched cars found");
                this.gui.clearCarTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search property by ID: " + ex.getMessage());
           this.gui.clearCarTable();
        } finally {
            this.gui.clearInput();
        }
    
    }
    
    public void searchCarByNameAndType(String name,String type){
        try {
            
            List<Car> cars = carsCatelogue.findByNameAndType(name, type);
            
            if (cars != null && !cars.isEmpty()) {
                this.gui.displayCarDetails(cars);
            } else {
                this.gui.displayMessageInDialog("No matched cars found");
                this.gui.clearCarTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search property by ID: " + ex.getMessage());
           this.gui.clearCarTable();
        } finally {
            this.gui.clearInput();
        }
    }
    
    public void searchCarByMakeAndType(String make,String type){
        try {
            
            List<Car> cars = carsCatelogue.findByMakeAndType(make, type);
            
            if (cars != null && !cars.isEmpty()) {
                this.gui.displayCarDetails(cars);
            } else {
                this.gui.displayMessageInDialog("No matched cars found");
                this.gui.clearCarTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search property by ID: " + ex.getMessage());
           this.gui.clearCarTable();
        } finally {
            this.gui.clearInput();
        }
    }
    
    public void searchCarByMake(String make){
        try {
            
            List<Car> cars = carsCatelogue.findByMake(make);
            
            if (cars != null && !cars.isEmpty()) {
                this.gui.displayCarDetails(cars);
            } else {
                this.gui.displayMessageInDialog("No matched cars found");
                this.gui.clearCarTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search property by ID: " + ex.getMessage());
           this.gui.clearCarTable();
        } finally {
            this.gui.clearInput();
        }
    
    }
    
    public void searchCarByType(String type){
        try {
            
            List<Car> cars = carsCatelogue.findByType(type);
            
            if (cars != null && !cars.isEmpty()) {
                this.gui.displayCarDetails(cars);
            } else {
                this.gui.displayMessageInDialog("No matched cars found");
                this.gui.clearCarTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search property by ID: " + ex.getMessage());
           this.gui.clearCarTable();
        } finally {
            this.gui.clearInput();
        }
    
    }
    
    public void searchCarByName(String modelName){
        try {
            
            List<Car> cars = carsCatelogue.findByModelName(modelName);
            
            if (cars != null && !cars.isEmpty()) {
                this.gui.displayCarDetails(cars);
            } else {
                this.gui.displayMessageInDialog("No matched cars found");
                this.gui.clearCarTable();
            }  
        } catch (Exception ex) {
            this.gui.displayMessageInDialog("Failed to search property by ID: " + ex.getMessage());
           this.gui.clearCarTable();
        } finally {
            this.gui.clearInput();
        }
    }
    
    
    public static void main(String[] args) {
        try {
            SearchCar s = new SearchCar();
            //JDK 1.7
//            SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    agency.initView();
//                }
//            });
            System.out.println("1");
            s.initView();
            System.out.println("1");
            s.setCombox();
            System.out.println("2");

        } catch (Exception ex) {
            System.out.println("Failed to run application: " + ex.getMessage());
        }
    }
    
    
    
}
