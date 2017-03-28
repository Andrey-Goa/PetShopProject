package ru.petproject.service;

import ru.petproject.datamodel.PetShop;
import ru.petproject.datamodel.PetShops;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by andrey-goa on 22.03.17.
 */
public class JaxbXML {
    public static String jaxbObjectToXML(PetShops petShops) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(PetShops.class);
            Marshaller mar = context.createMarshaller();

            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // To format XML
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            StringWriter sw = new StringWriter();
            mar.marshal(petShops,sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlString;
    }

    public static PetShop jaxbXMLToObject(String xmlData) {
        try {
            PetShop result = null;
            JAXBContext context = JAXBContext.newInstance(PetShop.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            StreamSource source = new StreamSource(new StringReader(xmlData));
            result = unmarshaller.unmarshal(source, PetShop.class).getValue();
            return result;
        } catch(JAXBException e) {
           e.printStackTrace();
        }
        return null;
    }
}
