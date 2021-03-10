package codebind;

import java.util.concurrent.TimeUnit;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

public class Library {

	public static void closeFail() {
		System.out.println("Close Webdriver");
		WebDriverRunner.getWebDriver().quit();
		System.exit(-1);
	}

	public static void waitSeconds(int sleepTime) {
		try {
			System.out.println("Wait: " + sleepTime + " seconds.");
			TimeUnit.SECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static void waitMiliSeconds(int sleepTime) {
		try {
			System.out.println("Wait: " + sleepTime + " miliseconds.");
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static void close() {
		System.out.println("Close Webdriver");
		WebDriverRunner.getWebDriver().quit();
		System.exit(0);
	}

	public void clearCookies() {
		try {
			Selenide.clearBrowserLocalStorage();
			Selenide.clearBrowserCookies();
		} catch (Exception e) {
			System.out.println("Error clearBrowserLocalStorage and clearBrowserCookies: " + e.getMessage());
		}
	}
}
