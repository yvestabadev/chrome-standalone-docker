package neopets.refresh;

import java.awt.AWTException;
import java.net.URISyntaxException;
import java.util.Date;

import org.openqa.selenium.WebDriverException;

import neopets.pages.Page;
import neopets.pages.shop.ShopPage;

public class ShopRefresher {

	private Integer numberofItems;
	private final Class<? extends ShopPage> classe;

	public ShopRefresher(Class<? extends ShopPage> classe) {
		this.classe = classe;
	}

	public void start(String url, String username, String password) throws InterruptedException, AWTException, InstantiationException,
			IllegalAccessException, URISyntaxException {
		while (true) {
			try {
				ShopPage page = classe.newInstance();
				((Page) page).login(username, password);
				page.setUrl(url);
				itemsAmountVerify(page);
			} catch (WebDriverException e) {

			}
		}
	}

	private void itemsAmountVerify(ShopPage page)
			throws AWTException, InterruptedException, URISyntaxException {
		while (true) {
			int items = page.howManyItemsForSale();
			if (this.numberofItems == null) {
				this.numberofItems = items;
			}
			if (items > numberofItems) {
				/*TODO kind of useless. The intention of this repository is to test
				standalone working, so you can implement here another way to notify*/
				System.out.println("The shop has been refreshed: " + new Date());
			}
			numberofItems = items;
			Thread.sleep(5000l);
		}
	}
}
