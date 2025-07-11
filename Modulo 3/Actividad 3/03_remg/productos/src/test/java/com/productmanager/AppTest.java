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

    @Test //------test 1----agregar 1er producto----- ZONA RED --------------------//
    void testAddProduct1()
    {   String  nombre ='Comida para gatos etapa 1';
        String  descripcion = "Comida semi húmeda para felinos etapa 1 de 3 hasta 6 meses.";
        int     precio = 19990;

        Product product = productManager.editProduct(nombre,descripcion,precio);
        assertNotNull(product, "El producto no puede ser nulo");
        


    }


    @Test //-------test 2 agregar 2do producto-------- ZONA RED --------------------//
    void testAddProduct2()
    {   String  nombre ='Comida para gatos etapa 2';
        String  descripcion = "Comida 30% de húmedad para felinos etapa 2 de 6 hasta 18 meses.";
        int     precio = 15990;

        Product product = productManager.editProduct(nombre,descripcion,precio);
        assertNotNull(product, "El producto no puede ser nulo");
         


    }

    @Test //-------test 3 agregar 3do producto-------- ZONA RED --------------------//
    void testAddProduct3()
    {   String  nombre ='Comida para gatos etapa 3';
        String  descripcion = "Comida 10% de húmedad para felinos etapa 3 mayores de 18 meses.";
        int     precio = 12990;

        Product product = productManager.editProduct(nombre,descripcion,precio);
        assertNotNull(product, "El producto no puede ser nulo");
         


    }



        //--------agregar producto sin PARAMETRIZAR-----------------------//
       
       // assertEquals(nombre, product.getTitle());

            //**---------------esto es para las pruebas parametrizadas ---------- */
            /*
             * assertNotNull(product, "El producto no puede ser nulo");
               
             * 
             * 
             * 
             */

/*

        //--------------actualizar producto -------------------//
        Product product = productManager.editProduct(nombre,descripcion,precio);
        assertNotNull(product, "El producto no puede ser nulo");
        assertEquals(nombre, product.getTitle());

        //------------eliminar producto -------------------//
        Product product = productManager.deleteProduct(nombre,descripcion,precio);
        assertNotNull(product, "El producto no puede ser nulo");
        assertEquals(nombre, product.getTitle());
 */
   

}
