1.Crea un proyecto llamado ProductManager 
    • Este proyecto será un sistema que permita administrar productos: agregar, actualizar y eliminar productos desde una lista.

2.Implementa las pruebas utilizando el ciclo RED-GREEN-REFACTOR.

3.Funciones a implementar: 
• Agregar un producto:
        o Cada producto debe tener un nombre, una descripción y un precio. o Debe almacenarse correctamente en una lista de productos.
•    Actualizar un producto:
        o Permitir modificar el nombre, descripción o precio. o Validar que el producto exista antes de actualizarlo.
• Eliminar un producto:
        o Permitir eliminar un producto por su identificador único. o Verificar que exista antes de eliminarlo.

4.Implementa pruebas unitarias que incluyan: 
• Aserciones tradicionales de JUnit. 
• Aserciones con Hamcrest. 
• Uso de assumeTrue() o assumingThat(). 
• Pruebas parametrizadas. 
• Anotaciones @BeforeEach y @AfterEach.