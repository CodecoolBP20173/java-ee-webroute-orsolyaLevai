package com.codecool.webroute;

import com.sun.net.httpserver.HttpExchange;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebRoutes {
    @WebRoute(path = "/")
    public static String routeIndex(HttpExchange requestData) {
        return "<h1>This is the index page</h1>";
    }

    @WebRoute(path = "/test")
    public static String routeTest(HttpExchange requestData) {
        return "<h1>This is the test page</h1>";
    }

    @WebRoute(method="POST", path = "/users")
    public static String routeGetUsers(HttpExchange requestData) {
        return "<h1>This is the users page with "+ requestData.getRequestMethod() +" request</h1>";
    }

    @WebRoute(path = "/users/<userName>")
    public static String routeGetUser(HttpExchange requestData) {
        String userName = "/[a-zA-Z]+";
        Pattern p = Pattern.compile("/[a-zA-Z]+$");
        Matcher m = p.matcher(requestData.getRequestURI().toString());
        m.find();

        return "<h1>This is the users page with "+ m.group().substring(1) +" request</h1>";
    }
}
