package api;

import pojo.cleanUri.ResultUrl;
import pojo.cleanUri.Urls;

import static io.restassured.RestAssured.given;

public class CleanUriApi extends Specifications {
    public static ResultUrl sendUri(Urls url) {
        return given()
                .when()
                .body(url)
                .post()
                .then()
                .extract().body().as(ResultUrl.class);
    }
}
