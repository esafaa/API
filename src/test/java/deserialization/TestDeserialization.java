package deserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ReusableMethods;

import javax.sound.midi.Soundbank;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.given;

public class TestDeserialization extends ReusableMethods {


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
        DashboardResponse dashObj= objMap.readValue(dashResponse.asString(), DashboardResponse.class  );

        System.out.println(dashObj.is__abp());
        System.out.println(dashObj.isSuccess());
        System.out.println(dashObj.isUnAuthorizedRequest());
        System.out.println(dashObj.getResultElement().getTargetUrl());


    }
}
