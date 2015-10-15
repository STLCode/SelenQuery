package com.softtechlabs.selenquery.core;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelenElement implements WebElement {

    private WebElement element;
    private SelenWebDriver driver;
    private String name = "";


    public SelenElement(SelenWebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    public SelenElement(SelenWebDriver driver, String name, WebElement element) {
        this.driver = driver;
        this.element = element;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WebElement getWebElement() {
        return element;
    }


    public boolean Assert(boolean condition) {
        return condition;
    }

    public Should should() {
        return new Should(driver, this, true);
    }

    public Should shouldNot() {
        return new Should(driver, this, false);
    }

    public Should should(int timeoutInSec) {
        return new Should(driver, this, true, timeoutInSec);
    }

    public Should shouldNot(int timeoutInSec) {
        return new Should(driver, this, false, timeoutInSec);
    }

    public Select toSelect() {
        return (Select) element;
    }


    @Override
    public <X> X getScreenshotAs(OutputType<X> target)
            throws WebDriverException {
        return element.getScreenshotAs(target);
    }

    @Override
    public void click() {
        element.click();
    }


    public void dblClick() {
        //Actions action = new Actions(element.);

        //action.doubleClick(elm).perform();

    }

    @Override
    public void submit() {
        element.submit();
    }

    //@Override
    public SpecialKeys type(CharSequence... keysToSend) {
        element.sendKeys(keysToSend);
        return new SpecialKeys(element);
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        element.sendKeys(keysToSend);

    }

    @Override
    public void clear() {
        element.clear();
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return element.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();

    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public String getText() {
        return element.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }


}
