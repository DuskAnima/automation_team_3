package cl.kibernum.apirest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ProductoDTO {

    @NotBlank(message = "Nombre es requerido")
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank(message = "Descripción es requerida")
    @Size(min = 1, max = 256)
    private String description;

    @NotBlank(message = "Imagen es requerida")
    private String image;

    @NotNull(message = "Stock es requerido")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    private Integer stock;

    @NotNull(message = "Precio es requerido")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double price;

    private boolean active = true;

    // Getters y setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
