package codebind;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import javax.swing.*;
import java.awt.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginPageTestRail extends Library {

    private final SelenideElement TXT_USERNAME_ID = $(By.id("name"));
    private final SelenideElement TXT_PASSWWORD_ID = $(By.id("password"));
    private final SelenideElement BTN_LOGIN_ID = $(By.id("button_primary"));
    private final ElementsCollection TABLE_PROJECTNAMES_CSS = $$(".table.summary.summary-auto .row.project.flex-projects-row .summary-title.text-ppp");


    private LoginPageTestRail open() {
        WebDriverRunner.clearBrowserCache();
        Selenide.open("");
        return this;
    }

    public LoginPageTestRail login(String username, String password) {
        try {
            Configuration.baseUrl = "https://testrail.swb.siemens.de";
            open();
            waitSeconds(1);
            TXT_USERNAME_ID.sendKeys(username);
            TXT_PASSWWORD_ID.sendKeys(password);
            BTN_LOGIN_ID.click();
            if (!TABLE_PROJECTNAMES_CSS.first().isDisplayed()) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Something went wrong!!! \n Please ensure You are on network or \n Check your user name and password.", "Error", -1);
                closeFail();
            }
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Something went wrong!!! \n - Please ensure You are on network or check your user name and password -", "Error", -1);
            closeFail();
        }
        return this;
    }
}
