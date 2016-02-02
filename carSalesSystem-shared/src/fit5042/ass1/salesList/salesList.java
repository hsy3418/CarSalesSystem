/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.salesList;

import fit5042.ass1.entities.Car;
import fit5042.ass1.entities.Sale;
import fit5042.ass1.entities.Users;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Heaven
 */
@Remote

public interface salesList {
    
     //public List<Car> findPurchasedCars(Users customer)throws Exception;
    
    public void create(Sale sale) throws Exception;
        
    

    public void edit(Sale sale)  throws Exception;
       

    public void remove(Sale sale)  throws Exception;
        


    
    public Sale find(int id) throws Exception;
    public List<Sale> findSaleByCustomer(Users customer) throws Exception;
    
}
