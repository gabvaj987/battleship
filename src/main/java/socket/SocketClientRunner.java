package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientRunner {
	private final String host;
	private final Integer port;

	public SocketClientRunner(final String host, final Integer port) {
		this.host = host;
		this.port = port;
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
		new SocketClientRunner(host, port).run();
	}

	private void run() throws IOException, UnknownHostException {
		try (Socket socket = new Socket(host, port);
				PrintWriter pr = new PrintWriter(socket.getOutputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
			String read = stdIn.readLine();
			while (!read.equals("kill")) {
				pr.println(read);
				pr.flush();
				final String responded = br.readLine();
				System.out.println("client received:" + responded);
				read = stdIn.readLine();
			}
		}
	}
}
