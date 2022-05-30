package neopets.pages.games;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import neopets.pages.Page;

public class PetpetBattlePage extends Page implements GamePage {

	public PetpetBattlePage() {
		super();
	}
	
	public PetpetBattlePage(WebDriver browser) {
		super(browser);
	}

	public void play() throws InterruptedException {
		this.mainPage();
		while (true) {
			try {
				List<WebElement> buttons = this.browser
						.findElements(By.cssSelector("tr td.content table tbody tr td p input"));
				if (buttons.isEmpty()) {
					this.browser.findElement(By.name("New_Game")).click();
				} else {
					this.clickRandomButton(buttons);
					Thread.sleep(1000l);
				}
			} catch (NoSuchElementException e) {
				this.mainPage();
			} catch (IndexOutOfBoundsException e) {

			}
		}
	}

	private void clickRandomButton(List<WebElement> botoes) {
		int get = (int) ((Math.random() * (botoes.size())));
		new WebDriverWait(this.browser, Duration.ofSeconds(10l)).until(ExpectedConditions.elementToBeClickable(botoes.get(get))).click();
	}

	private void mainPage() {
		this.browser.navigate().to("https://www.neopets.com/games/petpet_battle/ppb1.phtml");
	}

}
