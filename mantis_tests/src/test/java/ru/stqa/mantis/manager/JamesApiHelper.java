package ru.stqa.mantis.manager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.stqa.mantis.config.ConfigReader;

import java.io.ObjectInputFilter;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class JamesApiHelper extends HelperBase {
    public JamesApiHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password) {

        Response response =
            given()
                .header("Content-Type", "application/json")
                .body(String.format("{ \"password\": \"%s\" }",password))
            .when()
                .put(ConfigReader.buildApiUri(String.format("/users/%s",email)))
            .then()
                .statusCode(204)
                .extract().response();

    }
}
