package codebind;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.awt.*;

import javax.swing.*;

import org.openqa.selenium.By;

import com.codeborne.selenide.*;

public class LoginPageTestRail extends Library {

	private final SelenideElement TXT_USERNAME_ID = $(By.id("name"));
	private final SelenideElement TXT_PASSWWORD_ID = $(By.id("password"));
	private final SelenideElement BTN_LOGIN_ID = $(By.id("button_primary"));
	private final SelenideElement SINGLESIGNON = $(By.id("button_sso"));
	private final ElementsCollection TABLE_PROJECTNAMES_CSS = $$(
			".table.summary.summary-auto .row.project.flex-projects-row .summary-title.text-ppp");
	private final SelenideElement ANNAOUNCMENT_FORM = $("#announcementForm");
	private final SelenideElement BUTTON_CLOSE = $("#announcementForm button[type='submit']");
	private final SelenideElement ERROR_MSG = $(".loginpage-message-title ");

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
//			TXT_USERNAME_ID.sendKeys(username);
//			TXT_PASSWWORD_ID.sendKeys(password);
//			BTN_LOGIN_ID.click();
			SINGLESIGNON.click();
			if(ERROR_MSG.isDisplayed()){
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null,
						"Sorry, there was a problem.!!! " + "\n" +
								" - Email/Login or Password is incorrect. Please try again. -",
						"Error", -1);
				closeFail();
			}
			if (ANNAOUNCMENT_FORM.isDisplayed()) {
				BUTTON_CLOSE.click();
			}
		} catch (Exception e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null,
					"Something went wrong!!! \n - Please ensure You are on network or check your user name and password -",
					"Error", -1);
			closeFail();
		}
		return this;
	}
}
