package com.codecool.webroute;

import com.sun.net.httpserver.HttpExchange;

public class WebRoutes {
    @WebRoute("/")
    public static void routeIndex(HttpExchange requestData) {
        // Here goes the code to handle all requests going to myserver.com/test
        // and to return something
        System.out.println("Index");
    }

    @WebRoute("/test")
    public static void routeTest(HttpExchange requestData) {
        // Here goes the code to handle all requests going to myserver.com/test
        // and to return something
        System.out.println("Test");
    }
}
