/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.salesListImpl;

import fit5042.ass1.entities.Car;
import fit5042.ass1.entities.Sale;
import fit5042.ass1.entities.Users;
import fit5042.ass1.salesList.salesList;
import java.util.List;
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
public class salesListImpl implements salesList {
    
    @PersistenceContext(unitName = "fit5042assignment-ejbPU")
    private EntityManager em;

    @Override
    public void create(Sale sale) throws Exception {
        //sale.getModelnum().getModelnum();
       
        em.persist(sale);

       //em.createNamedQuery(Car.UPDATE_INSTOCK).setParameter("modelnum",sale.getModelnum().getModelnum());
    }

    @Override
    public void edit(Sale sale) throws Exception {
         em.merge(sale);
    }

    @Override
    public void remove(Sale sale) throws Exception {
         em.remove(sale);
    }

    @Override
    public Sale find(int id) throws Exception {
         Sale sale= em.find(Sale.class, id);
        
        return sale;
    }

    @Override
    public List<Sale> findSaleByCustomer(Users customer) throws Exception {

        return this.em.createNamedQuery(Sale.GET_ALL_BY_CUSTOMER).
                setParameter("customer",customer).
                getResultList();
    }
    
}
