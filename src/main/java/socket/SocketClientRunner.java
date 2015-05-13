package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientRunner {
    public static void main(final String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", SocketServerRunner.PORT);
                PrintWriter pr = new PrintWriter(socket.getOutputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            String read = stdIn.readLine();
            while (!read.equals("kill")) {
                pr.println(read);
                pr.flush();
                String responded = br.readLine();
                System.out.println("client received:" + responded);
                read = stdIn.readLine();
            }
        }
    }
}
