package com.scytalys.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scytalys.model.Product;
import com.scytalys.model.ProductSupplier;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Eshop {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Logger");
        ProductSupplier productSupplier = ProductSupplier.builder()
                .id(1)
                .name("Supplier")
                .build();


        Product product = Product
                .builder() // It is a static method if it is called from the class
                //chain started
                .id(1)
                .price(12)
                .quantity(5)
                .name("chips")
                //.productSupplier(productSupplier)
                .build();

        //    productSupplier.setProduct(product);

        //System.out.println("Product name: " + product.getName() + " \nPrice: " + product.getPrice());
        //   System.out.println(product.toString());

        //Serialization TO JSON
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(product);

        } catch (JsonProcessingException e) {

            logger.log(Level.WARNING, e.getMessage());
            //JsonProcessingexception is a superclass of almost all exceptions with Json (subclass = JsonGenerationsException,JsonMappingException)
        }

        // **************************************
        System.out.println("Serializing from Object to JSON " + json);

        //multi-line string
        String productJson = """
                [ 
                 {
                     "id":1,
                     "name":"chips",
                     "price":12.0,
                     "quantity":5
                 },{
                     "id":2,
                     "name":"Choco",
                     "price":3.0,
                     "quantity":2
                 }
                 ]
                 """;

// **************************************
        //DESERIALIZATION
        Product[] product1 = null;
        try {
            product1 = mapper.readValue(productJson, Product[].class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        for (int i = 0; i < product1.length; i++) {
//            System.out.println("Deserialzing from JSON to Object "+ product1[i]);
//        }
//          The same way but simpler
// **************************************
//             LAMDA EXPRESSION

        Arrays.stream(product1).forEach(System.out::println);


    }
}
