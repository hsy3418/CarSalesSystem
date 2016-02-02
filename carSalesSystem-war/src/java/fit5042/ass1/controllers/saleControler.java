/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.controllers;

import fit5042.ass1.carsCatelogue.CarsCatelogue;
import fit5042.ass1.entities.Car;
import fit5042.ass1.entities.Sale;
import fit5042.ass1.entities.UserGroups;
import fit5042.ass1.entities.Users;
import fit5042.ass1.salesList.salesList;
import fit5042.ass1.usersList.UserList;
import hashedpasswordgenerator.HashedPasswordGenerator;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import   java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Heaven
 */
@ManagedBean
@Named(value = "saleControler")
@ApplicationScoped
public class saleControler implements Serializable {
    @EJB
     private UserList userList;
     @EJB
    private salesList salesList;
      @EJB
    private CarsCatelogue carsCatelogue;
    private int salenum;
    private String salesdate;
    private char ifnotpaid;
    private Car car;
    private Users customer;
    private Users salesPerson;
    private Sale sale = new Sale();
    private CarControler carControler;
   
    /**
     * Creates a new instance of saleControler
     */
    public saleControler() {
        
        
    }

    public salesList getSalesList() {
        return salesList;
    }

    public void setSalesList(salesList salesList) {
        this.salesList = salesList;
    }

    public int getSalenum() {
        return salenum;
    }

    public void setSalenum(int salenum) {
        this.salenum = salenum;
    }

    public String getSalesdate() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        salesdate=sdf.format(new java.util.Date());  
        return salesdate;
    }

    public void setSalesdate(String salesdate) {
        this.salesdate = salesdate;
    }

    public char getIfnotpaid() {
        return ifnotpaid;
    }

    public void setIfnotpaid(char ifnotpaid) {
        this.ifnotpaid = ifnotpaid;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Users getCustomer() {
        return customer;
    }

    public void setCustomer(Users customer) {
        this.customer = customer;
    }

    public Users getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(Users salesPerson) {
        this.salesPerson = salesPerson;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
    
    
    public String add() throws Exception{
        sale.setSalesdate(this.getSalesdate());
        this.salesList.create(sale);
        this.sale = new Sale();
//        group.setId(this.user.getId());
//        group.setGroupname(this.user.getType());
//        this.userList.add(group);
//        this.group = new UserGroups();
        return "buyCar";
    }
    
    public String buyCar(Car car)throws Exception{
         this.car = car;
         return "buyCar";
        
    }
    

      public String addSale() throws Exception{
         HttpServletRequest request = 
                (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String username = request.getRemoteUser();
        System.out.println("jkjk");
        Users c = this.userList.findByUsername(username);
        String ifPay = request.getParameter("addSaleForm:ifPaid");
        char pay= ifPay.charAt(0);
        
        String salesIdForm = request.getParameter("addSaleForm:salesId");
        int salesId = Integer.parseInt(salesIdForm);
        Users sales = userList.find(salesId);
//        carControler = new CarControler();
//        Car purchasedCar = carControler.getCar();
//        int carModelNum = car.getModelnum();
//        Car purchasedCar = carsCatelogue.find(carModelNum);
        String salesDate = this.getSalesdate();
        Sale s =  new Sale(salesDate, pay, car, sales,c);
        System.out.println("jkjk");
        this.salesList.create(s);
     
        return "buyCar";   
    }
      
      
}
