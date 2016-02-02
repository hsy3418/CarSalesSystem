/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.entities;

import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Heaven
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
//@DiscriminatorColumn(  
//  
//name="Type",discriminatorType=DiscriminatorType.STRING  
//  
//)  
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id "),
    @NamedQuery(name = Users.GET_ALL_BY_LNAME, query = "SELECT u FROM Users u WHERE u.lastname = :lastname AND u.type = :type"),
    @NamedQuery(name = Users.GET_ALL_BY_FNAME, query = "SELECT u FROM Users u WHERE u.firstnam = :firstnam AND u.type = :type"),
    @NamedQuery(name = Users.GET_ALL_BY_EMAIL, query = "SELECT u FROM Users u WHERE u.email = :email AND u.type = :type"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = Users.GET_ALL_BY_TYPE, query = "SELECT u FROM Users u WHERE u.type = :type"),
    @NamedQuery(name = Users.GET_USER_BY_ID_AND_PASSWORD, query = "SELECT u FROM Users u WHERE u.id = :id AND u.password = :password"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = Users.GET_ALL_BY_FNAME_LNAME_EMAIL , query = "SELECT u FROM Users u WHERE u.firstnam = :firstnam "
            + "AND u.lastname = :lastname AND u.email = :email AND u.type = :type"),
    @NamedQuery(name = Users.GET_ALL_BY_FNAME_LNAME, query = "SELECT u FROM Users u WHERE u.firstnam = :firstnam "
            + "AND u.lastname = :lastname AND u.type = :type"),
    @NamedQuery(name = Users.GET_ALL_BY_FNAME_EMAIL , query = "SELECT u FROM Users u WHERE u.firstnam = :firstnam "
            + "AND u.email = :email AND u.type = :type"),
    @NamedQuery(name = Users.GET_ALL_BY_USERNAME , query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = Users.GET_ALL_BY_LNAME_EMAIL , query = "SELECT u FROM Users u WHERE u.lastname = :lastname AND u.email = :email AND u.type = :type"),
//    @NamedQuery(name = Users.GET_USER_BY_ID_AND_PASSWORD, query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findByMobile", query = "SELECT u FROM Users u WHERE u.mobile = :mobile")})
public class Users implements Serializable {
    
    public static final String GET_ALL_BY_TYPE = "Users.findByType";
    public static final String GET_ALL_BY_USERNAME = "Users.findByUsername";
    public static final String GET_ALL_BY_EMAIL = "Users.findByEmail";
    public static final String GET_ALL_BY_LNAME = "Users.findByLname";
    public static final String GET_ALL_BY_FNAME = "Users.findByFname";
    public static final String GET_ALL_BY_LNAME_EMAIL = "Users.findByLNameANDEmail";
    public static final String GET_ALL_BY_FNAME_EMAIL = "Users.findByFnameANDEmail";
    public static final String GET_ALL_BY_FNAME_LNAME = "Users.findByFnameAndLName";
    public static final String GET_ALL_BY_FNAME_LNAME_EMAIL = "Users.findByFnameAndLNameANDEmail";
    public static final String GET_USER_BY_ID_AND_PASSWORD ="Users.findByIdAndPassword";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @NotNull(message="Please provide your lastname")
    @Size(max = 40)
    @Pattern(regexp="^[a-zA-Z\\\\s]*$",message="digits in last name is illegal")
    @Column(name = "LASTNAME")
    private String lastname;
    @NotNull(message="Please provide your firstname")
    @Pattern(regexp="^[a-zA-Z\\\\s]*$",message="digits in first name is illegal")
    @Size(max = 40)
    @Column(name = "FIRSTNAM")
    private String firstnam;
    //@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 200)
    @NotNull(message="Please provide your email")
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
             message="invalid email")
    @Column(name = "EMAIL")
    private String email;
    @NotNull(message="Please provide your password")
    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;
    @NotNull(message="Please provide your type")
    @Size(max = 20)
    @Column(name = "TYPE")
    private String type;
    @NotNull(message="Please provide your address")
    @Size(max = 200)
    @Column(name = "ADDRESS")
    private String address;
    @NotNull(message="Please provide your phone number")
    @Pattern(regexp="^([9]{1})([0-9]{7})",message="the number must start with 9 and have 8 digits")
    @Size(max = 20)
    @Column(name = "PHONE")
    private String phone;
    @NotNull(message="Please provide your mobile number")
    @Pattern(regexp="^([0]{1})([0-9]{9})",message="the number must start with 9 and have 8 digits")
    @Size(max = 20)
    @Column(name = "MOBILE")
    private String mobile;
    @OneToMany(mappedBy = "salespersonid")
    private List<Sale> saleList;
    @OneToMany(mappedBy = "custid")
    private List<Sale> saleList1;
    @NotNull(message="Please provide your username")
    @Size(max = 255)
    @Column(name = "USERNAME")
    private String username;

    public Users(String lastname, String firstnam, String email, String password, String type, String address, String phone, String mobile, String username) {
        
        this.lastname = lastname;
        this.firstnam = firstnam;
        this.email = email;
        this.password = password;
        this.type = type;
        this.address = address;
        this.phone = phone;
        this.mobile = mobile;
        this.username = username;
    }
    
    

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstnam() {
        return firstnam;
    }

    public void setFirstnam(String firstnam) {
        this.firstnam = firstnam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlTransient
    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }

    @XmlTransient
    public List<Sale> getSaleList1() {
        return saleList1;
    }

    public void setSaleList1(List<Sale> saleList1) {
        this.saleList1 = saleList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fit5042.ass1.entities.Users[ id=" + id + " ]";
    }  
}
