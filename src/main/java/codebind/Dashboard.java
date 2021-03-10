package codebind;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import javax.swing.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class Dashboard extends Library {

	private final ElementsCollection TABLE_PROJECT_CSS = $$(
			".table.summary.summary-auto .row.project.flex-projects-row");
	private final ElementsCollection TABLE_PROJECTNAMES_CSS = $$(
			".table.summary.summary-auto .row.project.flex-projects-row .summary-title.text-ppp");
	private final ElementsCollection TABLE_SUITS_CSS = $$(".table .row.flex-suites-row .summary-title.text-ppp");
	private final ElementsCollection TABLE_SUITNAMES_CSS = $$(".table .row.flex-suites-row .summary-title.text-ppp a");
	private final ElementsCollection DRP_SUITS_CSS = $$("#importDropdown ul li");
	private final SelenideElement BTN_IMPORT_CSS = $(".icon-import");
	private final SelenideElement TXT_IMPORTCSVFILE_CSS = $("#importCsvFile");
	private final SelenideElement BTN_ADDSECTION_CSS = $("#addSection");
	private final SelenideElement TXT_editSectionName_CSS = $("#editSectionName");
	private final SelenideElement BTN_editSectionSubmit_CSS = $("#editSectionSubmit");
	private final SelenideElement DRP_importCsvImportTo_CSS = $("#importCsvImportTo");
	private final SelenideElement BTN_importCsvNext_CSS = $("#importCsvNext");
	private final SelenideElement BTN_importCsvLayoutMulti_CSS = $("#importCsvLayoutMulti");
	private final SelenideElement DRP_importCsvLayoutMultiBreak_CSS = $("#importCsvLayoutMultiBreak");
	private final ElementsCollection DRP_Fields_CSS = $$("#importCsvColumns tbody .mapping");
	private final ElementsCollection DRP_Fields_CSS2 = $$("#importCsvValues .mapping-list tbody .mapping");
	private final SelenideElement BTN_importCsvImport_CSS = $("#importCsvImport");
	private final SelenideElement BTN_importCsvClose_CSS = $("#importCsvClose");
	private final SelenideElement BTN_RETURNTOdASHBOARD_CSS = $("#navigation-dashboard-top");
	private final SelenideElement BTN_TESTSUITS_CSS = $("#navigation-suites");
	private final SelenideElement MSG_importCsvErrors_CSS = $("#importCsvErrors");

	public Dashboard findProjectAndClick(String projectName) {
		if (TABLE_PROJECT_CSS.first().isDisplayed()) {
			try {
				TABLE_PROJECT_CSS.filterBy(Condition.text(projectName)).first()
						.$(".summary-links.text-secondary a:nth-child(4)").click();
			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Something went wrong !!!", "Error", -1);
				closeFail();
			}
		} else {
			BTN_RETURNTOdASHBOARD_CSS.click();
			try {
				TABLE_PROJECT_CSS.filterBy(Condition.text(projectName)).first()
						.$(".summary-links.text-secondary a:nth-child(4)").click();
			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Something went wrong !!!", "Error", -1);
				closeFail();
			}
		}
		return this;
	}

	public List<String> findProjects() {
		List<String> names = null;
		try {
			ListIterator<SelenideElement> elemenet = null;
			names = new ArrayList<String>();
			elemenet = TABLE_PROJECTNAMES_CSS.listIterator();

			while (elemenet.hasNext()) {
				names.add(elemenet.next().getText());
			}
		} catch (Exception e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Something went wrong !!!", "Error", -1);
			closeFail();
		}
		return names;
	}

	public List<String> findTestSuits() {
		List<String> names = null;
		try {
			ListIterator<SelenideElement> elemenet = null;
			names = new ArrayList<String>();
			elemenet = TABLE_SUITNAMES_CSS.listIterator();

			while (elemenet.hasNext()) {
				names.add(elemenet.next().getText());
			}
		} catch (Exception e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Something went wrong !!!", "Error", -1);
			closeFail();
		}
		return names;
	}

	public Dashboard findSuitsAndClick(String suitName) {
		if (TABLE_SUITNAMES_CSS.first().isDisplayed()) {
			try {
				TABLE_SUITS_CSS.filterBy(Condition.text(suitName)).first().$("a").click();
			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Something went wrong !!!", "Error", -1);
				closeFail();
			}
		} else {
			BTN_TESTSUITS_CSS.click();
			try {
				TABLE_SUITS_CSS.filterBy(Condition.text(suitName)).first().$("a").click();
			} catch (Exception e) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Something went wrong !!!", "Error", -1);
				closeFail();
			}
		}
		return this;
	}

	public Dashboard addSection(String name) {
		try {
			BTN_ADDSECTION_CSS.click();
			TXT_editSectionName_CSS.sendKeys(name);
			BTN_editSectionSubmit_CSS.click();
		} catch (Exception e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Something went wrong !!!", "Error", -1);
			closeFail();
		}
		return this;
	}

	public Dashboard clickAndSelectImprot(String importType) {
		try {
			BTN_IMPORT_CSS.click();
			DRP_SUITS_CSS.filterBy(Condition.text(importType)).first().$("a").click();
		} catch (Exception e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Something went wrong !!!", "Error", -1);
			closeFail();
		}
		return this;
	}

	public Dashboard importCsv(String pathCSV) {
		try {
			TXT_IMPORTCSVFILE_CSS.sendKeys(pathCSV);
			waitSeconds(2);
			if ((DRP_importCsvImportTo_CSS.is(Condition.text("Functional Tests"))))
				DRP_importCsvImportTo_CSS.selectOption("Functional Tests");
			waitSeconds(1);
			BTN_importCsvNext_CSS.click();
			BTN_importCsvLayoutMulti_CSS.click();
			DRP_importCsvLayoutMultiBreak_CSS.selectOptionByValue("0");
			for (SelenideElement el : DRP_Fields_CSS) {
				String text = el.$("td:nth-child(1)").getText();

				if (text.equals("ï»¿Title"))
					text = "Title";
				else if (text.equals("Reviewer Status"))
					text = "Review Status";
				else if (text.equals("Steps"))
					text = "Steps (Step)";
				else if (text.equals("Expected Result"))
					text = "Steps (Expected Result)";
				el.$(".field select").click();

				boolean is = el.$(".field select").is(Condition.textCaseSensitive(text));
				if (is) {
					el.scrollIntoView(true);
					el.$(".field select").shouldBe(Condition.visible).selectOption(text);
				}

			}
			BTN_importCsvNext_CSS.click();
			waitSeconds(1);
			if (MSG_importCsvErrors_CSS.isDisplayed()) {
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null,
						"The Title field is a required field in TestRail and must be mapped to a CSV column.", "Error",
						-1);
				closeFail();
			}

			DRP_Fields_CSS2.first().scrollIntoView(true);
			for (SelenideElement el : DRP_Fields_CSS2) {
				String text = el.$("td:nth-child(1)").getText();
				boolean is = el.$(".field select").is(Condition.text(text));
				if (is) {
					waitMiliSeconds(300);
					el.$(".field select").selectOption(text);
				}

			}
			BTN_importCsvNext_CSS.click();
			waitSeconds(2);
			BTN_importCsvImport_CSS.click();
			BTN_importCsvClose_CSS.click();
		} catch (NoSuchElementException e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Something went wrong !!!", "Error", -1);
			closeFail();
		}
		return this;
	}
}
