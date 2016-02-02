/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.controllers;


import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import fit5042.ass1.entities.Car;
import fit5042.ass1.entities.Sale;
import fit5042.ass1.entities.UserGroups;
import fit5042.ass1.entities.Users;
import fit5042.ass1.salesList.salesList;
import fit5042.ass1.usersList.UserList;
import hashedpasswordgenerator.HashedPasswordGenerator;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Heaven
 */
@ManagedBean
@Named(value = "userControler")
@SessionScoped
public class UserControler implements Serializable {
    
    @EJB
    private salesList salesList;
    @EJB
    private UserList userList;
    
    private Users user= new Users();
    private Users salesPerson;
    private Users customer;
    private UserGroups group = new UserGroups();
    private List<Users> salesPeople;
    private int id;
    private String email;
    private String type;
    private String firstName;
    private String lastName;
    private List<SelectItem> selectItems;
    
    
    private List<Sale> sales;
    
    //private UserGroups group = new UserGroups();
    /**
     * Creates a new instance of UserControler
     */
    public UserControler() {
        
    }

    @PostConstruct
    public void init() {
        try{
        salesPeople = this.userList.findSalesPerson();
        }
        catch (Exception ex) {
            Logger.getLogger(UserControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Users> getSalesPeople() {    
        return salesPeople;
    }

    public void setSalesPeople(List<Users> salesPeople) {
        this.salesPeople = salesPeople;
    }

    public UserGroups getGroup() {
        return group;
    }

    public void setGroup(UserGroups group) {
        this.group = group;
    }

    
    public Users getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(Users salesPerson) {
        this.salesPerson = salesPerson;
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }

    
    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Users getCustomer() {
        return customer;
    }

    public void setCustomer(Users customer) {
        this.customer = customer;
        
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Sale> getSales() {
        return sales;
    }

    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public List<Users> findAll() throws Exception{
        return this.userList.findAll();
        
    }
    
    public List<Users>findCustomers()throws Exception{
        return this.userList.findCustomers();
    }
    
    public String addUser() {
        try {
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String username = request.getParameter("addCustomerForm:username");
            String lastname = request.getParameter("addCustomerForm:lastname");
            String firstnam = request.getParameter("addCustomerForm:firstnam");
            String email = request.getParameter("addCustomerForm:email");
            String password = request.getParameter("addCustomerForm:password");
            String hashedPassword = HashedPasswordGenerator.sha256(password);
            String type = request.getParameter("addCustomerForm:type");
            String address = request.getParameter("addCustomerForm:address");
            String phone = request.getParameter("addCustomerForm:phone");
            String mobile = request.getParameter("addCustomerForm:mobile");
            
            Users u =
                    new Users(lastname,firstnam,email,hashedPassword,type,address,phone,mobile,username);
            UserGroups g = new UserGroups(type,username);
            this.userList.create(u);
            this.userList.createUserGroup(g);   
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "Congraluations!You successfully created a customer");
             RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception ex) {
            Logger.getLogger(UserControler.class.getName()).log(Level.SEVERE, null, ex);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "something wrong..");
             RequestContext.getCurrentInstance().showMessageInDialog(message);
        }
        return "addCustomer";
    }
    
     public String add() throws Exception{
       
        
        //this.user = new Users();
       
//        group.setGroupname(this.user.getType());
//        
//        group.setId(this.user.getId());
//        group.setUsername(this.user.getUsername());
//        
        this.userList.create(user);
        this.userList.createUserGroup(group);
//        group.setId(this.user.getId());
//        group.setGroupname(this.user.getType());
//        this.userList.add(group);
//        this.group = new UserGroups();
        return "addCustomer";
    }
    
    public List<Users> searchCustomer() throws Exception{ 
        String customerType = "customer";
        List<Users> customers = new ArrayList();
        if (id > 0){
               user = userList.find(id);
               
               customers.add(user);
        }else if(firstName !=null){
              if(lastName!=null&&email!=null){
                  customers = this.userList.findByfirstNameAndlastNameAndEmail(firstName,lastName,email,customerType);

              }else if(lastName==null&&email!=null){
                  customers = this.userList.findByfirstNameAndEmail(firstName,email,customerType);
              }else if(lastName!=null&&email==null){
                   customers = this.userList.findByfirstNameAndlastName(firstName,lastName,customerType);
              }else if(lastName==null&&email==null){
                  customers = this.userList.findByfirstName(firstName,customerType);
              }
        }else if(firstName == null){
            if(lastName!=null&&email!=null){
                customers = this.userList.findBylastNameAndEmail(lastName,email,customerType);
            }else if(lastName==null&&email!=null){
                customers = this.userList.findByEmail(email,customerType);
                //return cars;
            }else if(lastName!=null&&email==null){
               customers = this.userList.findBylastName(lastName,customerType);
            }else if(lastName==null&email==null){
               customers = this.userList.findAll();
            }
        }else{
              return customers;
        }
        return customers;
    }
    
    public void delete(Users user){
        try {
            this.userList.remove(user);
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "Something wrong with deleting");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            Logger.getLogger(UserControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String edit() throws Exception{
      this.userList.edit(this.user);
      return "viewCustomers";
    }
    
    public String edit(Users customer) throws Exception{
     this.user = customer;
      return "editCustomer";
    }
     
    public String displayDetails() throws Exception{
        customer= userList.find(this.findUserName());
        return "displayDetails";
    }
    
    public Integer findUserName() throws Exception{
        HttpServletRequest request = 
                (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        String username = request.getRemoteUser();
        Users u = this.userList.findByUsername(username);
        int userId = u.getId();
        return userId;
    }
     
    public List<Users> findSalesPerson() throws Exception{
        return this.userList.findSalesPerson();
    }
    
    public String viewCustomer(Users customer)throws Exception{
        this.customer = customer;
//        sales = new ArrayList<Sale>(); //customer.getSaleList1();
//        sales=customer.getSaleList1();
        return "viewCustomer";
    }
    
    public List<Sale> findSaleByCustomer() throws Exception{
        return this.salesList.findSaleByCustomer(customer);
    }
    
    
//    public void findPurchasedCars()throws Exception{
//        return this.userList.findPurchasedCars(customer);
//    }
}
