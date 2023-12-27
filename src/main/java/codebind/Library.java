package codebind;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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

//	public void waitForLoadingJS(WebDriver driver, long timeoutSec) {
//		AtomicInteger isJsFinished = new AtomicInteger();
//		AtomicInteger isAjaxFinished = new AtomicInteger();
//		final int jsCompleteCheck = 3;
//		new WebDriverWait(driver, timeoutSec, 10).until(d -> {
//			JavascriptExecutor js = (JavascriptExecutor) d;
//			try {
//				if ((boolean) js.executeScript(
//						"return (document.readyState == \"complete\" || document.readyState == \"interactive\")"))
//					isJsFinished.set(jsCompleteCheck);
//				// Allure.addAttachment("Şu kadar süre loading beklendi: " + timeoutSec, "");
//			} catch (Exception e) {
//				isJsFinished.getAndIncrement();
//				// System.out.println("Load: isJsFinished error: " + e.getMessage());
//			}
//
//			try {
//				if ((boolean) js.executeScript(
//						"var result = true; try { result = (typeof jQuery != 'undefined') ? jQuery.active == 0 : true } catch (e) {}; return result;"))
//					isAjaxFinished.set(jsCompleteCheck);
//			} catch (Exception e) {
//				isAjaxFinished.getAndIncrement();
//			}
//
//			boolean loaderVisible = false;
//			loaderVisible = (boolean) js.executeScript(
//					"return (document.querySelectorAll(\"//div[@id='contentLoading' and @style = 'padding-top: 126px;']\").length > 0)");
//			// System.out.println("Load visible " + loaderVisible);
//			if (loaderVisible) {
//				isJsFinished.set(0);
//				isAjaxFinished.set(0);
//			}
//
//			// System.out.println("Load: isLoaderVisible error: " +
//			// Arrays.toString(e.getStackTrace()));
//
//			return isJsFinished.get() >= jsCompleteCheck && isAjaxFinished.get() >= jsCompleteCheck && !loaderVisible;
//
//		});
//	}
}
