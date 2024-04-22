package com.scytalys.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.scytalys.model.Customer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class XmlParser {

    public static void main(String[] args) {
        //DESERIALIZATION
        String xml = """
                <customers>
                  <guest>      
                    <firstName>John</firstName> <lastName>Doe</lastName>     
                  </guest> 
                  <guest> 
                    <firstName>María</firstName> <lastName>García</lastName> 
                  </guest>    
                  <guest>
                    <firstName>Nikki</firstName> <lastName>Wolf</lastName>
                  </guest>
                </customers>
                """;

        Customer[] customers;

        XmlMapper xmlMapper = new XmlMapper();
        try {
            customers = xmlMapper.readValue(xml, Customer[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Arrays.stream(customers).forEach(System.out::println);


        //SERIALIZATION XML TO GUESTS, THEN EXPORT THE XML TO A FILE
        try {
            xmlMapper.writeValue(new File("data/customers.xml"), customers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper objectMapper =  new ObjectMapper();
        try {
            objectMapper.writeValue(new File("data/customers.json"), customers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
