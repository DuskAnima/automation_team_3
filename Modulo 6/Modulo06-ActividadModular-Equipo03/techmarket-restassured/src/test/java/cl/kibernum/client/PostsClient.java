package cl.kibernum.client;

import cl.kibernum.config.Specs;
import cl.kibernum.model.Post;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

// Clase que agrupa métodos para llamar a endpoints relacionados con "posts"
public class PostsClient {

  // Listar todos los posts
  public Response listPosts() {
    return given()
        .spec(Specs.request())
        .when()
        .get("/posts")
        .then()
        .spec(Specs.ok200())
        .extract().response();

  }

  // Obtener el detalle de un posts por su id

  public Response getPost(int id) {
    return given() // Iniciar la construccion de la petición
        .spec(Specs.request()) // Aplica la configuración baseUri, headers, content-type
        .when() // Indicamos que a continuación vamos ejecutar metodos http (get,post,put,delete)
        .get("/posts/{id}", id) // Realiza un GET hacia la ruta /posts https://jsonplaceholder.typicode.com/posts
        .then() // Inicia el proceso para validaciones sobre response que nos otorga el server (jsonplaceholder)
        .spec(Specs.ok200()) // Esperamos el codigo 200
        .extract().response(); // Extrae y devuelve la response para que nuestros test la puedan utilizar
  }

  // Crear un Posts

  public Response createPost(Post payload) {
    return given()
        .spec(Specs.request())
        .body(payload)
        .when()
        .post("/posts")
        .then()
        .spec(Specs.created201())
        .extract().response();
  }

  // Actualizar un Posts

  public Response updatedPost(int id, Post payload) {
    return given()
        .spec(Specs.request())
        .body(payload)
        .when()
        .put("/posts/{id}", id)
        .then()
        .spec(Specs.ok200())
        .extract().response();
  }

  // Eliminar un Posts

  public Response deletePost(int id) {
    return given()
        .spec(Specs.request())
        .when()
        .delete("/posts/{id}", id)
        .then()
        .spec(Specs.ok200())
        .extract().response();
  }
}