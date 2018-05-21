package com.codecool.webroute;

import com.sun.net.httpserver.HttpExchange;

public class WebRoutes {
    @WebRoute(route = "/")
    public static String routeIndex(HttpExchange requestData) {
        // Here goes the code to handle all requests going to myserver.com/test
        // and to return something
        return "Index";
    }

    @WebRoute(route = "/test")
    public static String routeTest(HttpExchange requestData) {
        return "Test";
    }
}
