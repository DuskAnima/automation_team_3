package cl.kibernum.test;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import cl.kibernum.client.PostsClient;
import cl.kibernum.model.Post;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostCrudTest {
  
  private static PostsClient client;
  private static int createdId;
  private static final int USER_ID = 888;

  @BeforeAll
  static void setUp() {
    client = new PostsClient();
  }

  @Test
  @Order(1)
  @DisplayName("GET /posts - lista de todos los posts")
  void listPosts_ok() {
    Response response = client.listPosts();
    JsonPath json = response.jsonPath();
    List<Map<String, Object>> posts = json.getList("$");
    
    assertThat(posts, is(not(empty())));
    assertThat(response.getHeader("Content-Type"), 
              containsString("application/json"));
    
    Assertions.assertThat(posts).as("La lista de posts no debe ser vacia").isNotEmpty();
  }

  @Test
  @Order(2)
  @DisplayName("GET /post/{id} - obtiene un post existente")
  void getPost_ok() {
    Response response = client.getPost(1);
    JsonPath json = response.jsonPath();
    
    assertThat(json.getInt("id"), equalTo(1));
    assertThat(json.getString("title"), is(not(blankOrNullString())));
    assertThat(json.getMap(""), 
              allOf(hasKey("id"), hasKey("title"), hasKey("body"), hasKey("userId")));
  }

  @Test
  @Order(3)
  @DisplayName("POST /post - crear un post")
  void createPost_created() {
    String title = "Aprendiendo testing con Java";
    String body = "Rest assurance para automatización";
    Post payload = new Post(USER_ID, title, body);

    Response response = client.createPost(payload);
    JsonPath json = response.jsonPath();
    createdId = json.getInt("id");
    
    assertThat(json.getString("title"), equalTo(title));
    assertThat(json.getString("body"), equalTo(body));
    assertThat(json.getInt("userId"), equalTo(USER_ID));
    assertThat(createdId, greaterThan(0));
    
    assertThat(response.statusCode(), equalTo(201));
  }

  @Test
  @Order(4)
  @DisplayName("PUT /post/{id} - actualizar un post")
  void updatePost_ok() {
    int idToUpdate = 1;

    String title = "Aprendiendo testing con Java junto a los Javeros";
    String body = "Rest assurance para automatización. Tengo sueño, ngl";
    Post updatePost = new Post(USER_ID, title, body);

    Response response = client.updatedPost(idToUpdate, updatePost);
    JsonPath json = response.jsonPath();

    assertThat(json.getString("title"), equalTo(title));
    assertThat(json.getString("body"), equalTo(body));
    assertThat(json.getInt("userId"), equalTo(USER_ID));
    assertThat(json.getInt("id"), equalTo(idToUpdate));
    
    assertThat(response.statusCode(), equalTo(200));
  }

@Test
@Order(5)
@DisplayName("DELETE /post/{id} - elimina un post")
void deletePost_ok() {
    int idToDelete = (createdId > 0) ? createdId : 1;

    Response response = client.deletePost(idToDelete);
    int status = response.statusCode();

    assertThat(status, anyOf(equalTo(200), equalTo(204)));
    
    String responseBody = response.getBody().asString();
    assertThat(responseBody, anyOf(nullValue(), emptyString(), equalTo("{}")));
}
}