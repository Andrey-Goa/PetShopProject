package ru.petproject.dao;

import ru.petproject.datamodel.PetShop;
import java.util.List;

/**
 * Created by andrey-goa on 21.03.17.
 */
public interface PetShopMapper {


    List<String> findAllProduct();

    List<PetShop> findAllPetshop();

    void createTableIfNotExist();

    void setPetShop(PetShop petShop);

    void updatePetShop(PetShop petshop);

    void deletePetshop(int petshop_id);
}
