package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import player.Result;
import player.Server;

/**
 * Connects ServerSocket to business logic SocketServer
 */
public class SocketServerRunner {

    private final Server socketServer;
	private final Integer port;
	private final Integer width;
	private final Integer height;

	public SocketServerRunner(Server socketServer, Integer port, Integer width, Integer height) {
		this.socketServer = socketServer;
		this.port = port;
		this.width = width;
		this.height = height;
	}

	public void run() throws IOException{
		try (ServerSocket serverSocket = new ServerSocket(port);
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
	
	public static void main(final String[] args) throws IOException {
    	if(args.length!=3){
    		System.out.println("wrong number of parameters. Should be: <port> <width> <height>");
    		throw new IllegalArgumentException("wrong number of parameters");
    	}
    	Integer port;
    	try{
    		port = Integer.parseInt(args[0]);
    	} catch (NumberFormatException nfe){
    		System.out.println("port number cannot be parsed");
    		throw new IllegalArgumentException(nfe);
    	}
    	Integer width;
    	try{
    		width = Integer.parseInt(args[1]);
    	} catch (NumberFormatException nfe){
    		System.out.println("width cannot be parsed");
    		throw new IllegalArgumentException(nfe);
    	}
    	Integer height;
    	try{
    		height = Integer.parseInt(args[2]);
    	} catch (NumberFormatException nfe){
    		System.out.println("height number cannot be parsed");
    		throw new IllegalArgumentException(nfe);
    	}
        Server socketServer = new Server();
        new SocketServerRunner(socketServer,port,width,height);
    }
}
