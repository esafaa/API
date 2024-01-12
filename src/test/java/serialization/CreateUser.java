package serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import requestBuilder.GorestRequestBuilder;

import static io.restassured.RestAssured.given;

public class CreateUser {


    @Test
    public void createGorestUser() throws JsonProcessingException {
        String url = "url";
        String token = "your token";

        // Create JSON body from Java (Serialization)
        GorestRequestBuilder reqBuilder= new GorestRequestBuilder();
        reqBuilder.setName("name");
        reqBuilder.setEmail("email");
        reqBuilder.setGender("female");
        reqBuilder.setStatus("active");

        //convert java object to readable format
        ObjectMapper objMapper= new ObjectMapper();
        String payload =objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reqBuilder);
        System.out.println(payload);


        Response resp = given()
                .header("Authorization", " bearer " + token)
                .contentType(ContentType.JSON)
                .body(reqBuilder)
                .when()
                .post(url);
        resp.prettyPrint();

    }
}
