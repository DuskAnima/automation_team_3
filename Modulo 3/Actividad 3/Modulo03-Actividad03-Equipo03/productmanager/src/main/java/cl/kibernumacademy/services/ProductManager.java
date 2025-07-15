package cl.kibernumacademy.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cl.kibernumacademy.models.Product;

public class ProductManager {
    private final List<Product> products = new ArrayList<>();
    private int nextId = 1;

    public void addProduct(String name, String description, double price) {
        if (name   == null || name.isBlank()) throw new IllegalArgumentException("Nombre inválido");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("Descripción inválida");
        if (price < 0)    throw new IllegalArgumentException("Precio inválido");
        Product newProduct = new Product(nextId++, name, description, price);
        products.add(newProduct);
    }

    public void updateName(int id, String newName) {
        if (newName   == null || newName.isBlank()) throw new IllegalArgumentException("Nombre inválido");
        Product product = getProductById(id);
        product.setName(newName);
    }

    public void updateDescription(int id, String newDescription) {
        if (newDescription == null || newDescription.isBlank()) throw new IllegalArgumentException("Descripción inválida");
        Product product = getProductById(id);
        product.setDescription(newDescription);
    }

    public void updatePrice(int id, double newPrice) {
        if (newPrice  < 0)    throw new IllegalArgumentException("Precio inválido");
        Product product = getProductById(id);
        product.setPrice(newPrice);
    }

    public void deleteProduct(int id) {
        Product product = getProductById(id);
        products.remove(product);
    }

    // Función privada encapsulada por ser de uso reiterativo. Previene cualquier retorno de objeto vacío
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
