package edu.iis.mto.blog.rest.test;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;

public class FunctionalTests {

    static final String OPTION_JSON = "application/json;charset=UTF-8";
    static final String TYPE = "Content-Type";
    static final String USER_API = "/blog/user";
    static final String USER_POST_API = "/post";

    static String createPostApiForId(int id){
        return USER_API + "/" + id + USER_POST_API;
    }

    @BeforeAll
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        } else {
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if (basePath == null) {
            basePath = "";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
    }

}
