package app;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import app.SocketServer.Result;

public class SocketClient {

    public static void main(final String[] args) throws UnknownHostException, IOException {
        try (Socket socket = new Socket("localhost", 1234)) {
            socket.getOutputStream();
        }
    }

	public Result accept(String command) {
		Result ret = null;
    	if(command.startsWith("ERROR")){
    		ret = Result.TERMINATE;
    	} else if (command.startsWith("HELLO")){
    		ret = Result.CONTINUE;
    	} else {
    		throw new IllegalArgumentException();
    	}
    	return ret;
	}
}
