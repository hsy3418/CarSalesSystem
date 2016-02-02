package fit5042.ass1.entities;

import fit5042.ass1.entities.Sale;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-20T23:10:34")
@StaticMetamodel(Car.class)
public class Car_ { 

    public static volatile SingularAttribute<Car, String> colour;
    public static volatile SingularAttribute<Car, String> modelname;
    public static volatile SingularAttribute<Car, String> thumbnail;
    public static volatile SingularAttribute<Car, String> previewurl;
    public static volatile SingularAttribute<Car, String> description;
    public static volatile SingularAttribute<Car, String> vin;
    public static volatile SingularAttribute<Car, String> miles;
    public static volatile SingularAttribute<Car, Character> instock;
    public static volatile SingularAttribute<Car, String> type;
    public static volatile SingularAttribute<Car, String> make;
    public static volatile ListAttribute<Car, Sale> saleList;
    public static volatile SingularAttribute<Car, Integer> modelnum;

}