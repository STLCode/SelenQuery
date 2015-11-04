package com.softtechlabs.selenquery.core;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by MANISH on 05-11-2015.
 */
public class Dropdown {
    private  boolean expect;
    private List<WebElement> elements;
    public Dropdown(boolean expect, List<WebElement> elements) {
        this.expect = expect;
        this.elements = elements;
    }

    public boolean Selected(String string){
        String actualValue = elements.get(0).getText();
       return Assertion.test(expect, string,actualValue, "Selected > " + string);

    }

/*
    public boolean Selected(List<String> string){
        String actualValue = elements.get(0).getText();
        Assertion.test(expect, actualValue, string, "Test for Class Name matching");

    }*/
}
