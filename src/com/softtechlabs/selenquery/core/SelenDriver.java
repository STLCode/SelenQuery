package com.softtechlabs.selenquery.core;

import java.util.HashMap;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public interface SelenDriver extends WebDriver {
    boolean exists(By by);

    boolean exists(By by, int TimeoutInSec);

    boolean exists(String selector);

    boolean exists(String selector, int TimeoutInSec);

    boolean isNewSession();

    int getGlobalTimeout();

    int getPageLoadTimeout();

    List<SelenElement> $$(By by);

    List<SelenElement> $$(By by, int TimeoutInSec);

    List<SelenElement> $$(String selector);

    List<SelenElement> $$(String selector, int TimeoutInSec);

    List<SelenElement> $$x(String xpath);

    List<SelenElement> $$x(String xpath, int TimeoutInSec);

    SelenRadioGroup radio(String name);

    SelenElement $(By by);

    SelenElement $(By by, int TimeoutInSec);

    SelenElement $(String selector);

    SelenElement $(String selector, int TimeoutInSec);

    SelenElement $x(String xpath);

    SelenElement $x(String xpath, int TimeoutInSec);

    SelenElement any(HashMap<String, Object> mapNameAndQueryString);

    SelenElement any(HashMap<String, Object> mapNameAndQueryString, int TimeoutInSec);

    SelenElement any(int TimeoutInSec, Object... queryString);

    SelenElement any(List<Object> listQueryString);

    SelenElement any(List<Object> listQueryString, int TimeoutInSec);

    SelenElement any(Object... queryString);

    SelenElement attr(String name, String value);

    SelenElement attr(String name, String value, int TimeoutInSec);

    SelenElement executeScript(String script);

    void get(String url, boolean force);

    void setGlobalTimeout(int TimeoutInSec);

    void setPageLoadTimeout(int TimeoutInSec);

    void shouldExists(By by);

    void shouldExists(By by, int TimeoutInSec);

    void shouldExists(String selector);

    void shouldExists(String selector, int TimeoutInSec);

    void shouldNotExists(By by);

    void shouldNotExists(By by, int TimeoutInSec);

    void shouldNotExists(String selector);

    void shouldNotExists(String selector, int TimeoutInSec);

    void sleep(long milliseconds);
}
