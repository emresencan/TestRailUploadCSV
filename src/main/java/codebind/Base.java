package codebind;

import java.nio.charset.Charset;
import java.util.Locale;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class Base extends Library {

	public static String os = System.getProperty("os.name");

	public static final String USER_DIRECTORY = System.getProperty("user.dir");

	public static void driverSetUp() {
		int timeout = 15;

		String sysProperties = "";
		sysProperties += "Setup started";
		sysProperties += "\nfile.encoding: " + String.format("file.encoding: %s", System.getProperty("file.encoding"));
		sysProperties += "\ndefault charset=" + Charset.defaultCharset();
		sysProperties += "\njava.specification.version" + System.getProperty("java.specification.version");
		sysProperties += "\njava.runtime.version" + System.getProperty("java.runtime.version");
		sysProperties += "\nlocale default:" + Locale.getDefault();

//		System.setProperty("webdriver.chrome.driver",
//				USER_DIRECTORY + "\\src\\main\\resources\\driver\\chromedriver.exe");
//
//		Configuration.browserBinary = "C:\\TestRail\\testrailcsvimport\\src\\main\\java\\resources\\driver\\chromedriver.exe";
		Configuration.browser = "chrome";
		Configuration.driverManagerEnabled = true;
		Configuration.reportsFolder = "test-result/reports";
		Configuration.screenshots = Configuration.remote == null;
		Configuration.savePageSource = false;
		Configuration.holdBrowserOpen = false;
		Configuration.timeout = timeout * 1000;
		Configuration.startMaximized = true;
		Configuration.pollingInterval = 100;
		Configuration.headless = false;
//		WebDriverRunner.addListener(new WebDriverListener());
		WebDriverRunner.addListener(new DriverEventListener());

		sysProperties += "\nremote: " + Configuration.remote;
		sysProperties += "\nbrowser: " + Configuration.browser;
		sysProperties += "\nbrowser.version: " + Configuration.browserVersion;
		sysProperties += "\nurl: " + Configuration.baseUrl;
	}
}
