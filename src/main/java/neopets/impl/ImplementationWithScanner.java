package neopets.impl;

import java.util.NoSuchElementException;
import java.util.Scanner;

import neopets.pages.games.PetpetBattlePage;
import neopets.pages.games.PyramidPage;
import neopets.pages.shop.IglooShopPage;
import neopets.pages.shop.ShopItemClassPage;
import neopets.play.PlayGame;
import neopets.refresh.ShopRefresher;

public class ImplementationWithScanner {

	public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		Scanner scanner = new Scanner(System.in);
		try {
			firstSelection(scanner);
		} catch (NoSuchElementException e) {
			System.out.println("Please digit only numbers. Try again");
			firstSelection(scanner);
		}
	}

	private static void firstSelection(Scanner scanner) {
		System.out.println("Choose an option: ");
		System.out.println("1 - Play a game");
		System.out.println("2 - Receive notifications from a shop");
		switch (scanner.nextInt()) {
		case 1:
			playGame(scanner);
		case 2:
			refreshShop(scanner);
		default:
			System.out.println("Please choose a valid option");
			firstSelection(scanner);
		}
	}

	private static void refreshShop(Scanner scanner) {
		System.out.println("Select a shop:");
		System.out.println("1 - Food");
		System.out.println("2 - Igloo Garage Sale");
		String shopUrl;
		ShopRefresher refresher;
		switch (scanner.nextInt()) {
		case 1:
			refresher = new ShopRefresher(ShopItemClassPage.class);
			shopUrl = "http://www.neopets.com/objects.phtml?obj_type=1&type=shop";
			askForCredentials(scanner, refresher, shopUrl);
		case 2:
			refresher = new ShopRefresher(IglooShopPage.class);
			shopUrl = "http://www.neopets.com/objects.phtml?obj_type=1&type=shop";
			askForCredentials(scanner, refresher, shopUrl);
		default:
			System.out.println("Please choose a valid option");
			refreshShop(scanner);
		}

	}

	private static void playGame(Scanner scanner) {
		System.out.println("Select a game:");
		System.out.println("1 - Petpet Battle");
		System.out.println("2 - Pyramids");
		PlayGame playGame;
		switch (scanner.nextInt()) {
		case 1:
			playGame = new PlayGame(PetpetBattlePage.class);
			askForCredentials(scanner, playGame);
		case 2:
			playGame = new PlayGame(PyramidPage.class);
			askForCredentials(scanner, playGame);
		default:
			System.out.println("Please choose a valid option");
			playGame(scanner);
		}
	}

	private static void askForCredentials(Scanner scanner, PlayGame playGame) {
		System.out.println("Type your username: ");
		String username = scanner.next();
		System.out.println("Type your password: ");
		String password = scanner.next();
		try {
			playGame.play(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Invalid credentials, please try again");
			firstSelection(scanner);
		}
	}

	private static void askForCredentials(Scanner scanner, ShopRefresher shopRefresher, String shopUrl) {
		System.out.println("Type your username: ");
		String username = scanner.next();
		System.out.println("Type your password: ");
		String password = scanner.next();
		try {
			shopRefresher.start(shopUrl, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Invalid credentials, please try again");
			firstSelection(scanner);
		}
	}

}
