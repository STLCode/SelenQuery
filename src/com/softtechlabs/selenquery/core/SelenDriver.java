package com.softtechlabs.selenquery.core;

import java.util.HashMap;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public interface SelenDriver extends WebDriver {

	SelenElement $(String selector);

	SelenElement $(By by);

	SelenElement $x(String xpath);

	SelenElement attr(String name, String value);

	List<SelenElement> $$(String selector);

	List<SelenElement> $$(By by);

	List<SelenElement> $$x(String xpath);

	boolean exists(String selector);

	boolean exists(By by);

	void shouldExists(String selector);

	void shouldNotExists(String selector);

	void shouldExists(By by);

	void shouldNotExists(By by);

	boolean isNewSession();

	void get(String url, boolean force);

	void sleep(long milliseconds);

	SelenElement any(Object... queryString);

	SelenElement any(HashMap<String, Object> mapNameAndQueryString);

	SelenElement any(List<Object> listQueryString);


	//With timeout
	SelenElement $(String selector, int TimeoutInSec);

	SelenElement $(By by, int TimeoutInSec);

	SelenElement $x(String xpath, int TimeoutInSec);

	List<SelenElement> $$(String selector, int TimeoutInSec);

	List<SelenElement> $$(By by, int TimeoutInSec);

	List<SelenElement> $$x(String xpath, int TimeoutInSec);

	boolean exists(String selector, int TimeoutInSec);

	boolean exists(By by, int TimeoutInSec);

	int getGlobalTimeout();

	void setGlobalTimeout(int TimeoutInSec);

	int getPageLoadTimeout();

	void setPageLoadTimeout(int TimeoutInSec);


	void shouldExists(String selector, int TimeoutInSec);

	void shouldNotExists(String selector, int TimeoutInSec);

	void shouldExists(By by, int TimeoutInSec);

	void shouldNotExists(By by, int TimeoutInSec);

	SelenElement any(int TimeoutInSec, Object... queryString);

	SelenElement any(HashMap<String, Object> mapNameAndQueryString, int TimeoutInSec);

	SelenElement any(List<Object> listQueryString, int TimeoutInSec);

	SelenElement executeScript(String script);

	SelenElement attr(String name, String value, int TimeoutInSec);

}
