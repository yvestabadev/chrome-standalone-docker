package neopets.pages.shop;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import neopets.pages.Page;

public class ShopItemClassPage extends Page implements ShopPage{
	
	private String url;
	
	public ShopItemClassPage() {
		super();
	}
	
	public ShopItemClassPage(WebDriver browser) {
		super(browser);
	}

	public int howManyItemsForSale() {
		this.browser.navigate().to(this.url);
		List<WebElement> itemsForSale = this.browser.findElements(By.className("shop-item"));
		return itemsForSale.size();
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
}
