package com.scytalys.streams;

import com.scytalys.model.Customer;
import com.scytalys.model.CustomerCategory;

import java.util.List;
import java.util.Map;

public class FakeDataGenerator {

    public static void main(String[] args) {
        ProductStreamRepository productRepository = new ProductStreamRepository();
        productRepository.fillListWithData();
//        productRepository.printList();
//        System.out.println("------------------------------------------");

// SORTING
//        productRepository.sortProducts();
//        productRepository.printList();

//FILTERING
        //List<Product> products =  productRepository.getProductsAbovePriceLevel(5.0);
//        List<Product> products =  productRepository.getProductsStartsWith("H");
//        products.forEach(System.out::println);
//        for (Product product : products) {
//            System.out.println(product);
//        }

//MAPPING
//        List<String> descriptionsList = productRepository.getProductDescriptions();
//        descriptionsList.forEach(System.out::println);

//REDUCING (min,max,average,sum,count,standard deviation)
        //System.out.println("Average price: " + productRepository.getAveragePrice());
//
//        System.out.println("Count the ones above price level" + productRepository.countProductsAbovePriceLevel(50));

//MATCHING
//        System.out.println(productRepository.existsProduct("Mediocre Concrete Hat"));
//        System.out.println(productRepository.existsProduct("Shirt"));

//GROUPING
//        Map<Integer, List<Product>> map = productRepository.groupByQuantity();
//        map.forEach((quantity, group) -> {
//            System.out.println("Quantity: "+quantity);
//            System.out.println("Quantity: "+map);
//            System.out.println("---------------------------------");
//            System.out.println("Group: "+group);
//
//        });

//        CUSTOMER
        CustomerStreamRepository customerRepository = new CustomerStreamRepository();
        customerRepository.fillListWithData();
        customerRepository.printList();

//SORTING
//        System.out.println("---------------------------------------------------------------------------------");
//        customerRepository.sort();
//        customerRepository.printList();


//FILTERING
//        System.out.println("---------------------------------------------------------------------------------");
//        customerRepository.filterCustomerByBalance(500).forEach(System.out::println);

//MAPPING
        //        System.out.println("----------------------------Getting all addresses---------------------------------");
//        System.out.println(customerRepository.getAddresses());

//REDUCING (min,max,average,sum,count,standard deviation)
//        System.out.println("---------------------------------------------------------------------------------");
//        System.out.println("Max Balance: " + customerRepository.maxBalance());
//
//        System.out.println("---------------------------------------------------------------------------------");
//        System.out.println("Customers with registration date after 2024-01-01 is: "+customerRepository.countCustomersAfterRegDate(LocalDate.of(2024,01,01)));


//MATCHING
//        System.out.println("---------------------------------------------------------------------------------");
//        List<Customer> customers =         customerRepository.getCustomersStartsWithName("L");
//        customers.forEach(System.out::println);


//GROUPING
        System.out.println("---------------------------------------------------------------------------------");
        Map<CustomerCategory, List<Customer>> groupCustomersWithCategory = customerRepository.groupCustomersWithCategory();
        groupCustomersWithCategory.forEach((k, v) -> {
            System.out.println("*******************Category: " + k + "*************************");
            v.forEach(System.out::println);
        });

    }
}
