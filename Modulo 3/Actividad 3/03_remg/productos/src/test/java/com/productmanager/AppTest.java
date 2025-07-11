package com.productmanager;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

 
public class ProductManagerTest 
{
    ProductManager productManager;
    {
        @beforeEach();
        void setup()
        {
            productManager = new ProductManager();        }
    }

    @Test
    void testAddProduct()
    {   String  nombre ='Comida para gatos etapa 1';
        String  descripcion = "Comida semi humeda para felinos etapa 1 de 3 hasta 6 meses.";
        int     precio = 12990;



    }

}
