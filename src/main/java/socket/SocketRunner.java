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
		while (result instanceof Answer) {
			final Answer answer = (Answer) result;
			final String answerCommand = answer.getCommand();
			pr.println(answerCommand);
			pr.flush();
			System.out.println("sent:" + answerCommand);
			if (answer.isLast) {
				break;
			}
			readLine = br.readLine();
			System.out.println("received:" + readLine);
			result = player.accept(readLine);
		}
	}

	protected T getPlayer() {
		return player;
	}
}
