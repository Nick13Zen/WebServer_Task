package main.java.com.epam.testWebServer.method;


import main.java.com.epam.testWebServer.bean.HttpRequest;
import main.java.com.epam.testWebServer.storage.BooksStorage;

/**
 * Created by Nick on 14/04/17.
 */
public class MethodDelete extends Method {

    private static final String METHOD_NAME = "DELETE";
    private static final String CONTENT_TYPE_ATTRIBUTE_NAME = "Content-type:";


    @Override
    public String getName() {
        return METHOD_NAME;
    }

    @Override
    public String executeMethod(HttpRequest request) {
        String respond = null;
        BooksStorage books = BooksStorage.getInstance();
        int bookId = Integer.parseInt(request.getUri()
                .substring(request.getUri().indexOf(ROOT_PATH_TEXT) + 1));
        books.deleteBook(bookId);
        respond = CODE_SUCCESS + "\r\n" +
                SERVER_TEXT + "\r\n" +
                CONTENT_TYPE_TEXT + request.getHeaders().get(CONTENT_TYPE_ATTRIBUTE_NAME) + "\r\n" +
                CONTENT_LENGTH_TEXT + books.toString().getBytes().length + "\r\n" +
                CONNECTION_KEEP_ALIVE_TEXT + "\r\n\r\n" + books.getBooks().toString();
        return respond;
    }
}
