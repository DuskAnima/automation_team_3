package cl.kibernumacademy.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cl.kibernumacademy.models.Product;

public class ProductManager {
    private final List<Product> products = new ArrayList<>();
    private int nextId = 1;

    public void addProduct(String name, String description, int price) {
        Product newProduct = new Product(nextId++, name, description, price);
        products.add(newProduct);
    }

    public void updateName(int id, String newName) {
        
    }

    public void updateDescription(int id, String newDescription) {

    }

    public void updatePrice(int id, int newPrice) {

    }

    public void deleteProduct(int id) {

    }

    private Product getProductById(int id) {
        return products.stream().
            filter(product -> product.getId() == id).
            findFirst().
            orElseThrow(() -> new IllegalArgumentException("Este producto no existe"));
    }


    public List<Product> getList() {
        return Collections.unmodifiableList(products);
    }
}
