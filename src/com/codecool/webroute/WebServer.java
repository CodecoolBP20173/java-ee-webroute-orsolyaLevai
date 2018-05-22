package com.codecool.webroute;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class WebServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        Class handler = Class.forName("com.codecool.webroute.WebHandler");
        Object objHandler = handler.newInstance();

        server.createContext("/", (WebHandler) objHandler);
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
