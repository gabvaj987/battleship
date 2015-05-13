package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientRunner {
    public static void main(final String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", SocketServerRunner.PORT);
                PrintWriter pr = new PrintWriter(socket.getOutputStream())) {
            pr.println("hello!!");
        }
    }
}
