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



    //***************************************************************-//
    //--------           agregar producto      -----------------------//
    //***************************************************************-//

     //------------------- sin parametrizar    ---------------//
    @Test //------test 1----agregar 1er producto----- --------------------//
    void testAddProduct1()
    {   String  nombre ='Comida etapa 1';
        String  descripcion = "de 1 hasta 6 meses";
        int     precio = 19990;

        Product product = productManager.editProduct(nombre,descripcion,precio);
        assertNotNull(product, "El producto no puede ser nulo");
    }


    @Test //-------test 2 agregar 2do producto-----------------------//
    void testAddProduct2()
    {   String  nombre ='Comida etapa 2';
        String  descripcion = "de 6 hasta 18 meses";
        int     precio = 15990;

        Product product = productManager.editProduct(nombre,descripcion,precio);
        assertNotNull(product, "El producto no puede ser nulo");
    }


    @Test //-------test 3 agregar 3do producto-----------------------//
    void testAddProduct3()
    {   String  nombre ='Comida etapa 3';
        String  descripcion = "mayores de 18 meses";
        int     precio = 12990;

        Product product = productManager.editProduct(nombre,descripcion,precio);
        assertNotNull(product, "El producto no puede ser nulo");
    }
//-----------------fin agrear producto sin parametrizar --------------------//


//**---------agregar producto con pruebas parametrizadas ---------- */
@ParameterizedTest
    @CsvSource({ "Comida etapa 1, de 1 hasta 6 meses, 19990", "Comida etapa 2, de 6 hasta 18 meses, 15990",
     "Comida etapa 3, mayores de 18 meses, 12990"} )
    @DisplayName("AGREGAR PRODUCTOS")
    void testAgregarVarios(String = nombre, String = descripcion, int = precio) {
        Product p =library.addProduct(nombre,descripcion,precio);
        assertNotNull(p);
        assertEquals(nombre, p.getnombre());
    }
}




//-------------


/*

        //--------------actualizar producto -------------------//
        

        //------------eliminar producto -------------------//
         
 */
   

}
