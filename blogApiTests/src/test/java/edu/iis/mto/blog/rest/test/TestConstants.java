package edu.iis.mto.blog.rest.test;

public class TestConstants {

    public static final String OPTION_JSON = "application/json;charset=UTF-8";
    public static final String TYPE = "Content-Type";

    private static final String USER_API = "/user";
    private static final String ROOT_API = "/blog";
    private static final String USER_POST_API = "/post";
    private static final String POST_LIKE_API = "/like";

    public final static int FIRST_LIKER = 0x04;
    public final static int NEW_USER = 0x02;
    public final static int REMOVED_USER = 0x03;
    public final static int POST_OWNER = 0x01;
    public final static int USER_CONFIRMED = POST_OWNER;
    public final static int SECOND_LIKER = 0x05;
    public final static int NOT_EXISTING_USER = 0xFFFFFFFF;
    public final static int ANONYMOUS_USER = 0x06;
    public final static int POST_ID = 0x01;
    public final static int POST_ID_2 = 0x02;
    public final static int POST_FOR_ANON = 0x03;

    public static String postApiForId(int id) {
        return userApi() + "/" + id + USER_POST_API;
    }

    public static String postApiForPostId(int postId) {
        return ROOT_API + USER_POST_API + '/' + postId;
    }

    public static String userApi() {
        return ROOT_API + USER_API;
    }

    public static String likeApiForUserIdAndPostId(int userId, int postId) {
        return userApi() + '/' + userId + POST_LIKE_API + '/' + postId;
    }
}
