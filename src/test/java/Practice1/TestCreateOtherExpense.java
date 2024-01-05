package Practice1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import requestBuilder.OtherExpenseRequestBuilder;
import requestBuilder.TokenRequestBuilder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class TestCreateOtherExpense {
    String tokenURL="URL";
    String createExpenseURL="url";

    @Test
    public void createExpense() throws JsonProcessingException {

        //Create payload for Token api
        TokenRequestBuilder tokenPayload = new TokenRequestBuilder();
        tokenPayload.setUsernameOrEmailAddress("usename");
        tokenPayload.setPassword("password");

        // Convert payload to pretty format in String

        ObjectMapper objectMapper = new ObjectMapper();
        String strPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tokenPayload);
        System.out.println(strPayload);

        // Send Post request for Token api

        Response response = given()
                .body(strPayload)
                .contentType(ContentType.JSON)
                .when()
                .post(tokenURL);
        response.prettyPrint();

        //Retrieve TOKEN from response
       String token= JsonPath.read(response.asString(), "accessToken");

       // Create Payload for otherExpense Request

        OtherExpenseRequestBuilder reqBuilder= new OtherExpenseRequestBuilder();
        reqBuilder.setName("name");
        reqBuilder.setAmount("7.0");
        reqBuilder.setExpenseType("Other");
        reqBuilder.setOtherExpenseNameId("1");
        reqBuilder.setExpenseDateTime("11/02/2023 00:00:00");

        // Convert payload to readable format
        ObjectMapper objMapper= new ObjectMapper();
        String strExpense= objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objMapper);
// Submit a request whwre expense type == Other and otherExpenseNameID == 1
        Response response1= given()
                .header("Authorization", "Bearer "+token)
                .body( strExpense)
                .contentType(ContentType.JSON)
                .when()
                .post(createExpenseURL);
        response1.prettyPrint();
        // Validate status code is 200
        // response1.then().statusCode(200);
        int statusCode =response1.getStatusCode();
        assertEquals(200, statusCode);

        // Validate if value of "name" in response is "Electricity"
        String valueOfName=JsonPath.read(response1.asString(), "$.result.name");
        assertEquals("Electricity", valueOfName);

    }
}
