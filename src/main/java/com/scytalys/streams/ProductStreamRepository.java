package com.scytalys.streams;

import com.github.javafaker.Faker;
import com.scytalys.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductStreamRepository {

    private final ArrayList<Product> products = new ArrayList<Product>();

    public void fillListWithData() {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            Product product = new Product();
            product.setId(i);
            product.setName(faker.commerce().productName());
            product.setPrice(faker.number().randomDouble(2, 1, 100));
            product.setDescription(faker.lorem().sentence());
            product.setQuantity(faker.number().numberBetween(1, 5));
            products.add(product);
        }

    }

    public void printList() {
        products.forEach(System.out::println);
    }

    public void sortProducts() {
//        products = products.stream()
//                        .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
//                                .collect(Collectors.toList());
//      Change the list of products to a List type
        products.sort((Comparator.comparingInt(Product::getQuantity).thenComparing(Comparator.comparingDouble(Product::getPrice).reversed())));

    }

    public List<Product> getProductsAbovePriceLevel(double priceLevel) {
        return products.stream()
                .filter(product -> product.getName() != null)
                .filter(product -> product.getPrice() > priceLevel)
                .collect(Collectors.toList());
    }

    public List<Product> getProductsStartsWith(String start) {
        return products.stream()
                .filter(product -> product.getName() != null)
                .filter(product -> product.getName().startsWith(start))
                .collect(Collectors.toList());
    }

    public List<String> getProductDescriptions() {
        return products.stream().map(Product::getDescription).collect(Collectors.toList());
    }

    public double getAveragePrice() {
//        return products.stream().map(Product::getPrice)
//                .reduce(0.0, Double::sum) / products.size();
        return products.stream().mapToDouble(Product::getPrice).average().orElse(0);

        // size may be 0 or price can be 0
    }

    public long countProductsAbovePriceLevel(double priceLevel) {
        return products.stream().filter(product -> product.getPrice() > priceLevel).count();
       // return getProductsAbovePriceLevel(priceLevel).size();
    }

    public boolean existsProduct(String productName) {
        return products.stream().anyMatch(product -> product.getName().equals(productName));
//        Contains the string
//        return products.stream().anyMatch(product -> product.getName().contains(productName));
    }

    public Map<Integer,List<Product>> groupByQuantity() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getQuantity));
    }

}
