package main.java.com.epam.testWebServer;


import main.java.com.epam.testWebServer.bean.HttpRequest;
import main.java.com.epam.testWebServer.method.MethodHandler;
import main.java.com.epam.testWebServer.util.HttpRequestParser;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer extends Thread {

    public static void main(String args[]) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8081, 10, InetAddress.getByName("127.0.0.1"));
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new SocketPerformer(clientSocket)).start();
        }
    }

    private static class SocketPerformer implements Runnable {

        private Socket clientSocket;
        private InputStream inputStream;
        private OutputStream outputStream;

        private SocketPerformer(Socket connection) throws IOException {
            this.clientSocket = connection;
            this.inputStream = connection.getInputStream();
            this.outputStream = connection.getOutputStream();
        }

        @Override
        public void run() {
            try {
                HttpRequest request = HttpRequestParser.parse(inputStream);
                String response = MethodHandler.getInstance().executeMethod(request);
                writeResponse(response);
            } catch (IOException e) {

            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {

                }
            }
        }

        private void writeResponse(String response) throws IOException {
            outputStream.write(response.getBytes());
        }
    }
}