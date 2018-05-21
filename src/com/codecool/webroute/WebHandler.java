package com.codecool.webroute;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WebHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        //String response = "This is the response";
        //String response = httpExchange.getRequestURI().toString();

        String response = getRequestedRoute(httpExchange);
        System.out.println(response);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    String getRequestedRoute(HttpExchange httpExchange) {
        String uri = httpExchange.getRequestURI().toString();
        try {
            for (Method method : Class.forName("com.codecool.webroute.WebRoutes")
                    .getMethods()) {

                if (method.isAnnotationPresent(WebRoute.class)) {
                    Annotation annotation = method.getAnnotation(WebRoute.class);
                    WebRoute webRoute = (WebRoute) annotation;
                    //System.out.println(m.getName());
                    if (webRoute.route().equals(uri)) {
                        //System.out.println(method.getName());

                        try {
                            Object returnValue = method.invoke(null, httpExchange);
                            System.out.println(returnValue);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*if (uri.equals("/")) System.out.println("index");
        else if (uri.equals("/test")) System.out.println("test");*/
        return "";
    }

}
