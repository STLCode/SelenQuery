package com.softtechlabs.selenquery.core;

import org.openqa.selenium.WebElement;

public class Should {

	private int timeoutInSec = 0;
	private boolean expect = true;
	private SelenElement selenWebElement = null;
	private SelenDriver driver = null;

	public Should(SelenDriver driver, SelenElement selenWebElement, boolean expect) {
		this.driver = driver;
		this.selenWebElement = selenWebElement;
		this.expect = expect;
	}

	public Should(SelenDriver driver, SelenElement selenWebElement, boolean expect, int timeoutInSec) {
		this.driver = driver;
		this.selenWebElement = selenWebElement;
		this.expect = expect;
		this.timeoutInSec = timeoutInSec;
	}

	public void haveText(String regex) {
		haveText(regex, true);
	}

	public void haveText(String regex, boolean caseSensitive) {
		String txt = selenWebElement.getText();
		if (!caseSensitive) {
			regex = regex.toLowerCase();
			txt = txt.toLowerCase();
		}

		boolean res = Assertion.test(expect, txt.matches(regex), "Test for Text matching");
		if (!res) {
			Assertion.printDetails(expect, regex, txt);
		}

	}

	public void haveClass(String className) {
		String actualClassName = selenWebElement.getAttribute("class");
		Assertion.test(expect, actualClassName, className, "Test for Class Name matching");

	}

	public void haveAttr(String attrName) {
		try {
			selenWebElement.getAttribute(attrName);
			Assertion.test(expect, true, "Test for Attribute availability");
		} catch (Exception e) {
			Assertion.test(expect, false, "Test for Attribute availability");
		}

	}

	public void haveValue(String value) {
		haveAttrVal("value", value, true);
	}

	public void haveValue(String value, boolean caseSensitive) {
		haveAttrVal("value", value, caseSensitive);
	}

	public void haveAttrVal(String attrName, String attrValue) {
		haveAttrVal(attrName, attrValue, true);
	}

	public void haveAttrVal(String attrName, String attrValue, boolean caseSensitive) {
		String attibValue = selenWebElement.getAttribute(attrName);
		if (!caseSensitive) {
			Assertion.test(expect, attrValue.toLowerCase(), attibValue.toLowerCase(), "Have Attribute value");
		} else {
			Assertion.test(expect, attrValue, attibValue, "Have Attribute value");
		}

	}

	public void haveStyle(String styleProperty, String styleValue) {
		String val = selenWebElement.getCssValue(styleProperty);
		Assertion.test(expect, styleValue.toLowerCase(), val.toLowerCase(), "Test for css Style property matching");
	}

	public void displayed() {
		Assertion.test(expect, selenWebElement.isDisplayed(), "Test for elment displying");
	}

	public void readonly() {
		String x = selenWebElement.getAttribute("readonly");
		if (x == null) {
			Assertion.test(expect, false, "readonly");
		} else if (x.equals("true")) {
			Assertion.test(expect, true, "readonly");
		}
	}

	public void disabled() {
		String x = selenWebElement.getAttribute("disabled");
		if (x == null) {
			Assertion.test(expect, false, "disabled");
		} else if (x.equals("true")) {
			Assertion.test(expect, true, "disabled");
		}

	}

	public void focused() {
		WebElement elm = driver.executeScript("return document.activeElement");
		if (elm != null && elm.equals(selenWebElement.getWebElement())) {
			Assertion.test(expect, true, "Test for elment foucsed");
		} else {
			Assertion.test(expect, false, "Test for elment foucsed");
		}
	}

	public void selected() {
		boolean selected = selenWebElement.isSelected();
		Assertion.test(expect, selected, "Selected > Having Value : " + selenWebElement.getAttribute("value"));
	}
}
