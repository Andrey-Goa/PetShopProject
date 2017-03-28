package ru.petproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.petproject.dao.PetShopMapper;
import ru.petproject.datamodel.PetShop;
import ru.petproject.datamodel.PetShops;
import ru.petproject.service.JaxbXML;

import java.util.List;

/**
 * Created by andrey-goa on 21.03.17.
 */
@Controller
public class HelloController {

    @Autowired
    private PetShopMapper petShopMapper;

    String success = "<success>true</success>";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHello() {
        return "resources/hello.html";

    }

    @RequestMapping(value = "/getXML", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String getXML() {
        List<PetShop> list2 = petShopMapper.findAllPetshop();
        PetShops petShops = new PetShops();
        petShops.setPetshops(list2);
        return JaxbXML.jaxbObjectToXML(petShops);
    }

    @RequestMapping(value = "/controller/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String  delete(@RequestBody String data) throws Exception {
        if(!data.isEmpty()) {
           Integer petshop_id =  Integer.parseInt(data);
           petShopMapper.deletePetshop(petshop_id);
        }
        return success;

}
    @RequestMapping(value = "/controller/new", method = RequestMethod.POST)
    @ResponseBody
    public String create(@RequestBody String data) {
        PetShop petShop = JaxbXML.jaxbXMLToObject(data);
        if(petShop.getPetshop_id() == null){
            petShopMapper.setPetShop(petShop);
       }else{
            petShopMapper.updatePetShop(petShop);
        }
        return success;

    }
}
