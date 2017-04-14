package main.java.com.epam.testWebServer.method;


import main.java.com.epam.testWebServer.bean.HttpRequest;

public abstract class Method {

    protected static final String CODE_SUCCESS = "200 OK";
    protected static final String CODE_SERVER_ERROR = "500 server error";
    protected static final String ROOT_PATH_TEXT = "/";
    protected static final String SERVER_TEXT = "Server: MyServer/2017-04-12";
    protected static final String CONTENT_TYPE_TEXT = "Content-type: ";
    protected static final String CONTENT_LENGTH_TEXT = "Content-Length: ";
    protected static final String CONNECTION_KEEP_ALIVE_TEXT = "Connection: keep-alive";

    public abstract String getName();
    public abstract String executeMethod(HttpRequest request);
}
