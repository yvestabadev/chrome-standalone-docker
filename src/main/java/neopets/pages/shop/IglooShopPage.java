package neopets.pages.shop;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import neopets.pages.Page;

public class IglooShopPage extends Page implements ShopPage{
	
	private static final String URL = "https://www.neopets.com/winter/igloo.phtml";

	public IglooShopPage() {
		super();
	}
	
	public IglooShopPage(WebDriver browser) {
		super(browser);
	}

	public int howManyItemsForSale() {
		this.browser.navigate().to(URL);
		this.browser.findElement(By.cssSelector("div#content table tbody tr td.content p a b")).click();
		List<WebElement> itemsForSale = this.browser.findElements(By.cssSelector("td.content form table tbody tr td a"));
		return itemsForSale.size();
	}
	
	public void setUrl(String url) {
		
	}
	
	public String getUrl() {
		return URL;
	}
}
