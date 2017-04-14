package main.java.com.epam.testWebServer.method;


import main.java.com.epam.testWebServer.bean.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 14/04/17.
 */
public class MethodHandler {

    private List<Method> methods = new ArrayList<>();

    private static MethodHandler instance;

    {
        methods.add(new MethodGet());
    }

    private MethodHandler() {
    }

    public static MethodHandler getInstance() {
        if (instance == null) {
            instance = new MethodHandler();
        }
        return instance;
    }

    public String executeMethod(HttpRequest request) {
        String response = null;
        for (Method method : methods) {
            if (method.getName().equals(request.getMethod())) {
                response = method.executeMethod(request);
            }
        }
        return response;
    }
}
