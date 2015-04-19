package app;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServer {

    public static void main(final String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            serverSocket.accept();

        }
    }

}
