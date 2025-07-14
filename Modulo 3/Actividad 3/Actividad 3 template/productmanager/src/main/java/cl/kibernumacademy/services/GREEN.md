package cl.kibernumacademy.services;

import java.util.ArrayList;
import java.util.List;

import cl.kibernumacademy.models.Product;

public class ProductManager {
    private final List<Product> products = new ArrayList<>();
    private int nextId = 1;

    public void addProduct(String name, String description, Double price) {
        Product newProduct = new Product(nextId++, name, description, price);
        products.add(newProduct);
    }

    public void updateName(int id, String newName) {
        Product product = getProductById(id);
        product.setName(newName);
    }

    public void updateDescription(int id, String newDescription) {
        Product product = getProductById(id);
        product.setDescription(newDescription);
    }

    public void updatePrice(int id, double newPrice) {
        Product product = getProductById(id);
        product.setPrice(newPrice);
    }

    public void deleteProduct(int id) {
        Product product = getProductById(id);
        products.remove(product);

    }

    private Product getProductById(int id) {
        return products.stream().
            filter(product -> product.getId() == id).
            findFirst().
            orElseThrow(() -> new IllegalArgumentException("Este producto no existe"));
    }


    public List<Product> getList() {
        return products;
    }
}
