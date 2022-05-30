package neopets.pages;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class Page {

	protected WebDriver browser;

    public Page(){
    }

    public Page(WebDriver browser){
        this.browser = browser;
    }
    
    public void login(String username, String password) {
    	LoginPage loginPage;
		try {
			loginPage = new LoginPage(username, password,
					new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), chromeOptions()));
		} catch (MalformedURLException e) {
			throw new RuntimeException("Internal Error");
		}
        this.browser = loginPage.getBrowser();
    }

    public void close(){
        this.browser.quit();
    }
    
	private ChromeOptions chromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.addArguments("--no-sandbox"); // Bypass OS security model
		return options;
	}
}
