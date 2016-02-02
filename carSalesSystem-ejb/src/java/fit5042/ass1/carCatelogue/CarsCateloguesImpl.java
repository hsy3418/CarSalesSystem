/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.carCatelogue;

import fit5042.ass1.carsCatelogue.CarsCatelogue;
import fit5042.ass1.entities.Car;
import static java.util.EnumSet.range;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Heaven
 */

@Stateless
public class CarsCateloguesImpl implements CarsCatelogue {
    
    @PersistenceContext(unitName = "fit5042assignment-ejbPU")
    private EntityManager em;
    
   
    
    @Override
    public void create(Car car) throws Exception {
       em.persist(car);
    }

    @Override
    public void edit(Car car) throws Exception {
        em.merge(car);
    }

    @Override
    public void remove(Car car) throws Exception {
        em.remove(em.merge(car));
    }

    @Override
    public Car find(int id) throws Exception {
        Car car = em.find(Car.class, id);
        
        return car;
    }

    @Override
    public List<Car> findAll() throws Exception {
        System.out.println("inside jpa");
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Car.class));
        return em.createQuery(cq).getResultList();
    }
    
    @Override
    public void Update(int modelnum){
        // create update
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaUpdate<Car> update = cb.createCriteriaUpdate(Car.class);
  
            // set the root class
            Root e = update.from(Car.class);
  
  // set update and where clause
            update.set("instock", "N");
             update.where(cb.greaterThanOrEqualTo(e.get("modelnum"),modelnum));
  
        // perform update
        this.em.createQuery(update).executeUpdate();
        //this.em.createNamedQuery(Car.UPDATE_INSTOCK).setParameter("modelnum",modelnum);
    }
    
    @Override
    public void UpdateCar(String vin){
        CriteriaBuilder qb= em.getCriteriaBuilder();
         
         CriteriaUpdate<Car> crit = qb.createCriteriaUpdate(Car.class);
         Root<Car> candidate = crit.from(Car.class);
         candidate.alias("c");
         crit.set(candidate.get("INSTOCK"), "N");
        Predicate teamName = qb.equal(candidate.get("VIN"), vin);
        crit.where(teamName);
        Query q = em.createQuery(crit);
        int num = q.executeUpdate();
    
    }
    
    @Override
    public List<Car> findRange(int[] range) throws Exception {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Car.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public List<Car> findByNameAndMakeAndType(String modelname, String make, String type) throws Exception {
        return this.em.createNamedQuery(Car.GET_ALL_BY_NAME_MAKE_TYPE).
                setParameter("modelname",modelname).setParameter("make",make).setParameter("type",type).
                getResultList();
    }

    @Override
    public List<Car> findByNameAndMake(String modelname, String make) throws Exception {
        return this.em.createNamedQuery(Car.GET_ALL_BY_NAME_MAKE).setParameter("modelname",modelname).
                setParameter("make",make).getResultList();
    }

    @Override
    public List<Car> findByNameAndType(String modelname, String type) throws Exception {
        return this.em.createNamedQuery(Car.GET_ALL_BY_NAME_TYPE).setParameter("modelname",modelname).
                setParameter("type",type).getResultList();
    }

    @Override
    public List<Car> findByMakeAndType(String make, String type) throws Exception {
        return this.em.createNamedQuery(Car.GET_ALL_BY_MAKE_TYPE).setParameter("make",make).
                setParameter("type",type).
                getResultList();
    }

    @Override
    public List<Car> findByMake(String make) throws Exception {

        return this.em.createNamedQuery(Car.GET_ALL_BY_MAKE).setParameter("make",make).
                getResultList();
    }

    @Override
    public List<Car> findByType(String type) throws Exception {
        return this.em.createNamedQuery(Car.GET_ALL_BY_TYPE).setParameter("type",type).
                getResultList();
    }

    @Override
    public List<Car> findByModelName(String modelName) throws Exception {
        return this.em.createNamedQuery(Car.GET_ALL_BY_NAME).setParameter("modelname",modelName).
                getResultList();
    }

    
    
    
}
    

