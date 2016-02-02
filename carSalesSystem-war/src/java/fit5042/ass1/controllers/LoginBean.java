/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.controllers;

import fit5042.ass1.entities.Users;
import fit5042.ass1.usersList.UserList;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Heaven
 */
@ManagedBean
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable  {
    @EJB
    private UserList userList;
    private Users user;
    private int id;
    private String username;
    private String password;
    private String error;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getError() {
        return error;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername() {
        this.username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }
    public void setError(String error) {
        this.error = error;
    }
    
    public void logout() throws IOException {
   // FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    this.username = null;
    FacesContext fc = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
    session.invalidate();
    FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
    }
//    //被绑定到UI组件的action方法，返回值被导航规则处理结果页面跳转判断时使用，导航规则将在后面编写
//    public String valid() throws Exception{
//        if(userList.validateUser(id, password)){
//       
//            return "success";
//        }else{
//        setError("error！");
//        return "failure";
//        
//        }
//    }
//    
//    public String matchUser() throws Exception{
//        user = userList.findByIdAndPassword(id, password);
//        if(user.getType().equals("SalesPerson")){
//            return "salesPerson/welcomeSalesPerson";
//        }else{
//            return "customer/welcomeCustomer";
//        }
//    }
    
   
}
