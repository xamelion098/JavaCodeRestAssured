import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {
    String JsonBodyRequestSuccessful = "{\n" +
            "    \"email\": \"eve.holt@reqres.in\",\n" +
            "    \"password\": \"pistol\"\n" + "}";
    String JsonBodyRequestInSuccessful = "{\n" +
            "    \"email\": \"sydney@fife\"\n" +
            "}";

    @Test
    public void apiRegistrationTest() {

        given()
                .baseUri("https://reqres.in/")
                .contentType(ContentType.JSON)
                .body(JsonBodyRequestSuccessful)

                .when()
                .post("/api/register")

                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id" , equalTo (4), "token"
                        , equalTo ("QpwL5tke4Pnpja7X4"));
    }


    @Test
    public void apiFailRegistrationTest() {

        given()
                .baseUri("https://reqres.in/")
                .contentType(ContentType.JSON)
                .body(JsonBodyRequestInSuccessful)

                .when()
                .post("/api/register")

                .then()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("error", equalTo("Missing password"));
    }
    @Test
    public void usersEmailTest (){

        given()
                .baseUri("https://reqres.in/")
                .contentType(ContentType.JSON)

                .when()
                .get("/api/users?page=2")

                .then()
                .statusCode(200)
                .body("data.email",everyItem(endsWith("@reqres.in")));
    }
    @Test
    public void usersDeleteTest (){

        given()
                .baseUri("https://reqres.in/")
                .contentType(ContentType.JSON)

                .when()
                .delete("/api/users?page=2")

                .then()
                .statusCode(204);
    }


}
