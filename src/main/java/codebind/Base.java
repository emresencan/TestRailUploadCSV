package codebind;

import java.nio.charset.Charset;
import java.util.Locale;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

public class Base extends Library {

	public static String os = System.getProperty("os.name");

	public static void driverSetUp() {
		int timeout = 15;

		String sysProperties = "";
		sysProperties += "Setup started";
		sysProperties += "\nfile.encoding: " + String.format("file.encoding: %s", System.getProperty("file.encoding"));
		sysProperties += "\ndefault charset=" + Charset.defaultCharset();
		sysProperties += "\njava.specification.version" + System.getProperty("java.specification.version");
		sysProperties += "\njava.runtime.version" + System.getProperty("java.runtime.version");
		sysProperties += "\nlocale default:" + Locale.getDefault();

		Configuration.browser = "chrome";
		Configuration.driverManagerEnabled = true;
		Configuration.reportsFolder = "test-result/reports";
		Configuration.screenshots = Configuration.remote == null;
		Configuration.savePageSource = false;
		Configuration.holdBrowserOpen = false;
		Configuration.timeout = timeout * 1000;
		Configuration.startMaximized = true;
		Configuration.pollingInterval = 100;
		Configuration.headless = true;
		WebDriverRunner.addListener(new DriverEventListener());

		sysProperties += "\nremote: " + Configuration.remote;
		sysProperties += "\nbrowser: " + Configuration.browser;
		sysProperties += "\nbrowser.version: " + Configuration.browserVersion;
		sysProperties += "\nurl: " + Configuration.baseUrl;
	}
}
