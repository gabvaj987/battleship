package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import player.Client;
import player.result.Answer;
import player.result.Result;

public class SocketClientRunner extends SocketRunner {
	private final Client client;
	private final String host;
	private final Integer port;

	public SocketClientRunner(final Client client, final String host, final Integer port) {
		this.client = client;
		this.host = host;
		this.port = port;
	}

	private void run() throws IOException, UnknownHostException {
		try (Socket socket = new Socket(host, port);
				PrintWriter pr = new PrintWriter(socket.getOutputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			Result result;
			String readLine = br.readLine();
			System.out.println("received: " + readLine);
			try {
				result = client.accept(readLine);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException("illegal command", e);
			}
			while (result instanceof Answer) {
				pr.println(((Answer) result).getCommand());
				pr.flush();
				readLine = br.readLine();
				System.out.println("received:" + readLine);
				try {
					result = client.accept(readLine);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException("illegal command", e);
				}
			}
		}
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
