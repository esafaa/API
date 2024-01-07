package Practice1;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;
import org.junit.Test;

import utils.ReusableMethods;

import static org.junit.Assert.assertEquals;

public class TestCreateOtherExpense extends ReusableMethods {


    @Test
    public void createExpense() throws JsonProcessingException {


        String strPayload = createTokenPayload();

        String strExpense = createOtherExpensePayload("name", "99.55", "01/02/2024 00:00:00", "2");


        Response resp = createExpense(strExpense);

        // Validate status code is 200
        // resp.then().statusCode(200);

        int statusCode = resp.getStatusCode();
        assertEquals(200, statusCode);

        // Validate if value of "name" in response is "Electricity"


        String valueOfName = JsonPath.read(resp.asString(), "$.result.name");
        assertEquals("Electricity", valueOfName);

    }
}
