/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "USER_GROUPS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserGroups.findAll", query = "SELECT u FROM UserGroups u"),
    @NamedQuery(name = "UserGroups.findByGroupid", query = "SELECT u FROM UserGroups u WHERE u.groupid = :groupid"),
    @NamedQuery(name = "UserGroups.findByGroupname", query = "SELECT u FROM UserGroups u WHERE u.groupname = :groupname"),
    //@NamedQuery(name = "UserGroups.findById", query = "SELECT u FROM UserGroups u WHERE u.id = :id"),
    @NamedQuery(name = "UserGroups.findByUsername", query = "SELECT u FROM UserGroups u WHERE u.username = :username")})
public class UserGroups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GROUPID")
    private Integer groupid;
    @Size(max = 20)
    @Column(name = "GROUPNAME")
    private String groupname;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "ID")
//    private int id;
    @Size(max = 255)
    @Column(name = "USERNAME")
    private String username;

    public UserGroups() {
    }

    public UserGroups(Integer groupid) {
        this.groupid = groupid;
    }

//    public UserGroups(Integer groupid, int id) {
//        this.groupid = groupid;
//        this.id = id;
//    }

    public UserGroups(String groupname, String username) {
        this.groupname = groupname;
        this.username = username;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupid != null ? groupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroups)) {
            return false;
        }
        UserGroups other = (UserGroups) object;
        if ((this.groupid == null && other.groupid != null) || (this.groupid != null && !this.groupid.equals(other.groupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fit5042.ass1.entities.UserGroups[ groupid=" + groupid + " ]";
    }
    
}
