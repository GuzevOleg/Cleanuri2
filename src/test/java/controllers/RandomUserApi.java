package controllers;

import pojo.randomUser.RandomUsersResult;

import static io.restassured.RestAssured.given;

public class RandomUserApi extends Specifications {
    public static RandomUsersResult sendUri() {
        return given()
                .when()
                .get()
                .then()
                .extract().body().as(RandomUsersResult.class);
    }

    public static RandomUsersResult sendUri(String key, String params) {
        return given()
                .when()
                .queryParam(key, params)
                .get()
                .then()
                .extract().body().as(RandomUsersResult.class);
    }

    public static RandomUsersResult sendUri(String key, String params, String key2, int params2) {
        return given()
                .when()
                .queryParam(key, params)
                .queryParam(key2, params2)
                .get()
                .then()
                .extract().body().as(RandomUsersResult.class);
    }

    public static RandomUsersResult sendUri(String version) {
        return given()
                .when()
                .get(version)
                .then()
                .extract().body().as(RandomUsersResult.class);
    }
}
