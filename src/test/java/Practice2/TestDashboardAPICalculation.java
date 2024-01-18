package Practice2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import utils.ReusableMethods;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class TestDashboardAPICalculation extends ReusableMethods {


    @Test
    public void practice() throws JsonProcessingException {

        String token= getToken();
        String dashboardURL= "url";
        String getExpensesURL ="url";
        // Submit GET request to dashboard API

        Response respDashbaord= given()
                .header("Authorization","Bearer "+ token)
                .when()
                .get(dashboardURL);

        respDashbaord.prettyPrint();

        // Retrieve the value of otherExpenses, travelExpenses, giftExpenses from repsonse
        Double otherExpensesValue= JsonPath.read(respDashbaord.asString(), "$.result.otherExpenses");
        Double travelExpensesValue= JsonPath.read(respDashbaord.asString(), "$.result.travelExpenses");
        Double giftExpensesValue= JsonPath.read(respDashbaord.asString(), "$.result.giftExpenses");

        System.out.println("otherExpenses: "+ otherExpensesValue);
        System.out.println("travel Expenses: " +travelExpensesValue );
        System.out.println("gift Expenses: "+ giftExpensesValue);

        // Submit Get request to GetExpenses API
        Response respExpenses= given()
                .header("Authorization","Bearer "+ token)
                .relaxedHTTPSValidation()
                .when()
                .get(getExpensesURL);
        respExpenses.prettyPrint();

        // From response of GetExpenses API, get the total amount of all otherExpenses and
        //validate against the value from Dashboard API
        List<Double> otherExpensesList= JsonPath.read(respExpenses.asString(), "$.result[?(@.expenseType==10)].amount");
        Double totalOtherExpenses =0.0;
        for (Double otherExpense: otherExpensesList){
            totalOtherExpenses += otherExpense;
        }
        assertEquals(otherExpensesValue, totalOtherExpenses,0.01);

        //From response of GetExpenses API, get the total amount of all travelExpenses and
        //validate against the value from Dashboard API

        List<Double> travelExpensesList= JsonPath.read(respExpenses.asString(), "$.result[?(@.expenseType==40)].amount");
        Double totalTravelExpenses =0.0;
        for (Double travelExpense: travelExpensesList){
            totalTravelExpenses += travelExpense;
        }
        assertEquals(travelExpensesValue, totalTravelExpenses,0.01);


        //From response of GetExpenses API, get the total amount of all giftExpenses and
        //validate against the value from Dashboard API


        List<Double> giftExpensesList= JsonPath.read(respExpenses.asString(), "$.result[?(@.expenseType==50)].amount");
        Double totalgiftExpenses =0.0;
        for (Double giftExpense: giftExpensesList){
            totalgiftExpenses += giftExpense;
        }
        assertEquals(giftExpensesValue,  totalgiftExpenses,0.01);
    }
}
