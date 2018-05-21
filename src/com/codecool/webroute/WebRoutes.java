package com.codecool.webroute;

import com.sun.net.httpserver.HttpExchange;

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
}
