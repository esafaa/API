package deserializationMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ReusableMethods;

import java.util.Set;

import static io.restassured.RestAssured.given;

public class TestDeserializationMAP extends ReusableMethods {

    @Test
    public void test() throws JsonProcessingException {

        String dashboardAPIUrl = "";

        Response dashResponse = given()
                .header("Authorization", "Bearer " + getToken())
                .when()
                .get(dashboardAPIUrl);

        dashResponse.prettyPrint();


        // map response to Java object (Deserialization)
        ObjectMapper objMap= new ObjectMapper();
        DashboardResponseMap dashObj= objMap.readValue(dashResponse.asString(), DashboardResponseMap.class);

        // read values as MAP
        System.out.println(dashObj.getResult());
        System.out.println(dashObj.getResult().keySet());
        System.out.println(dashObj.getResult().values());

        // Print as KEY and VALUE
        Set<String> elements= dashObj.getResult().keySet();
        for(String key:elements){
            System.out.println(key+": "+dashObj.getResult().get(key));
        }

        // Get value of one elements

        System.out.println(dashObj.getResult().get("giftExpenses"));



    }
}
