package com.scytalys.products;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ProductRepository {

    private final ConcurrentHashMap<Integer, Product> products = new ConcurrentHashMap<Integer, Product>();

    public void add(Product product, int id) {
        products.put(id, product);
    }

    public Product create(int id, String name, ProductCategory productCategory) {
        Product product = null;
        switch (productCategory) {
            case DAIRY:
                product = new DiaryProduct();
                product.setId(id);
                product.setName(name);
                break;

            case FRAGILE:
                product = new FragileProduct();
                break;
            case MEAT:
                product = new MeatProduct();
                break;
            case PRODUCE:
                product = new ProduceProduct();
                break;
            default:
                System.out.println("Invalid product category");
        }
        return product;
    }

    public List<Product> getProducts() {
        return new ArrayList<Product>(products.values());
    }

    public Product getProduct(int id) {
        return products.get(id);
    }


}
