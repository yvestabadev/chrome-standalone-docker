package neopets.pages.games;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import neopets.pages.Page;

public class PyramidPage extends Page implements GamePage {

	public PyramidPage() {
		super();
	}

	public PyramidPage(WebDriver browser) {
		super(browser);
	}

	public void play() throws InterruptedException {
		this.mainPage();
		while (true) {
			try {
				this.playCard();
			} catch (NoSuchElementException e) {
				this.mainPage();
			} catch(IndexOutOfBoundsException e) {
				this.mainPage();
			}
		}
	}

	public void mainPage() {
		this.browser.navigate().to("https://www.neopets.com/games/pyramids/index.phtml");
		this.browser.findElement(By.cssSelector("tr td.content div div div div form")).submit();
	}

	public void playCard() throws InterruptedException {
		Thread.sleep(3000l);
		List<WebElement> images = this.browser.findElements(By.cssSelector(".contentModule table tr td img"));
		WebElement baseImage = images.get(1);
		Integer baseImageValue = this.getImageValue(baseImage);
		boolean clicked = false;
		for (int i = 2; i < images.size(); i++) {
			try {
				Integer value1 = this.getImageValue(images.get(i)) - 1;
				if (value1.equals(1)) {
					value1 = 14;
				}
				Integer value2 = this.getImageValue(images.get(i)) + 1;
				if (value2.equals(15)) {
					value2 = 2;
				}
				if (value1 == baseImageValue || value2 == baseImageValue) {
					images.get(i).click();
					clicked = true;
					break;
				}
			} catch (Exception e) {
			}
		}
		if (!clicked) {
			images.get(0).click();
		}
		try {
			this.browser.findElement(By.cssSelector("td.content div div.frame p font b a b")).click();
		} catch (NoSuchElementException e) {
			// ignore
		}

	}

	private Integer getImageValue(WebElement imagem) {
		String[] split = imagem.getAttribute("src").split("/");
		String valueString = split[5].split("_")[0];
		return Integer.valueOf(valueString);
	}

}
