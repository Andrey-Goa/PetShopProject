package ru.petproject.datamodel;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey-goa on 21.03.17.
 */

@XmlAccessorType (XmlAccessType.FIELD)
@XmlRootElement(name = "petshop")
public class PetShop {

    private Integer petshop_id ;
    private String storage;
    private String produck_name;
    private Double quantity;
    private Double price;



    public Integer getPetshop_id() {
        return petshop_id;
    }

    public String getStorage() {
        return storage;
    }

    public String getProduck_name() {
        return produck_name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }



    public void setPetshop_id(Integer petshop_id) {
        this.petshop_id = petshop_id;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public void setProduck_name(String produck_name) {
        this.produck_name = produck_name;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
