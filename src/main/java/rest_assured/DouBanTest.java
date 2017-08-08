package rest_assured;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by sunny-chen on 2017/7/31.
 */
public class DouBanTest {

    @Before
    public void init() {
        RestAssured.baseURI = "http://www.mini37.com/service/playerweb";
        RestAssured.port = 80;
//        RestAssured.port = 8080;
    }

    @Test
    public void testUrl() {
        String url = "/game/getSystemTime/";
        RestAssured.when().get(url)
                .then().assertThat().body("desc", equalTo("success"));
    }

    @Test
    public void test1() {
        RequestSpecification request = RestAssured.given().log().all().filter(new Filter() {
            @Override
            public Response filter(FilterableRequestSpecification requestSpec,
                                   FilterableResponseSpecification responseSpec,
                                   FilterContext ctx) {
                return new ResponseBuilder().clone(ctx.next(requestSpec, responseSpec))
                        .setHeader("Content-Type", ContentType.JSON.toString()).build();
            }
        });

        request.then().body("desc", equalTo("success"));
    }
}

