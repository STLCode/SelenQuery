package com.softtechlabs.selenquery.core;

import org.openqa.selenium.WebElement;

class Should {

	private int timeoutInSec =0;
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
		String txt = selenWebElement.getText();
		boolean res = Assertion.test(expect, txt.matches(regex), "Test for Text matching");
		if(!res){
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

	public void haveAttrVal(String attrName, String attrValue) {
		String attibValue = selenWebElement.getAttribute(attrName);
		Assertion.test(expect, attrValue, attibValue, "Test for Attribute value matching");

	}

	public void haveStyle(String styleProperty, String styleValue) {
		String val = selenWebElement.getCssValue(styleProperty);
		Assertion.test(expect, styleValue, val, "Test for css Style property matching");
	}

	public void displayed() {
		Assertion.test(expect,selenWebElement.isDisplayed(), "Test for elment displying"); 
	}

	public void enabled() {
		try {
			selenWebElement.getAttribute("readonly");
			Assertion.test(expect, true, "Readonly Test");
		} catch (Exception e) {
			Assertion.test(expect, false, "Readonly Test");
		}
	}
	
	
	public void focused() {
		WebElement elm = driver.executeScript("return document.activeElement");
	    if(elm != null && elm.equals(selenWebElement.getWebElement()))
	    {
	    	Assertion.test(expect,true, "Test for elment foucsed");
	    }
	    else{
	    	Assertion.test(expect,false, "Test for elment foucsed");
	    }
	}
	

	
}
