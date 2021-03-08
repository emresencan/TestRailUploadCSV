package codebind;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import javax.swing.*;
import java.nio.charset.Charset;
import java.util.Locale;

public class Base extends  Library {

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
        ClassLoader classLoader = Base.class.getClassLoader();
//        String path = classLoader.getResource("drivers/chromedriver.exe").getPath();
//        System.setProperty("webdriver.chrome.driver", path);
//        ChromeDriver driver = new ChromeDriver();

        Configuration.driverManagerEnabled = false;
        Configuration.reportsFolder = "test-result/reports";
        Configuration.screenshots = Configuration.remote == null;
        Configuration.savePageSource = false;
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = timeout * 1000;
        Configuration.startMaximized = true;
        Configuration.pollingInterval = 100;
        Configuration.headless = true;
        WebDriverRunner.addListener(new DriverEventListener());
//        WebDriverRunner.setWebDriver(driver);

        sysProperties += "\nremote: " + Configuration.remote;
        sysProperties += "\nbrowser: " + Configuration.browser;
        sysProperties += "\nbrowser.version: " + Configuration.browserVersion;
        sysProperties += "\nurl: " + Configuration.baseUrl;
    }
}
