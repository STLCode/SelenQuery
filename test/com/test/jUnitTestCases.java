package com.test;

import com.softtechlabs.selenquery.core.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by MANISH on 04-11-2015.
 */
public class jUnitTestCases extends TestCaseClass {



    //@Test
    public void loggerTest(){

        // inbuilt logger
        Logger.setLogLevel("info"); // set for only info
        Logger.setLogLevel("info,debug,warning,error"); // set for all
        //OR
        Logger.setLogLevel("all");

        Logger.setLogLevel("info,error"); // set for some

        Log.error("some error have come");
        Log.warning("this is a warning example"); // this will not print
        Log.info("exceuting method SendEmail"); // this will be printed in console

        int i = 10;
        Log.debug("printing the value of i : " + i); // this will not get printer
    }

    //@Test
    public void creatingDriver(){
        driver = new Driver(SupportedBrowsers.Chrome,false, "chromedriver.exe");
        driver.get("http://www.google.co.in");
       // driver.get("http://www.yahoo.co.in"); //nothing will happen
       // driver.get("http://www.yahoo.co.in",true);//navigate google to yahoo
       // driver.get("file:///E:/softechlabs/coding/SelenQuery/demo%20app/SamplePage.html",true); //lets get back to the business

    }

    //@Test
    public void ShortcutCoding() {
        //working with controls with shortcut coding - setting values
        driver.$("#fname").type("Manish"); // using id
        driver.$("textarea").type("Wakad, Pune, India"); //using tagname

        //working with radio button was never been so simple
        driver.radio("r1").SelectByValue("female");
        driver.radio("r1").SelectByAttr("value", "male");

        //finding element using attribute
        driver.attr("value","java").click();

        //Dropdown list
        driver.$("Select").toSelect().selectByVisibleText("America"); //Selection of text
        driver.$("Select").toSelect().selectByIndex(2);
        driver.$("Select").toSelect().selectByValue("ind");

        //Selecting a multiselect
        Select select = driver.$$("Select").get(1).toSelect();
        select.deselectAll();
        select.selectByVisibleText("Mango");
        select.selectByIndex(2);

        //Clicking buttons
        driver.$("input[type=Submit]").click();
        driver.$("button").click();
        driver.$("input[type=button]").click();


    }


    @Test
    public void validation(){
        driver.$("#fname").should().haveValue("Manish");
        driver.$(".amt").should().readonly();
        driver.$("#uid").should().disabled();
        driver.$("textarea").shouldNot().haveValue("");
        driver.radio("r1").Should("value", "male").selected();
        driver.radio("r1").ShouldNot("value", "female").selected();
        driver.attr("value","java").shouldNot().selected();
        driver.$("Select").toSelect().Should().Selected("India");

    }
}
