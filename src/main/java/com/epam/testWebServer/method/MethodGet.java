package main.java.com.epam.testWebServer.method;


import main.java.com.epam.testWebServer.bean.HttpRequest;
import main.java.com.epam.testWebServer.storage.BooksStorage;

/**
 * Created by Nick on 14/04/17.
 */
public class MethodGet extends Method {

    private static final String METHOD_NAME = "GET";
    private static final String CONTENT_TYPE_ATTRIBUTE_NAME = "Content-type:";
    protected static final String APPLICATION_XML_TEXT = "application/xml";
    protected static final String APPLICATION_JSON_TEXT = "application/json";


    @Override
    public String getName() {
        return METHOD_NAME;
    }

    @Override
    public String executeMethod(HttpRequest request) {
        String respond = null;
        BooksStorage books = BooksStorage.getInstance();
        if (request.getUri().equals("/")) {
            respond = CODE_SUCCESS + "\r\n" +
                    SERVER_TEXT + "\r\n" +
                    CONTENT_TYPE_TEXT + request.getHeaders().get(CONTENT_TYPE_ATTRIBUTE_NAME) + "\r\n" +
                    CONTENT_LENGTH_TEXT + books.toString().getBytes().length + "\r\n" +
                    CONNECTION_KEEP_ALIVE_TEXT + "\r\n\r\n" + books.getBooks().toString();
        } else {
            respond = CODE_SUCCESS + createRespond(respond, request, books);
        }
        return respond;
    }

    private String createRespond(String respond, HttpRequest request, BooksStorage books) {
        String contentType = request.getHeaders().get(CONTENT_TYPE_ATTRIBUTE_NAME);
        switch (contentType) {
            case APPLICATION_JSON_TEXT: {
                // TODO: 14/04/17
            }
            case APPLICATION_XML_TEXT: {
                //// TODO: 14/04/17
            }
        }

        return null;
    }
}
