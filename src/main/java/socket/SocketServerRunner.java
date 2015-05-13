package socket;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServerRunner {

    public static void main(final String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            serverSocket.accept();

        }
    }

}
