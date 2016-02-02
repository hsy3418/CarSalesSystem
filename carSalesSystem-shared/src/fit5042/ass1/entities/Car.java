/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "CAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c"),
    @NamedQuery(name = "Car.findByModelnum", query = "SELECT c FROM Car c WHERE c.modelnum = :modelnum"),
    @NamedQuery(name = "Car.findByVin", query = "SELECT c FROM Car c WHERE c.vin = :vin"),
    @NamedQuery(name = Car.GET_ALL_BY_NAME, query = "SELECT c FROM Car c WHERE c.modelname = :modelname"),
    @NamedQuery(name = Car.GET_ALL_BY_MAKE, query = "SELECT c FROM Car c WHERE c.make = :make"),
    @NamedQuery(name = Car.GET_ALL_BY_TYPE, query = "SELECT c FROM Car c WHERE c.type = :type"),
    @NamedQuery(name = "Car.findByThumbnail", query = "SELECT c FROM Car c WHERE c.thumbnail = :thumbnail"),
    @NamedQuery(name = "Car.findByDescription", query = "SELECT c FROM Car c WHERE c.description = :description"),
    @NamedQuery(name = "Car.findByPreviewurl", query = "SELECT c FROM Car c WHERE c.previewurl = :previewurl"),
    @NamedQuery(name = "Car.findByColour", query = "SELECT c FROM Car c WHERE c.colour = :colour"),
    @NamedQuery(name = "Car.findByMiles", query = "SELECT c FROM Car c WHERE c.miles = :miles"),
    @NamedQuery(name = Car.GET_ALL_BY_NAME_MAKE_TYPE, query = "SELECT c FROM Car c WHERE "
            + "c.modelname = :modelname AND c.make = :make AND c.type = :type"),
    @NamedQuery(name = Car.GET_ALL_BY_NAME_MAKE,query = "SELECT c FROM Car c WHERE "
            + "c.modelname = :modelname AND c.make = :make"),
    @NamedQuery(name = Car.GET_ALL_BY_NAME_TYPE,query = "SELECT c FROM Car c WHERE "
            + "c.modelname = :modelname AND c.type = :type"),
    @NamedQuery(name = Car.GET_ALL_BY_MAKE_TYPE,query = "SELECT c FROM Car c WHERE "
            + "c.make = :make AND c.type = :type"),
    @NamedQuery(name = Car.UPDATE_INSTOCK,query = "UPDATE Car c SET c.instock = 'N' WHERE c.modelnum = :modelnum"),
    @NamedQuery(name = "Car.findByInstock", query = "SELECT c FROM Car c WHERE c.instock = :instock")})
public class Car implements Serializable {
    
     public static final String UPDATE_INSTOCK ="Car.UPDATE";
    public static final String GET_ALL_BY_NAME_MAKE_TYPE ="Car.findByModelNameMakeType";
    public static final String GET_ALL_BY_NAME_MAKE="Car.findByNameAndMake";
    public static final String GET_ALL_BY_NAME_TYPE ="Car.findByNameAndType";
    public static final String GET_ALL_BY_MAKE_TYPE ="Car.findByMakeAndType";
    public static final String GET_ALL_BY_NAME ="Car.findByModelName";
    public static final String GET_ALL_BY_MAKE ="Car.findByMake";
    public static final String GET_ALL_BY_TYPE ="Car.findByType";
    
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "MODELNUM")
    private Integer modelnum;
    @NotNull(message="Please provide a vin number")
//    @Pattern(regexp="^(([a-h,A-H,j-n,J-N,p-z,P-Z,0-9]{9})"
//            + "([a-h,A-H,j-n,J-N,p,P,r-t,R-T,v-z,V-Z,0-9"
//            + "a-h,A-H,j-n,J-N,p-z,P-Z,0-9])(\\d{6}))$",
//             message="invalid vin number")
    @Size(max = 100)
    @Column(name = "VIN")
    private String vin;
    @NotNull(message="Please provide a model name")
    @Size(max = 100)
    @Column(name = "MODELNAME")
    private String modelname;
    @NotNull(message="Please provide a make")
    @Size(max = 100)
    @Column(name = "MAKE")
    private String make;
    @NotNull(message="Please provide a type")
    @Size(max = 100)
    @Column(name = "TYPE")
    private String type;
    @NotNull(message="Please provide a thumbanil")
    @Size(max = 255)
    @Column(name = "THUMBNAIL")
    private String thumbnail;
    @NotNull(message="Please provide a description")
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;
    @NotNull(message="Please provide a previewurl")
    @Size(max = 255)
    @Column(name = "PREVIEWURL")
    private String previewurl;
    @NotNull(message="Please provide  a color")
    @Size(max = 100)
    @Column(name = "COLOUR")
    private String colour;
    @NotNull(message="Please provide  miles")
    @Size(max = 100)
    @Column(name = "MILES")
    private String miles;
    @NotNull(message="Please provide instock information")
    @Column(name = "INSTOCK")
    private Character instock;
    @OneToMany(mappedBy = "modelnum")
    private List<Sale> saleList;
    public Car() {
    }

    public Car(Integer modelnum) {
        this.modelnum = modelnum;
    }

    public Integer getModelnum() {
        return modelnum;
    }

    public void setModelnum(Integer modelnum) {
        this.modelnum = modelnum;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreviewurl() {
        return previewurl;
    }

    public void setPreviewurl(String previewurl) {
        this.previewurl = previewurl;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }

    public Character getInstock() {
        return instock;
    }

    public void setInstock(Character instock) {
        this.instock = instock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modelnum != null ? modelnum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.modelnum == null && other.modelnum != null) || (this.modelnum != null && !this.modelnum.equals(other.modelnum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fit5042.ass1.entities.Car[ modelnum=" + modelnum + " ]";
    }

    @XmlTransient
    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }
    
}
