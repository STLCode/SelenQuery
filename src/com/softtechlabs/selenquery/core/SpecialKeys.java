package com.softtechlabs.selenquery.core;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

class SpecialKeys {
	WebElement element;
	public SpecialKeys(WebElement element) {
		this.element = element;
	}

	public void pressEnter(){
		this.element.sendKeys(Keys.ENTER);
	}

	public void pressTab(){
		this.element.sendKeys(Keys.TAB);
	}

	public void pressEsc(){
		this.element.sendKeys(Keys.ESCAPE);
	}
}
