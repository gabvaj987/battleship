package app;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

    public static void main(final String[] args) throws UnknownHostException, IOException {
        try (Socket socket = new Socket("localhost", 1234)) {
            socket.getOutputStream();
        }
    }
}
