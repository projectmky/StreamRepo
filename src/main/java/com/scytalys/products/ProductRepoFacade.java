package com.scytalys.products;

import java.util.List;

public class ProductRepoFacade {
    private ProductRepository repo;
    private int id = 0;

    public List<Product> getProducts() {
        return repo.getProducts();
    }


    public Product addProduct(String name, ProductCategory category) {
        id++;
        Product product = repo.create(id, name, category);
        repo.add(product, product.getId());
        return product;
    }

    public Product getProduct(int id) {
        return repo.getProduct(id);
    }


}
