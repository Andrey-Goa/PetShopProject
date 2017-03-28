package ru.petproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.petproject.dao.PetShopMapper;
import ru.petproject.datamodel.PetShop;

import javax.annotation.PostConstruct;

/**
 * Created by andrey-goa on 22.03.17.
 */
@Component
@Scope(value = "singleton")
public class InitPetShop {

    @Autowired
    PetShopMapper petShopMapper;

   private PetShop p1 = new PetShop();
   private PetShop p2 = new PetShop();
   private PetShop p3 = new PetShop();

    @PostConstruct
    public void init() {
        p1.setProduck_name("Royal Canin BRITISH SHORTHAIR ADULT (В СОУСЕ) Британская короткошерстная");
        p1.setStorage("Склад 1");
        p1.setQuantity(12.0);
        p1.setPrice(74.0);
        p2.setProduck_name("Eukanuba Паучи для кошек старше 7 лет 85 г");
        p2.setStorage("Склад 2");
        p2.setQuantity(2.0);
        p2.setPrice(61.423);
        p3.setProduck_name("\n" +
                "TiTBiT Печенье Grain Free беззерновое с мясом ягненка 100г");
        p3.setStorage("Склад 3");
        p3.setQuantity(2.0);
        p3.setPrice(30.0);
        petShopMapper.createTableIfNotExist();
        petShopMapper.setPetShop(p1);
        petShopMapper.setPetShop(p2);
        petShopMapper.setPetShop(p3);
    }
}
