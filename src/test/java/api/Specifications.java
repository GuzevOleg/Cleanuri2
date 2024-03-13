package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.ResultUrl;
import pojo.Urls;

import static io.restassured.RestAssured.given;

public class Specifications {

    public static final String BASE_URL = "https://cleanuri.com/";

    public static RequestSpecification reqSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .setBasePath("api/v1/shorten")
                .build();
    }

    public static ResponseSpecification repSpec(int code) {
        return new ResponseSpecBuilder()
                .expectStatusCode(code)
                .build();
    }

    public static void installSpec(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

    public static ResultUrl sendUri(Urls url, int code) {
        Specifications.installSpec(Specifications.reqSpec(), Specifications.repSpec(code));
        return given()
                .when().log().all()
                .body(url)
                .post()
                .then().log().all()
                .extract().body().as(ResultUrl.class);
    }
}
