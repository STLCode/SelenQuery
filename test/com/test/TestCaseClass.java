package com.test;

import com.softtechlabs.selenquery.core.Driver;
import com.softtechlabs.selenquery.core.Logger;
import com.softtechlabs.selenquery.core.SelenDriver;
import com.softtechlabs.selenquery.core.SupportedBrowsers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;

import java.awt.*;

/**
 * Created by MANISH on 04-11-2015.
 */
public class TestCaseClass {

     public SelenDriver driver;

    @Before
    public void createDriver() {
        Logger.setLogLevel("all");
        driver = new Driver(SupportedBrowsers.Chrome, false, "chromedriver.exe");
        driver.get("file:///E:/softechlabs/coding/SelenQuery/demo%20app/SamplePage.html");

    }
}
