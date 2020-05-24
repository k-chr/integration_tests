package edu.iis.mto.blog.domain;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ExceptionMatcher extends TypeSafeMatcher<Exception> {

    private String message;

    private ExceptionMatcher(String message) {

        this.message = message;
    }

    public static Matcher<Exception> hasMessage(String message) {
        return new ExceptionMatcher(message);
    }

    @Override
    protected boolean matchesSafely(Exception e) {
        return e.getMessage().equals(message);
    }

    @Override
    public void describeTo(Description description) {

    }
}
