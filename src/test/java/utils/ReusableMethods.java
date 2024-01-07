package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import requestBuilder.OtherExpenseRequestBuilder;
import requestBuilder.TokenRequestBuilder;

import static io.restassured.RestAssured.given;

public class ReusableMethods {
    private final String TOKENURL = "url";
    private final String EXPENSEURL = "url";

    private final String USERNAME="username";
    private final String PASSWORD="password";

public Response createExpense(Object request){
    Response resp= given()
            .header("Authorization","Bearer "+getToken())
                    .body(request)
                    .contentType(ContentType.JSON)
                    .when()
                    .post(EXPENSEURL);
            resp.prettyPrint();
            return resp;
}
    public String createOtherExpensePayload(String name, String amount, String date, String id) {
        OtherExpenseRequestBuilder requestBuilder = new OtherExpenseRequestBuilder();
        requestBuilder.setName(name);
        requestBuilder.setAmount(amount);
        requestBuilder.setExpenseDateTime(date);
        requestBuilder.setExpenseType("Other");
        requestBuilder.setOtherExpenseNameId(id);
        return convertObjectToString(requestBuilder);
    }

    public String getToken() {
        Response resp = given()
                .body(createTokenPayload())
                .contentType(ContentType.JSON)
                .when()
                .post(TOKENURL);
        resp.prettyPrint();
        return JsonPath.read(resp.asString(), "$.result.accessToken");
    }

    public String createTokenPayload() {
        TokenRequestBuilder tokenRequestBuilder = new TokenRequestBuilder();
        tokenRequestBuilder.setUsernameOrEmailAddress(USERNAME);
        tokenRequestBuilder.setPassword(PASSWORD);
        return convertObjectToString(tokenRequestBuilder);
    }

    public String convertObjectToString(Object request) {

        String strPayload = "";

        try {
            ObjectMapper objMapper = new ObjectMapper();
            return objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return strPayload;
    }


}
