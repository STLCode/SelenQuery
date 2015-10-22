package com.test;

import com.softtechlabs.selenquery.core.Driver;
import com.softtechlabs.selenquery.core.SelenDriver;
import com.softtechlabs.selenquery.core.SupportedBrowsers;

/**
 * Created by MANISH on 22-10-2015.
 */
public class testclass {
    public static void main(String[] args) {

        SelenDriver driver = new Driver(SupportedBrowsers.Chrome, false, "chromedriver.exe");


    }
}
