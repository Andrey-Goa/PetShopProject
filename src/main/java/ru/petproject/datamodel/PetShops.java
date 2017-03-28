package ru.petproject.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey-goa on 22.03.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PetShops")
public class PetShops {

    @XmlElement(name = "petshop", type = PetShop.class)
    private List<PetShop> petshops = new ArrayList<PetShop>();

    public PetShops() {}

    public PetShops(List<PetShop> petshops) {
        this.petshops=petshops;
    }

    public void setPetshops(List<PetShop> petshops) {
        this.petshops = petshops;
    }

    public List<PetShop> getPetshops() {
        return petshops;
    }
}
