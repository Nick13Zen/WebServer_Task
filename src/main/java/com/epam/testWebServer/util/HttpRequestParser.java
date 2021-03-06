package main.java.com.epam.testWebServer.util;



import main.java.com.epam.testWebServer.bean.HttpRequest;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evgeny on 14.04.2017.
 */
public class HttpRequestParser {
    private static final String REQUEST_LINE_DELIMITER = "\\s";
    private static final String HEADER_DELIMITER = ":";
    private static final String NEW_LINE = "\n";

    public static HttpRequest parse(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        HttpRequest request = new HttpRequest();

        /* Parse first http request line */
        String requestLine = reader.readLine();
        String[] requestLineParams = requestLine.split(REQUEST_LINE_DELIMITER);
        request.setMethod(requestLineParams[0]);
        request.setUri(requestLineParams[1]);
        request.setVersion(requestLineParams[2]);

        /* Parse http request headers */
        Map<String, String> headers = new HashMap<>();
        String header = reader.readLine();
        String[] headerParams = null;
        String headerName = null;
        String headerValue = null;
        while (header.length() > 0) {
            headerParams = header.split(HEADER_DELIMITER);
            headerName = headerParams[0];
            headerValue = headerParams[1].substring(1);
            headers.put(headerName, headerValue);
            header = reader.readLine();
        }
        request.setHeaders(headers);

        /* Parse http request body */
        StringBuilder body = new StringBuilder();
        if (reader.ready()) {
            String bodyLine = reader.readLine();
            while (bodyLine != null) {
                if (bodyLine.isEmpty()) {
                    break;
                }
                body.append(bodyLine).append(NEW_LINE);
                bodyLine = reader.readLine();
            }
            request.setBody(body.toString());
        }

        return request;
    }
}