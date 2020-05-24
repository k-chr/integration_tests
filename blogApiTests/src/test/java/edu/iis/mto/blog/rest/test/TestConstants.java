package edu.iis.mto.blog.rest.test;

public class TestConstants {

    public static final String OPTION_JSON = "application/json;charset=UTF-8";
    public static final String TYPE = "Content-Type";

    public static final String REMOVED_USER_SEARCH_STRING = "Cena";
    public static final String VALID_FULL_NAME = "John";
    public static final String VALID_PART_OF_NAME = "homas";
    public static final String INVALID = "XDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD";
    public static final String EMPTY = "";
    public static final String VALID_FULL_LAST_NAME = "Nameless";
    public static final String VALID_PART_OF_LAST_NAME = "teralus";
    public static final String VALID_FULL_EMAIL = "is_not_this_the_song_with_the_fibonacci_sequence_question_mark@domain.com";
    public static final String VALID_PART_OF_EMAIL = "@domain.com";

    public static final String GET_LIST = "stream().toList()";

    private static final String USER_API = "/user";
    private static final String ROOT_API = "/blog";
    private static final String USER_POST_API = "/post";
    private static final String POST_LIKE_API = "/like";
    private static final String FIND_USER_API = "/find?searchString=";

    public final static int FIRST_LIKER = 0x04;
    public final static int NEW_USER = 0x02;
    public final static int REMOVED_USER = 0x03;
    public final static int POST_OWNER = 0x01;
    public final static int USER_CONFIRMED = 0x07;
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

    public static String findUserByStringApi(String pattern){
        return userApi() + FIND_USER_API + pattern;
    }
}
