package neopets.play;

import org.openqa.selenium.WebDriverException;

import neopets.pages.Page;
import neopets.pages.games.GamePage;

public class PlayGame {
	
	private final Class<? extends GamePage> game;
	
	public PlayGame(Class<? extends GamePage> game) {
		this.game = game;
	}
	
	public void play(String username, String password) throws Exception {
		GamePage gamePage = this.game.newInstance();
		((Page) gamePage).login(username, password);
		this.start(gamePage);
	}

	private void start(GamePage gamePage) throws Exception {
		while (true) {
			try {
				gamePage.play();
			} catch (WebDriverException e) {
			}
		}
	}

}
