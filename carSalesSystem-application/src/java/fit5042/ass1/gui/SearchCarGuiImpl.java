/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.gui;

import fit5042.ass1.entities.Car;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Heaven
 */
public class SearchCarGuiImpl extends JFrame implements SearchCarGui {
     private static final String[] TABLE_COLUMNS = {"Model Number", "Model Name", "image"};
     
     //private final JTabbedPane tabbedPane;
    private final JPanel leftPanel;
    private final JPanel rightPanel;
    private final JPanel serachPanel;
    //private final JPanel wholePanel;
    //private final JPanel personalDetailsPanel;
    private final JPanel displayDetailPanel;
    
    private final JLabel modelNumLabel;
    private final JLabel modelNameLabel;
    private final JLabel makeLabel;
    private final JLabel typeLabel;
    //the label in second panel
    private final JLabel personalDetailsTitle;
    private final JLabel personalDetails;
    
    //the labe in displayDetail Panel
    private final JLabel modelNumDisplayLabel;
    private final JLabel vinDisplayLabel;
    private final JLabel modelNameDisplayLabel;
    private final JLabel makeDisplayLabel;
    private final JLabel typeDisplayLabel;
    private final JLabel thumbnailDisplayLabel;
    private final JLabel descriptionDisplayLabel;
    private final JLabel colourDisplayLabel;
    private final JLabel milesDisplayLabel;
    private final JLabel instockDisplayLabel;
    
            
    private final JComboBox modelNumComboBox;
    private final JComboBox modelNameComboBox;
    private final JComboBox makeComboBox;
    private final JComboBox typeComboBox;
    
    private final JButton searchButton;
    
    private final JTable carTable;
    
    
     public SearchCarGuiImpl(ActionListener actionListener, ListSelectionListener listSelectionListener){
         super("Car sales System");
         //tabbedPane = new JTabbedPane();
        Container container = this.getContentPane();
        
        //the second panel which display the personal details
        //this.personalDetailsPanel= new JPanel();
        this.personalDetailsTitle = new JLabel("This is your personal information");
        this.personalDetails = new JLabel("TBC");
        
        //this.personalDetailsPanel.setLayout(new GridLayout(2,1));
        //personalDetailsPanel.add(personalDetailsTitle);
        //personalDetailsPanel.add(personalDetails);
        
        this.displayDetailPanel = new JPanel();
        BoxLayout layout=new BoxLayout(displayDetailPanel, BoxLayout.Y_AXIS); 
        displayDetailPanel.setLayout(layout);
        this.serachPanel = new JPanel();
        BoxLayout layoutsearch=new BoxLayout(serachPanel, BoxLayout.Y_AXIS); 
        serachPanel.setLayout(layoutsearch);
        //this.wholePanel = new JPanel();
        this.leftPanel = new JPanel();
        this.rightPanel = new JPanel();
        
        this.searchButton = new JButton("Search");
        
        this.modelNumLabel = new JLabel("Model Number:");
        this.modelNameLabel = new JLabel("Model Name:");
        this.makeLabel = new JLabel("Make:");
        this.typeLabel = new JLabel("Type");
    
       
        modelNumDisplayLabel = new JLabel();
        vinDisplayLabel =  new JLabel();
        modelNameDisplayLabel =  new JLabel();
        makeDisplayLabel =  new JLabel();
        typeDisplayLabel=  new JLabel();
        thumbnailDisplayLabel=  new JLabel();
        descriptionDisplayLabel=  new JLabel();
        colourDisplayLabel=  new JLabel();
        milesDisplayLabel=  new JLabel();
        instockDisplayLabel=  new JLabel();
        
        this.carTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0){
        @Override
        public Class<?> getColumnClass(int column) {
            if (getRowCount() > 0) {
               return getValueAt(0, column).getClass();
            }

            return super.getColumnClass(column);
         }
        });
        this.carTable.getSelectionModel().addListSelectionListener(listSelectionListener);       
        this.carTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        carTable.setRowHeight(70);
        TableColumnModel propertyTableColumnModel = this.carTable.getColumnModel();
        propertyTableColumnModel.getColumn(0).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(1).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(2).setPreferredWidth(300);
        
        this.modelNumComboBox = new JComboBox();
        this.modelNameComboBox = new JComboBox();
        this.makeComboBox = new JComboBox();
        this.typeComboBox = new JComboBox();
        
        container.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.leftPanel.setLayout(new GridLayout(5,2));
        this.rightPanel.add(new JScrollPane(this.carTable), BorderLayout.CENTER);
        
        this.searchButton.addActionListener(actionListener);
        
        this.leftPanel.add(modelNumLabel);
        this.leftPanel.add(modelNumComboBox);
        this.leftPanel.add(modelNameLabel);
        this.leftPanel.add(modelNameComboBox);
        this.leftPanel.add(makeLabel);
        this.leftPanel.add(makeComboBox);
        this.leftPanel.add(typeLabel);
        this.leftPanel.add(typeComboBox);
        this.leftPanel.add(searchButton);
        
        this.displayDetailPanel.add(thumbnailDisplayLabel);
        this.displayDetailPanel.add(modelNumDisplayLabel);
        this.displayDetailPanel.add(vinDisplayLabel);
        this.displayDetailPanel.add(modelNameDisplayLabel);
        this.displayDetailPanel.add(makeDisplayLabel);
        this.displayDetailPanel.add(typeDisplayLabel);
        this.displayDetailPanel.add(descriptionDisplayLabel);
        this.displayDetailPanel.add(colourDisplayLabel);
        this.displayDetailPanel.add(milesDisplayLabel);
        this.displayDetailPanel.add(instockDisplayLabel);
        
        this.serachPanel.add(leftPanel);
        this.serachPanel.add(rightPanel);
        
        container.add(serachPanel);
        //container.add(rightPanel,BorderLayout.CENTER);
         container.add(displayDetailPanel);
        
        
        
        //this.wholePanel.add(leftPanel,BorderLayout.WEST);
        //this.wholePanel.add(rightPanel,BorderLayout.EAST);
        
        
        //add two big panels into tabbedPane;
//        tabbedPane.add("Search Cars",wholePanel);
//        tabbedPane.add("Personal Info",personalDetailsPanel);
//        container.add(tabbedPane);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setSize(850, 670);       
        this.setVisible(true);
        
    }
    
    
    
    
    
    

    @Override
    public JComboBox getModelNumComboBox() {
       return modelNumComboBox;
    }

    @Override
    public JComboBox getModelNameComboBox() {
       return modelNameComboBox;
    }

    @Override
    public JComboBox getMakeComboBox() {
       return makeComboBox;
    }

    @Override
    public JComboBox getTypeComboBox() {
        return typeComboBox;
    }

   
    @Override
    public void clearCarTable() {
        int numberOfRow = this.carTable.getModel().getRowCount();
        
        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.carTable.getModel();
            for (int index = (numberOfRow - 1); index >= 0; index --) {
                tableModel.removeRow(index);
            }            
        }
    }

   @Override
    public void displayMessageInDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    @Override
    public void clearInput() {
       
        this.clearComboBoxes();
    }
    
    @Override
    public void clearComboBoxes(){
        
            this.getModelNumComboBox().setSelectedIndex(0);
            this.getModelNumComboBox().setSelectedIndex(0);
            this.getMakeComboBox().setSelectedIndex(0);
            this.getTypeComboBox().setSelectedIndex(0);
        
    }

    @Override
    public void displayModelNum(List<Car> cars) {
        this.modelNumComboBox.removeAllItems();
        
         this.modelNumComboBox.addItem("");
        
        for (Car car : cars) {
            this.modelNumComboBox.addItem(car.getModelnum());
        }
    }

    @Override
    public void displayModelName(List<Car> cars) {
        this.modelNameComboBox.removeAllItems();
        this.modelNameComboBox.addItem("");
        for (Car car : cars) {
            this.modelNameComboBox.addItem(car.getModelname());
        }
    }

    @Override
    public void displayMake(List<Car> cars) {
        this.makeComboBox.removeAllItems();
         this.makeComboBox.addItem("");
          for (Car car : cars) {
            this.makeComboBox.addItem(car.getMake());
        }
    }

    @Override
    public void displayType() {
        this.typeComboBox.removeAllItems();
         this.typeComboBox.addItem("");
         this.typeComboBox.addItem("Sedan");
         this.typeComboBox.addItem("4 wheel drive");
         this.typeComboBox.addItem("Truck");
    }

    @Override
    public int getSelectedModelNum() {
        if (this.modelNumComboBox.getItemCount() > 0 && this.modelNumComboBox.getSelectedIndex() > 0) {
            return (int)this.modelNumComboBox.getSelectedItem();
        } else {
            return -1;
        }
    }

    @Override
    public String getSelectedModelName() {
        if (this.modelNameComboBox.getItemCount() > 0 && this.modelNameComboBox.getSelectedIndex() > 0) {
            return (String)this.modelNameComboBox.getSelectedItem();
        } else {
            return null;
        }
    }

    @Override
    public String getSelectedMake() {
        if (this.makeComboBox.getItemCount() > 0 && this.makeComboBox.getSelectedIndex() > 0) {
            return (String)this.makeComboBox.getSelectedItem();
        } else {
            return null;
        }
    }

    @Override
    public String getSelectedType() {
        if (this.typeComboBox.getItemCount() > 0 && this.typeComboBox.getSelectedIndex() > 0) {
            return (String)this.typeComboBox.getSelectedItem();
        } else {
            return null;
        }
    }

    @Override
    public JButton getSearchButton() {
        return searchButton;
    }
    
     @Override
    public void displayCarDetails(List<Car> Cars) {
         this.clearCarTable();
        for(Car car :Cars){
             int modelNum = car.getModelnum();
            String make = car.getMake();
            String path = String.valueOf(car.getThumbnail());
            try {
             URL url = new URL(path);
             BufferedImage image = ImageIO.read(url);
             ImageIcon icon = new ImageIcon(image);
             Object[] rowData = {modelNum,make,icon};
//JLabel imageLabel = new JLabel(new ImageIcon(image));
             ((DefaultTableModel)this.carTable.getModel()).addRow(rowData);
          
            //this.thumbnailDisplayLabel.setIcon(new ImageIcon(image));
         } catch (MalformedURLException ex) {
             Logger.getLogger(SearchCarGuiImpl.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(SearchCarGuiImpl.class.getName()).log(Level.SEVERE, null, ex);
         }                
//         ((DefaultTableModel)this.carTable.getModel()).addRow(new Object[]{car.getModelnum(), 
//                                                                                   car.getMake(),
//                                                                                   car.getThumbnail()});
        }
    }
    
    @Override
    public void displaySelectedCarDetails(Car car) {
        this.modelNumDisplayLabel.setText("Model Number:"+String.valueOf(car.getModelnum()));
        
        this.vinDisplayLabel.setText("Vin:"+String.valueOf(car.getVin()));
        this.modelNameDisplayLabel.setText("Model Name:"+String.valueOf(car.getModelname()));
        this.makeDisplayLabel.setText("Make:"+String.valueOf(car.getMake()));
        this.typeDisplayLabel.setText("Type:"+String.valueOf(car.getType()));
        //this.thumbnailDisplayLabel.setText(String.valueOf(car.getThumbnail()));
        String path = String.valueOf(car.getThumbnail());
         try {
             URL url = new URL(path);
             BufferedImage image = ImageIO.read(url);
            this.thumbnailDisplayLabel.setIcon(new ImageIcon(image));
         } catch (MalformedURLException ex) {
             Logger.getLogger(SearchCarGuiImpl.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(SearchCarGuiImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        this.descriptionDisplayLabel.setText("Discription:"+String.valueOf(car.getDescription()));
        this.colourDisplayLabel.setText("Colour:"+String.valueOf(car.getColour()));
        this.milesDisplayLabel.setText("Miles:"+String.valueOf(car.getMiles()));
        this.instockDisplayLabel.setText("Instock:"+String.valueOf(car.getInstock()));
    }

    @Override
    public void displayCarDetails(Car car) {
            this.clearCarTable();
            int modelNum = car.getModelnum();
            String make = car.getMake();
            String path = String.valueOf(car.getThumbnail());
         try {
             URL url = new URL(path);
             BufferedImage image = ImageIO.read(url);
             ImageIcon icon = new ImageIcon(image);
             Object[] rowData = {modelNum,make,icon};
//JLabel imageLabel = new JLabel(new ImageIcon(image));
             ((DefaultTableModel)this.carTable.getModel()).addRow(rowData);
          
            //this.thumbnailDisplayLabel.setIcon(new ImageIcon(image));
         } catch (MalformedURLException ex) {
             Logger.getLogger(SearchCarGuiImpl.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(SearchCarGuiImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
            
         
                                                                             
    }
    
    
    
    @Override
    public JTable getCarTable() {
        return carTable;
    }

    @Override
    public boolean isCarSelected() {
        return (this.carTable.getSelectedRow() >= 0);
    }

    @Override
    public int getSelectedRowModelNum() {
        int selectedRowIndex = this.carTable.getSelectedRow();
        
        String modelNum = this.carTable.getValueAt(selectedRowIndex, 0).toString();
        return Integer.parseInt(modelNum); 
    }
    
    
}
