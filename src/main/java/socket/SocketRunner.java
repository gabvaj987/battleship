package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import player.Player;
import player.result.Answer;
import player.result.Result;

public abstract class SocketRunner<T extends Player> {

	private final T player;

	public SocketRunner(final T player) {
		this.player = player;
	}

	protected abstract void run() throws IOException;

	protected void play(final PrintWriter pr, final BufferedReader br) throws IOException {
		String readLine = br.readLine();
		System.out.println("received:" + readLine);
		Result result = player.accept(readLine);
		while (result instanceof Answer || !result.isLast()) {
			if (result instanceof Answer) {
				for (final String command : ((Answer) result).getCommands()) {
					pr.println(command);
					System.out.println("sent:" + command);
				}
				pr.flush();
			}
			if (result.isLast()) {
				break;
			} else {
				readLine = br.readLine();
				System.out.println("received:" + readLine);
				result = player.accept(readLine);
			}
		}
	}

	protected T getPlayer() {
		return player;
	}
}
