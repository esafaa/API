package jsonSchema;

import io.restassured.response.Response;
import org.junit.Test;
import utils.ReusableMethods;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestDashboardJsonSchema extends ReusableMethods {

    @Test
    public void testDashboardJsonSchema(){

        String dashboardUrl= "url";
        Response response = given()
                .header("Authorization", "Bearer "+getToken())
                .when()
                .get(dashboardUrl);
        response.prettyPrint();

        //Validate Json Schema

        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/dashboardSchema.json"));



    }
}
