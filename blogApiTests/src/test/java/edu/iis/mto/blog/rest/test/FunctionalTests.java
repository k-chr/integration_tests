package edu.iis.mto.blog.rest.test;

import org.junit.jupiter.api.BeforeAll;

import io.restassured.RestAssured;

public class FunctionalTests {

    static final String OPTION_JSON = "application/json;charset=UTF-8";
    static final String TYPE = "Content-Type";
    private static final String USER_API = "/user";
    private static final String ROOT_API = "/blog";
    private static final String USER_POST_API = "/post";
    private static final String POST_LIKE_API = "/like";

    static String postApiForId(int id) {
        return userApi() + "/" + id + USER_POST_API;
    }

    static String postApiForPostId(int postId) {
        return ROOT_API + USER_POST_API + '/' + postId;
    }

    static String userApi() {
        return ROOT_API + USER_API;
    }

    static String likeApiForUserIdAndPostId(int userId, int postId) {
        return userApi() + '/' + userId + POST_LIKE_API + '/' + postId;
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
