package com.test;

import com.softtechlabs.selenquery.core.*;

/**
 * Created by MANISH on 22-10-2015.
 */
public class testclass {
    public static void main(String[] args) {





        // Creating Reusing the existing browser session
        SelenDriver driver = new Driver(SupportedBrowsers.Chrome, false, "chromedriver.exe");
        driver.get("file:///E:/softechlabs/coding/SelenQuery/demo%20app/SamplePage.html");

        //working with controls with shortcut coding - setting values
        driver.$("#fname").type("Manish"); // using id
        driver.$("textarea").type("Wakad, Pune, India"); //using tagname


        driver.$(".amt").should().haveValue("25.00");


    }
}
