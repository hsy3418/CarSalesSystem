/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.ass1.controllers;

//import fit5042.ass1.carCatelogue.CarFacade;
import fit5042.ass1.carsCatelogue.CarsCatelogue;
import fit5042.ass1.entities.Car;
import fit5042.ass1.entities.Sale;
import fit5042.ass1.entities.Users;
import fit5042.ass1.salesList.salesList;
import fit5042.ass1.usersList.UserList;
import fit5042.ass1.webclient.RestClient;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Heaven
 */
@ManagedBean
@Named(value = "carControler")
@SessionScoped
public class CarControler implements Serializable {

    @EJB
    private UserList userList;
    @EJB
    private salesList salesList;
    @EJB
    private CarsCatelogue carsCatelogue;
    private Car car = new Car();
    private int modelNum;
    private String modelname;
    private String make;
    private String type;
    private String queryMake;
    private String salesdate;

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

    public int getModelNum() {
        return modelNum;
    }

    public void setModelNum(int modelNum) {
        this.modelNum = modelNum;
    }

    public String getQueryMake() {
        return queryMake;
    }

    public void setQueryMake(String queryMake) {
        this.queryMake = queryMake;
    }

    /**
     * Creates a new instance of CarControler
     */
    public CarControler() {

        //modelNum = car.getModelnum();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String add() throws Exception {
        this.carsCatelogue.create(car);
        this.car = new Car();
        return "index";
    }

    public String add(Car newCar){
        try {
            this.carsCatelogue.create(newCar);
              FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "successful");
           RequestContext.getCurrentInstance().showMessageInDialog(message);
        } catch (Exception ex) {
              FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "something wrong");
           RequestContext.getCurrentInstance().showMessageInDialog(message);
            Logger.getLogger(CarControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this.car = new Car();
        return "addCarResult";
    }

    public List<Car> displayCarFromWeb() throws JSONException {
        List<Car> carList = new ArrayList();
        RestClient restClient = new RestClient();
        String response = restClient.getCarByMakeAsString(queryMake);
        JSONArray jsonCarArr = new JSONArray(response);
        for (int i = 0; i < jsonCarArr.length(); i++) {
            String carinfo = jsonCarArr.getString(i);
            JSONObject carObj = new JSONObject(carinfo);
            String vinC = carObj.getString("vin");
            String typeC = carObj.getString("type");
            String thumbnailC = carObj.getString("thumbnail");
            String previewurlC = carObj.getString("previewurl");
            String modelnameC = carObj.getString("modelname");
            String milesC = carObj.getString("miles");
            String makeC = carObj.getString("make");
            String instockStr = carObj.getString("instock");
            char instock = instockStr.charAt(0);
            String description = carObj.getString("description");
            String color = carObj.getString("colour");
            Car c = new Car();
            c.setVin(vinC);
            c.setType(typeC);
            c.setThumbnail(thumbnailC);
            c.setPreviewurl(previewurlC);
            c.setModelname(modelnameC);
            c.setMiles(milesC);
            c.setMake(makeC);
            c.setInstock(instock);
            c.setDescription(description);
            c.setColour(color);
            carList.add(c);
        }

        return carList;
    }

    public Car findCarByModelNum(int num) throws Exception {
        car = carsCatelogue.find(num);
        return car;
    }

    public List<Car> searchCar() throws Exception {
        List<Car> cars = new ArrayList();

        if (modelNum > 0) {
            car = carsCatelogue.find(modelNum);

            cars.add(car);
        } else if (modelname != null) {
            if (make != null && type != null) {
                cars = this.carsCatelogue.findByNameAndMakeAndType(modelname, make, type);
            } else if (make == null && type != null) {
                cars = this.carsCatelogue.findByNameAndType(modelname, type);
            } else if (make != null && type == null) {
                cars = this.carsCatelogue.findByNameAndMake(modelname, make);
            } else if (make == null && type == null) {
                cars = carsCatelogue.findByModelName(modelname);
            }
        } else if (modelname == null) {
            if (make != null && type != null) {
                cars = carsCatelogue.findByMakeAndType(make, type);
            } else if (make == null && type != null) {
                cars = carsCatelogue.findByType(type);

            } else if (make != null && type == null) {
                cars = this.carsCatelogue.findByMake(make);

            } else if (make == null && type == null) {
                cars = carsCatelogue.findAll();
            }
        } else {
            return cars;
        }
        return cars;

    }

    public String getSalesdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        salesdate = sdf.format(new java.util.Date());
        return salesdate;
    }

    public boolean checkPaid(Users customer) throws Exception {
        boolean flag = true;
        List<Sale> sales = salesList.findSaleByCustomer(customer);
        for (int i = 0; i < sales.size(); i++) {
            if (sales.get(i).getIfnotpaid() == 'Y') {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public String addSale() {
        try {
            HttpServletRequest request
                    = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String username = request.getRemoteUser();

            Users c = this.userList.findByUsername(username);
            char instock = car.getInstock();
            String stringInstock = String.valueOf(instock);
            if (stringInstock.equalsIgnoreCase("Y")) {
                if (!(checkPaid(c))) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "You have an unpaid sale");
                    //        int modelnum = car.getModelnum();
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                } else {
                    String ifPay = request.getParameter("addSaleForm:ifPaid");
                    char pay = ifPay.charAt(0);

                    //String salesIdForm = request.getParameter("addSaleForm:salesId");
                    int salesId = 3;//Integer.parseInt(salesIdForm);
                    Users sales = userList.find(salesId);
                    String salesDate = this.getSalesdate();
                    System.out.println(salesDate);
                    Sale s = new Sale(salesDate, pay, car, sales, c);

                    this.salesList.create(s);
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "Congraluations!You successfully purchased a car");
                    int modelnum = car.getModelnum();
                    carsCatelogue.Update(modelnum);
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                }
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "Sorry,this car is not instock");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
//        
        } catch (Exception ex) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "Something wrong happend");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            Logger.getLogger(CarControler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "buyCar";

    }

    public List<Car> findAll() throws Exception {
        return this.carsCatelogue.findAll();
    }

    public Car findById(int modelNum) throws Exception {
        return this.carsCatelogue.find(modelNum);
    }

    public void delete(Car car){
        try {
            this.carsCatelogue.remove(car);
        } catch (Exception ex) {
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "Something wrong with deleting");
           RequestContext.getCurrentInstance().showMessageInDialog(message);
           Logger.getLogger(UserControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String edit() {
        try {
            this.carsCatelogue.edit(this.car);
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "successful");
           RequestContext.getCurrentInstance().showMessageInDialog(message);
           
          
        } catch (Exception ex) {
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "something wrong");
           RequestContext.getCurrentInstance().showMessageInDialog(message);
            Logger.getLogger(CarControler.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "editCar";
    }

    public String edit(Car car) throws Exception {
        this.car = car;
        return "editCar";
    }

    public String viewCar(Car car) throws Exception {
        this.car = car;
        return "viewCar";
    }

    public String buyCar(Car car) throws Exception {
        this.car = car;
        return "buyCar";

    }

    public void submit() {
        System.out.println("submit");
    }
}
