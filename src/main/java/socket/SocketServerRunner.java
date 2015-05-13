package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Connects ServerSocket to business logic SocketServer
 */
public class SocketServerRunner {

    public static final int PORT = 1234;

    public static void main(final String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT);
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            System.out.println(reader.readLine());
        }
    }
}
