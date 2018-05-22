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
        String response = getRequestedRoute(httpExchange);

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
                    String webRoutePath = webRoute.path();

                    if (webRoutePath.equals(uri)) {
                        try {
                            Object returnValue = method.invoke(null, httpExchange);
                            return (String) returnValue;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } else if (uri.matches("/user/[a-zA-Z]+") && webRoutePath.matches("/user/\\<[a-zA-Z]+\\>")) {
                        Object returnValue = "";
                        try {
                            returnValue  = method.invoke(null, httpExchange);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        return (String) returnValue;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

}
