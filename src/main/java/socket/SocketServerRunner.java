package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import player.Server;
import player.result.Answer;

/**
 * Connects ServerSocket to business logic SocketServer
 */
public class SocketServerRunner extends SocketRunner<Server> {

	private final Integer port;

	public SocketServerRunner(final Server server, final Integer port) {
		super(server);
		this.port = port;
	}

	@Override
	protected void run() throws IOException {
		try (final ServerSocket serverSocket = new ServerSocket(port);
				final Socket socket = serverSocket.accept();
				final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				final PrintWriter pr = new PrintWriter(socket.getOutputStream())) {
			final Server player = getPlayer();
			player.place();
			final Answer result = player.sayHello();
			final String greeting = result.getCommand();
			pr.println(greeting);
			pr.flush();
			System.out.println("sent: " + greeting);
			play(pr, reader);
		}
		System.out.println("server stops normally");
	}

	public static void main(final String[] args) throws IOException {
		if (args.length != 3) {
			System.out.println("wrong number of parameters. Should be: <port> <width> <height>");
			throw new IllegalArgumentException("wrong number of parameters");
		}
		Integer port;
		try {
			port = Integer.parseInt(args[0]);
		} catch (final NumberFormatException nfe) {
			System.out.println("port number cannot be parsed");
			throw new IllegalArgumentException(nfe);
		}
		Integer width;
		try {
			width = Integer.parseInt(args[1]);
		} catch (final NumberFormatException nfe) {
			System.out.println("width cannot be parsed");
			throw new IllegalArgumentException(nfe);
		}
		Integer height;
		try {
			height = Integer.parseInt(args[2]);
		} catch (final NumberFormatException nfe) {
			System.out.println("height number cannot be parsed");
			throw new IllegalArgumentException(nfe);
		}
		new SocketServerRunner(new Server(width, height), port).run();
	}
}
