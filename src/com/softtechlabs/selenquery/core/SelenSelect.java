package com.softtechlabs.selenquery.core;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by MANISH on 05-11-2015.
 */
public class SelenSelect extends Select {
    public SelenSelect(WebElement element) {
        super(element);
    }

    public Dropdown Should() {
        return new Dropdown(true, this.getAllSelectedOptions());
    }

    public Dropdown ShouldNot() {
        return new Dropdown(false, this.getAllSelectedOptions());
    }


}
