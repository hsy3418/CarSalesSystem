package fit5042.ass1.entities;

import fit5042.ass1.entities.Sale;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-20T23:10:34")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, String> username;
    public static volatile SingularAttribute<Users, String> phone;
    public static volatile ListAttribute<Users, Sale> saleList1;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, String> address;
    public static volatile SingularAttribute<Users, String> lastname;
    public static volatile SingularAttribute<Users, String> firstnam;
    public static volatile SingularAttribute<Users, String> type;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile ListAttribute<Users, Sale> saleList;
    public static volatile SingularAttribute<Users, String> mobile;

}