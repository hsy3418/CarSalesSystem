/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Heaven
 */
@Entity
@Table(name = "SALE")
@XmlRootElement
@NamedQueries({
//    @NamedQuery(name = "Sale.findCar",query = "SELECT s FROM Sale s WHERE s.custid "),
    @NamedQuery(name = "Sale.findAll", query = "SELECT s FROM Sale s"),
    @NamedQuery(name = "Sale.findByCustomer",query = "SELECT s FROM Sale s WHERE s.custid = :customer"),
    @NamedQuery(name = "Sale.findBySalenum", query = "SELECT s FROM Sale s WHERE s.salenum = :salenum"),
    @NamedQuery(name = "Sale.findBySalesdate", query = "SELECT s FROM Sale s WHERE s.salesdate = :salesdate"),
    @NamedQuery(name = "Sale.findByIfnotpaid", query = "SELECT s FROM Sale s WHERE s.ifnotpaid = :ifnotpaid")})
public class Sale implements Serializable {
    
    public static final String GET_ALL_BY_CUSTOMER = "Sale.findByCustomer";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALENUM")
    private Integer salenum;
    @Size(max = 100)
    @Column(name = "SALESDATE")
    private String salesdate;
    @Column(name = "IFNOTPAID")
    private Character ifnotpaid;
    @JoinColumn(name = "MODELNUM", referencedColumnName = "MODELNUM")
    @ManyToOne
    private Car modelnum;
    @JoinColumn(name = "SALESPERSONID", referencedColumnName = "ID")
    @ManyToOne
    private Users salespersonid;
    @JoinColumn(name = "CUSTID", referencedColumnName = "ID")
    @ManyToOne
    private Users custid;

    public Sale() {
    }

    public Sale(String salesdate, Character ifnotpaid, Car modelnum, Users salespersonid, Users custid) {
        this.salesdate = salesdate;
        this.ifnotpaid = ifnotpaid;
        this.modelnum = modelnum;
        this.salespersonid = salespersonid;
        this.custid = custid;
    }

    
    public Sale(Integer salenum) {
        this.salenum = salenum;
    }

    public Integer getSalenum() {
        return salenum;
    }

    public void setSalenum(Integer salenum) {
        this.salenum = salenum;
    }

    public String getSalesdate() {
        return salesdate;
    }

    public void setSalesdate(String salesdate) {
        this.salesdate = salesdate;
    }

    public Character getIfnotpaid() {
        return ifnotpaid;
    }

    public void setIfnotpaid(Character ifnotpaid) {
        this.ifnotpaid = ifnotpaid;
    }

    public Car getModelnum() {
        return modelnum;
    }

    public void setModelnum(Car modelnum) {
        this.modelnum = modelnum;
    }

    public Users getSalespersonid() {
        return salespersonid;
    }

    public void setSalespersonid(Users salespersonid) {
        this.salespersonid = salespersonid;
    }

    public Users getCustid() {
        return custid;
    }

    public void setCustid(Users custid) {
        this.custid = custid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salenum != null ? salenum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sale)) {
            return false;
        }
        Sale other = (Sale) object;
        if ((this.salenum == null && other.salenum != null) || (this.salenum != null && !this.salenum.equals(other.salenum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fit5042.ass1.entities.Sale[ salenum=" + salenum + " ]";
    }
    
}
