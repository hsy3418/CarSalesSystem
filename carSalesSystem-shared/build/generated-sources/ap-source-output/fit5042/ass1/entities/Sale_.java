package fit5042.ass1.entities;

import fit5042.ass1.entities.Car;
import fit5042.ass1.entities.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-20T23:10:34")
@StaticMetamodel(Sale.class)
public class Sale_ { 

    public static volatile SingularAttribute<Sale, String> salesdate;
    public static volatile SingularAttribute<Sale, Integer> salenum;
    public static volatile SingularAttribute<Sale, Users> salespersonid;
    public static volatile SingularAttribute<Sale, Car> modelnum;
    public static volatile SingularAttribute<Sale, Users> custid;
    public static volatile SingularAttribute<Sale, Character> ifnotpaid;

}