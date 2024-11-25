package codebind;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.events.WebDriverListener;

public class DriverEventListener implements WebDriverEventListener, WebDriverListener {

	private static final boolean log = true;

	@Override
	public void beforeAlertAccept(WebDriver driver) {

	}

	@Override
	public void afterAlertAccept(WebDriver driver) {

	}

	@Override
	public void afterAlertDismiss(WebDriver driver) {

	}

	@Override
	public void beforeAlertDismiss(WebDriver driver) {

	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {

	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {

	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {

	}

	@Override
	public void afterNavigateBack(WebDriver driver) {

	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {

	}

	@Override
	public void afterNavigateForward(WebDriver driver) {

	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {

	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {

	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		/*
		 * By loadingLocator = By.cssSelector("div[style*='display: block;'] .loading");
		 * long timeout = Configuration.timeout / 1000;
		 */
		waitMiliSeconds(250);
//		waitForLoadingJS(driver,120);
		return;

	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
//		waitForLoadingJS(driver,120);
		waitMiliSeconds(250);
		if (log) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			System.out.println(timestamp + "    found element: " + by.toString());
		}
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
//		waitForLoadingJS(driver,120);
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
//		waitForLoadingJS(driver,120);
		waitMiliSeconds(250);
		if (log) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			System.out.println(timestamp + "    clicked: " + element.toString());
		}
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
//		waitForLoadingJS(driver,120);
		if (log) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			System.out.println(timestamp + "    Change value: " + Arrays.toString(keysToSend) + "    element: "
					+ element.toString());
		}
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
//		waitForLoadingJS(driver,120);
		if (log) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			System.out.println(timestamp + "    value chaged: " + element.toString());
		}
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		if (log) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			System.out.println(timestamp + "    before script: " + script);
		}
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		if (log) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			System.out.println(timestamp + "    after script: " + script);
		}
	}

	@Override
	public void beforeSwitchToWindow(String windowName, WebDriver driver) {

	}

	@Override
	public void afterSwitchToWindow(String windowName, WebDriver driver) {

	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {

	}

	@Override
	public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

	}

	@Override
	public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

	}

	@Override
	public void beforeGetText(WebElement webElement, WebDriver webDriver) {
		// waitMiliSeconds(100);
	}

	@Override
	public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {

	}

	/*
	 * @Override public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {
	 *
	 * }
	 *
	 * @Override public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x)
	 * {
	 *
	 * }
	 */

	protected void waitMiliSeconds(int sleepTime) {
		try {
			System.out.println("Wait: " + sleepTime + " miliseconds.");
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}