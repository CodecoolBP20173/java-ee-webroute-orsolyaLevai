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
    public void handle(HttpExchange t) throws IOException {
        //String response = "This is the response";
        String response = t.getRequestURI().toString();
        //System.out.println(response);
        getRequestedRoute(response);

        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    String getRequestedRoute(String uri) {
        try {
            for (Method m : Class.forName("com.codecool.webroute.WebRoutes")
                    .getMethods()) {

                if (m.isAnnotationPresent(WebRoute.class)) {
                    Annotation annotation = m.getAnnotation(WebRoute.class);
                    WebRoute webRoute = (WebRoute) annotation;
                    //System.out.println(m.getName());
                    if (webRoute.value().equals(uri)) {
                        System.out.println(m.getName());
                        /*try {
                            m.invoke(null);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }*/
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
