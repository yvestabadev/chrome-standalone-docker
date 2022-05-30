package neopets.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private final WebDriver browser;

	public LoginPage(String username, String password, WebDriver browser) {
		this.browser = browser;

		this.logIn(username, password);
	}

	private void logIn(String username, String password) {
		this.browser.navigate().to("https://www.neopets.com/login/");
		this.browser.findElement(By.id("loginUsername")).sendKeys(username);
		this.browser.findElement(By.id("loginPassword")).sendKeys(password);
		this.browser.findElement(By.id("loginButton")).submit();
		if (this.browser.getCurrentUrl().contains("password")) {
			this.browser.quit();
			throw new RuntimeException("Invalid Credentials");
		}
	}

	public WebDriver getBrowser() {
		return browser;
	}

}
