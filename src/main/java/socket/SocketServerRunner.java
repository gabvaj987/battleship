package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import socket.SocketServer.Result;

/**
 * Connects ServerSocket to business logic SocketServer
 */
public class SocketServerRunner {

    public static final int PORT = 1234;

    public static void main(final String[] args) throws IOException {
        SocketServer socketServer = new SocketServer();

        try (ServerSocket serverSocket = new ServerSocket(PORT);
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pr = new PrintWriter(socket.getOutputStream())) {
            String readLine;
            Result answer = Result.CONTINUE;
            while (answer == Result.CONTINUE) {
                readLine = reader.readLine();
                System.out.println("server received:" + readLine);
                answer = socketServer.accept(readLine);
                pr.println(answer.toString());
                pr.flush();
            }
            System.out.println("server stops normally");
        } catch (IllegalArgumentException e) {
            System.out.println("server stops: wrong command received");
        }
    }
}
