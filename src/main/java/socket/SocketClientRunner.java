package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import player.Client;

public class SocketClientRunner extends SocketRunner<Client> {
	private final String host;
	private final Integer port;

	public SocketClientRunner(final Client client, final String host, final Integer port) {
		super(client);
		this.host = host;
		this.port = port;
	}

	@Override
	protected void run() throws IOException {
		try (Socket socket = new Socket(host, port);
				PrintWriter pr = new PrintWriter(socket.getOutputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			play(pr, br);
		}
		System.out.println("client stops normally");
	}

	public static void main(final String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("wrong number of parameters. Should be: <host> <port>");
			throw new IllegalArgumentException("wrong number of parameters");
		}
		final String host = args[0];
		Integer port;
		try {
			port = Integer.parseInt(args[1]);
		} catch (final NumberFormatException nfe) {
			System.out.println("port cannot be parsed");
			throw new IllegalArgumentException(nfe);
		}
		new SocketClientRunner(new Client(), host, port).run();
	}
}
