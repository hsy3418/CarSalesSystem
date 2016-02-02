/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.carsCatelogue;

import fit5042.ass1.entities.Car;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Heaven
 */
@Remote
public interface CarsCatelogue {
    
    
    public void create(Car car) throws Exception;
        
    

    public void edit(Car car)  throws Exception;
       

    public void remove(Car car)  throws Exception;
        

    public Car find(int id) throws Exception;
       

    public List<Car> findAll() throws Exception;
        

    public List<Car>findRange(int[] range) throws Exception;
       

    public List<Car>findByNameAndMakeAndType(String modelname,String make,String type) throws Exception;
    
    public List<Car>findByNameAndMake(String modelname,String make) throws Exception;
    
    public List<Car>findByNameAndType(String modelname,String type) throws Exception;
    
    public List<Car>findByMakeAndType(String make,String type) throws Exception;
    
    public List<Car>findByMake(String make) throws Exception;
    
    public List<Car>findByType(String type) throws Exception;
    
    public List<Car>findByModelName(String modelName) throws Exception;
    
    public void UpdateCar(String vin)throws Exception;
    
    public void Update(int modelnum)throws Exception;
   
        
}
