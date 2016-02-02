/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.userListImpl;

import fit5042.ass1.entities.Car;
import fit5042.ass1.entities.UserGroups;
import fit5042.ass1.entities.Users;
import fit5042.ass1.usersList.UserList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Heaven
 */
@Stateless
public class UserListImpl implements UserList{
    
    @PersistenceContext(unitName = "fit5042assignment-ejbPU")
    private EntityManager em;

    @Override
    public void create(Users user) throws Exception {
         em.persist(user);
    }

    @Override
    public void createUserGroup(UserGroups usergroup) throws Exception {
            em.persist(usergroup);
    }

    @Override
    public void edit(Users user) throws Exception {
         em.merge(user);
    }

    @Override
    public void remove(Users user) throws Exception {
          Users userToBeRemoved = em.getReference(Users.class, user.getId());
          em.remove(userToBeRemoved);

    }

    @Override
    public Users find(int id) throws Exception {
                Users user= em.find(Users.class, id);
        
        return user;
    }

    @Override
    public List<Users> findAll() throws Exception {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Users.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public Users findByIdAndPassword(int id,String password) throws Exception {
       return (Users) this.em.createNamedQuery(Users.GET_USER_BY_ID_AND_PASSWORD).setParameter("id",id).setParameter("password",password).
               getSingleResult();
               
    }

    @Override
    public Boolean validateUser(int id, String password) throws Exception {
        Users user  = new Users();
        user = (Users) this.em.createNamedQuery(Users.GET_USER_BY_ID_AND_PASSWORD).setParameter("id",id).getSingleResult();
        if(user.getFirstnam().isEmpty()){
            return false;
           
        }
        else
            return true;
       
    }
    
    @Override
    public List<Users> findByfirstNameAndlastNameAndEmail(String firstName, String lastName, String email,String type) throws Exception {
        return this.em.createNamedQuery(Users.GET_ALL_BY_FNAME_LNAME_EMAIL).
                setParameter("firstnam",firstName).setParameter("lastname",lastName).setParameter("email",email).
                setParameter("type",type).
                getResultList();
    }

    @Override
    public List<Users> findByfirstNameAndlastName(String firstName, String lastName, String type) throws Exception {
        return this.em.createNamedQuery(Users.GET_ALL_BY_FNAME_LNAME).
                setParameter("firstnam",firstName).setParameter("lastname",lastName).
                setParameter("type",type).
                getResultList();
    }
    
    

    @Override
    public List<Users> findByfirstNameAndEmail(String firstName, String email, String type) throws Exception {
         return this.em.createNamedQuery(Users.GET_ALL_BY_FNAME_EMAIL).
                setParameter("firstnam",firstName).setParameter("email",email).
                setParameter("type",type).
                getResultList();
    }

    @Override
    public List<Users> findBylastNameAndEmail(String lastName, String email, String type) throws Exception {
         return this.em.createNamedQuery(Users.GET_ALL_BY_LNAME_EMAIL).
                setParameter("lastname",lastName).setParameter("email",email).
                setParameter("type",type).
                getResultList();
    }
    @Override
    public Users findByUsername(String username) throws Exception {
         return (Users)this.em.createNamedQuery(Users.GET_ALL_BY_USERNAME).
                setParameter("username",username). 
                getSingleResult();
    }
    

    @Override
    public List<Users> findByfirstName(String firstName, String type) throws Exception {
        return this.em.createNamedQuery(Users.GET_ALL_BY_FNAME).
                setParameter("firstnam",firstName).
                setParameter("type",type).
                getResultList();
    }

    @Override
    public List<Users> findBylastName(String lastName, String type) throws Exception {
         return this.em.createNamedQuery(Users.GET_ALL_BY_LNAME).
                setParameter("lastname",lastName).
                setParameter("type",type).
                getResultList();
    }

    @Override
    public List<Users> findByEmail(String email, String type) throws Exception {
         return this.em.createNamedQuery(Users.GET_ALL_BY_EMAIL).
                setParameter("email",email).
                setParameter("type",type).
                getResultList();
    }

    @Override
    public List<Users> findSalesPerson() throws Exception {
        String type = "salesperson";
        return this.em.createNamedQuery(Users.GET_ALL_BY_TYPE).setParameter("type",type).getResultList();
    }

    @Override
    public List<Users> findCustomers() throws Exception {
        String type = "customer";
         return this.em.createNamedQuery(Users.GET_ALL_BY_TYPE).setParameter("type",type).getResultList();
    }
    
   
   
}
