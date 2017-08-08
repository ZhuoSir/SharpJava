package rest_assured;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 *
 * Created by sunny-chen on 2017/8/2.
 */
public class XmlTest {

    @Before
    public void init() {
        RestAssured.baseURI = "http://petstore.swagger.io/v2/pet";
//        RestAssured.port = 80;
    }

    @Test
    public void test1() {
        String url = "/findByStatus?status=available";
        RestAssured.get(url)
                .then().body("pets.Pet.find{ it.@id == 165 }.name", Matchers.equalTo("doggie"));
    }

    @Test
    public void test2() {
        String url = "/findByStatus?status=available";
//        RequestSpecification request = RestAssured.given().log().all().filter(new Filter() {
//            @Override
//            public Response filter(FilterableRequestSpecification requestSpec,
//                                   FilterableResponseSpecification responseSpec,
//                                   FilterContext ctx) {
//                return new ResponseBuilder().clone(ctx.next(requestSpec, responseSpec))
//                        .setHeader("Content-Type", ContentType.XML.toString()).build();
//            }
//        });

        ValidatableResponse response = RestAssured.get(url)
                .then().body("find { it.id == 9073703475169482807 }.name", Matchers.equalTo("doggie"));
        System.out.println(response);
    }

    @Test
    public void jsonTest02(){

        RestAssured.given().log().all().filter(new Filter() {
            public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification
                    responseSpec, FilterContext ctx) {
                return new ResponseBuilder().clone(ctx.next(requestSpec, responseSpec)).
                        setHeader("Content-Type", ContentType.JSON.toString()).build();
            }
        })
                .param("version",  "V2.0.0-test")
                .param("os", "android")
                .param("tel", "15000000001")
                .param("uid", "1110").param("username", "python8")
                .param("ts", "1501582494699")
                .param("sign", "6a5754144908a825c962406bb579c7ae")
                .param("access_token", "0bcc5969bccfccd66c8dbde43f72acca")
                .param("channel", "").accept("application/xml")
                .when()
                .post("https://uatapi.95303.com/User/Update_user")
                .then().log().all()
                .statusCode(200)
                .body("code", Matchers.equalTo(0));
    }

    @Test
    public void test03() {
        String url = "/findByStatus?status=available";
        String json = RestAssured.get(url).asString();

        List<Integer> Ids = JsonPath.from(json).get("id");
        System.out.println("id: " + Ids);

        List<String> status = JsonPath.from(json).get("status");
        System.out.println("status: " + status);
    }

    @Test
    public void test4() {
        String url = "http://ebeta.95303.com/Product/Pro_list";
        RestAssured.given().log().all().filter(new Filter() {
            public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification
                    responseSpec, FilterContext ctx) {
                return new ResponseBuilder().clone(ctx.next(requestSpec, responseSpec)).
                        setHeader("Content-Type", ContentType.JSON.toString()).build();
            }
        }).post(url).then().statusCode(200).log().all();
    }

    @Test
    public void testSendMsg() {
        String url = "http://ebeta.95303.com/User/Send_tel_identifying";
        RestAssured.given().param("tel", "18515422024")
                .param("type", 1)
//                .param("code", 2)
//                .param("phoneid", "uisdw129013102910")
                .accept("application/json")
                .when()
                .post(url).then().log().all();
    }

    @Test
    public void testXml() {
        String url = "http://10.0.0.80:9001/carchannel?comid=AEC16110001";
        final String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                "<CarModelQueryRequest>\n" +
                "\t<RequestHead>\n" +
                "\t\t<request_Type>01</request_Type>\n" +
                "\t\t<user></user>\n" +
                "\t\t<password></password>\n" +
                "\t\t<queryId></queryId>\n" +
                "\t\t<sourceSystemCode></sourceSystemCode>\n" +
                "\t\t<versionNo></versionNo>\n" +
                "\t\t<areaCode></areaCode>\n" +
                "\t\t<areaName></areaName>\n" +
                "\t\t<tradeTime>20151102092648</tradeTime>\n" +
                "\t\t<response_Type>01</response_Type>\n" +
                "\t\t<signData></signData>\n" +
                "\t</RequestHead>\n" +
                "\t<CarModelQueryRequestMain>\n" +
                "\t\t<Channel>\n" +
                "\t\t\t<channelCode>AEC16110001</channelCode>\n" +
                "\t\t\t<channelName></channelName>\n" +
                "\t\t\t<channelComCode></channelComCode>\n" +
                "\t\t\t<channelComName></channelComName>\n" +
                "\t\t\t<channelProductCode></channelProductCode>\n" +
                "\t\t\t<channelOperateCode></channelOperateCode>\n" +
                "\t\t\t<channelTradeCode>000001</channelTradeCode>\n" +
                "\t\t\t<channelTradeSerialNo>20151102092648</channelTradeSerialNo>\n" +
                "\t\t\t<channelRelationNo></channelRelationNo>\n" +
                "\t\t\t<channelTradeDate>20151102</channelTradeDate>\n" +
                "\t\t</Channel>\n" +
                "\t\t<!--请求标识-->\n" +
                "\t\t<requestId>VEH_0</requestId>\n" +
                "\t\t<!--请求类型-->\n" +
                "\t\t<productRequestType>F</productRequestType>\n" +
                "\t\t<!--业务类型-->\n" +
                "\t\t<serviceType>C</serviceType>\n" +
                "\t\t<!--分页类型-->\n" +
                "\t\t<pagingFlag>T</pagingFlag>\n" +
                "\t\t<!--页码-->\n" +
                "\t\t<pageNo>1</pageNo>\n" +
                "\t\t<!--每页显示数量-->\n" +
                "\t\t<pageSize>10</pageSize>\n" +
                "\t\t<!--车型名-->\n" +
                "\t\t<vehicleName>奔驰</vehicleName>\n" +
                "\t\t<!--品牌ID-->\n" +
                "\t\t<brandId></brandId>\n" +
                "\t\t<!--车系ID-->\n" +
                "\t\t<familyId></familyId>\n" +
                "\t\t<!--驱动型式-->\n" +
                "\t\t<gearboxType></gearboxType>\n" +
                "\t\t<!--发动机描述-->\n" +
                "\t\t<engineDesc></engineDesc>\n" +
                "\t</CarModelQueryRequestMain>\n" +
                "</CarModelQueryRequest>";

        RestAssured.given()
                .param("", xml).post(url).then().log().all();
    }
}
