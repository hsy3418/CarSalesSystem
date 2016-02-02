/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.usersList;

import fit5042.ass1.entities.Car;
import fit5042.ass1.entities.UserGroups;
import fit5042.ass1.entities.Users;
import java.util.List;
import javax.ejb.Remote;


/**
 *
 * @author Heaven
 */
@Remote
public interface UserList {
    
   
    
    public List<Users> findCustomers() throws Exception;
     
    public void create(Users user) throws Exception;
    
    public void createUserGroup(UserGroups usergroup)throws Exception;
        
    

    public void edit(Users user)  throws Exception;
       

    public void remove(Users user)  throws Exception;
        


    public List<Users> findSalesPerson() throws Exception;
    
    public Users find(int id) throws Exception;
    
    public List<Users> findAll() throws Exception;
    
    public Users findByIdAndPassword(int id,String password)throws Exception;
    
    public Boolean validateUser(int id,String password)throws Exception;
       
    public List<Users> findByfirstNameAndlastNameAndEmail
        (String firstName, String lastName, String email,String type) throws Exception;
    
    public List<Users> findByfirstNameAndlastName
        (String firstName, String lastName,String type) throws Exception;
    
    public List<Users> findByfirstNameAndEmail
        (String firstName,String email,String type) throws Exception;
        
    public List<Users> findBylastNameAndEmail
        (String lastName, String email,String type) throws Exception;
    
    public List<Users> findByfirstName
        (String firstName,String type) throws Exception;
    
    public List<Users> findBylastName
        (String lastName,String type) throws Exception;
    
    public List<Users> findByEmail
        (String email,String type) throws Exception;
        
    public Users findByUsername(String username) throws Exception;
}
