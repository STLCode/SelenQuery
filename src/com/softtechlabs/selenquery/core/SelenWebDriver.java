package com.softtechlabs.selenquery.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.util.concurrent.UncheckedTimeoutException;

class SelenWebDriver extends ReusableRemoteWebDriver implements WebDriver, SelenDriver {

	private int globalTimeOut = 30;
	private int pageloadTimeout = 120;

	SelenWebDriver(SupportedBrowsers browsers, DesiredCapabilities capabilities, boolean NewSession, String driverPath, String host) {
		super(browsers, capabilities, NewSession, driverPath, host);
		this.manage().timeouts().pageLoadTimeout(globalTimeOut, TimeUnit.SECONDS);
		this.manage().timeouts().implicitlyWait(pageloadTimeout, TimeUnit.SECONDS);

	}

	@Override
	public SelenElement $(String selector) {
		return $(selector, globalTimeOut);
	}

	@Override
	public SelenElement $(By by) {
		return $(by, globalTimeOut);
	}

	@Override
	public SelenElement $x(String xpath) {
		return $x(xpath, globalTimeOut);
	}

	@Override
	public List<SelenElement> $$(String selector) {
		return $$(selector, globalTimeOut);
	}

	@Override
	public List<SelenElement> $$(By by) {
		return $$(by, globalTimeOut);
	}

	@Override
	public List<SelenElement> $$x(String xpath) {
		return $$x(xpath, globalTimeOut);
	}

	@Override
	public boolean exists(String selector) {
		return exists(selector, globalTimeOut);
	}

	@Override
	public boolean exists(By by) {
		return exists(by, globalTimeOut);
	}

	@Override
	public boolean exists(String selector, int timeoutInSec) {
		try {
			$(selector,timeoutInSec);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean exists(By by, int timeoutInSec) {
		try {
			setTimeout(timeoutInSec);
			$(by);
			resetTimeout();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public SelenElement $(String selector, int timeoutInSec) {
		return $$(selector, timeoutInSec).get(0);
	}

	@Override
	public SelenElement $(By by, int timeoutInSec) {
		return $$(by, timeoutInSec).get(0);
	}

	@Override
	public SelenElement $x(String xpath, int timeoutInSec) {
		return $$x(xpath, timeoutInSec).get(0);
	}

	@Override
	public List<SelenElement> $$(String selector, int timeoutInSec) {
		return findElement("SELECTOR", selector, timeoutInSec, null);
	}

	@Override
	public List<SelenElement> $$(By by, int timeoutInSec) {
		return findElement("BY", null, timeoutInSec, by);
	}

	@Override
	public List<SelenElement> $$x(String xpath, int timeoutInSec) {
		return findElement("XPATH", xpath, timeoutInSec, null);
	}

	@Override
	public int getGlobalTimeout() {
		return globalTimeOut;
	}

	@Override
	public void setGlobalTimeout(int timeoutInSec) {
		try {
			this.globalTimeOut = timeoutInSec;
			this.manage().timeouts().implicitlyWait(timeoutInSec, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("Driver is null - " + e.getMessage());
		}

	}

	@Override
	public int getPageLoadTimeout() {
		return pageloadTimeout;
	}

	@Override
	public void setPageLoadTimeout(int timeoutInSec) {
		try {
			this.pageloadTimeout = timeoutInSec;
			this.manage().timeouts().pageLoadTimeout(timeoutInSec, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("Driver is null - " + e.getMessage());
		}

	}

	@Override
	public void shouldExists(String selector) {
		if (!Assertion.test(true, exists(selector))) {
			System.err.println("Element Not Found - " + selector);
			sleep(100);
		}

	}

	@Override
	public void shouldNotExists(String selector) {
		if (!Assertion.test(false, exists(selector))) {
			System.err.println("Element Found - " + selector);
			sleep(100);
		}
	}

	@Override
	public void shouldExists(By by) {
		if (!Assertion.test(true, exists(by))) {
			System.err.println("Element Not Found - " + by.toString());
			sleep(100);
		}
	}

	@Override
	public void shouldNotExists(By by) {
		if (!Assertion.test(false, exists(by))) {
			System.err.println("Element Found - " + by.toString());
			sleep(100);
		}
	}

	@Override
	public void shouldExists(String selector, int timeoutInSec) {
		if (!Assertion.test(true, exists(selector, timeoutInSec))) {
			System.err.println("Element Not Found, Timeout Reached - " + selector);
			sleep(100);
		}
	}

	@Override
	public void shouldNotExists(String selector, int timeoutInSec) {
		if (!Assertion.test(false, exists(selector, timeoutInSec))) {
			System.err.println("Element Found, Timeout Reached - " + selector);
			sleep(100);
		}
	}

	@Override
	public void shouldExists(By by, int timeoutInSec) {
		if (!Assertion.test(true, exists(by, timeoutInSec))) {
			System.err.println("Element Not Found, Timeout Reached - " + by.toString());
			sleep(100);
		}

	}

	@Override
	public void shouldNotExists(By by, int timeoutInSec) {
		if (!Assertion.test(false, exists(by, timeoutInSec))) {
			System.err.println("Element Found, Timeout Reached - " + by.toString());
			sleep(100);
		}

	}

	private void setTimeout(int sec) {
		this.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
	}

	private void resetTimeout() {
		try {
			this.manage().timeouts().implicitlyWait(getGlobalTimeout(), TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("Driver is null - " + e.getMessage());
		}
	}

	public void sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private List<SelenElement> findElement(String searchType, String searchData, int timeoutInSec, By by) {
		List<WebElement> elements = null;
		try {
			setTimeout(timeoutInSec);

			switch (searchType) {
			case "SELECTOR":
				elements = this.findElementsByCssSelector(searchData);
				break;
			case "XPATH":
				elements = this.findElementsByXPath(searchData);
				break;
			case "BY":
				elements = this.findElements(by);
				break;
			default:
				break;
			}

			List<SelenElement> list = new ArrayList<>();
			for (WebElement webElement : elements) {
				list.add(new SelenElement(this, webElement));
			}
			resetTimeout();
			if (list.size() <= 0) {
				if (searchData.equals("")) {
					throw new UncheckedTimeoutException("Element Not Found : " + by.toString());
				} else {
					throw new UncheckedTimeoutException("Element Not Found : " + searchData);
				}

			}
			return list;
		} catch (Exception e) {
			resetTimeout();
			throw e; // Breaking if there is exception
		}

	}

	@Override
	public void get(String url) {
		if (this.isNewSession()) {
			super.get(url);
		}
	}

	@Override
	public void get(String url, boolean force) {
		if (force) {
			super.get(url);
		} else {
			this.get(url);
		}
	}

	@Override
	public SelenElement any(Object... queryString) {
		return any(0,  queryString);
	}
	
	@Override
	public SelenElement any(List<Object> listQueryString) {
		return any(listQueryString,0);
	}
	
	@Override
	public SelenElement any(int TimeoutInSec, Object... queryString) {
		List<Object> list = new ArrayList<>();
		Collections.addAll(list, queryString);
		return any(list, TimeoutInSec);
	}
	

	@Override
	public SelenElement any(HashMap<String, Object> mapNameAndQueryString) {
		return any(mapNameAndQueryString, 0);
	}


	@Override
	public SelenElement any(HashMap<String, Object> mapNameAndQueryString, int TimeoutInSec) {
		setTimeout(0);
		if(TimeoutInSec==0){
			TimeoutInSec = getGlobalTimeout();
		}
		int iteration = TimeoutInSec * 10; 
		while (iteration-- >0) {
			for (String key : mapNameAndQueryString.keySet()) {
				Object string = mapNameAndQueryString.get(key);
				if (this.exists(string)) {
					resetTimeout();
					SelenElement elm =  $(string);
					elm.setName(key);
					return elm;
				}
				else {
					sleep(100);
				}
			}
		}
		resetTimeout();
		return null;
	}


	@Override
	public SelenElement any(List<Object> listQueryString, int TimeoutInSec) {
		setTimeout(0);
		if(TimeoutInSec==0){
			TimeoutInSec = getGlobalTimeout();
		}
		int iteration = TimeoutInSec * 10; 
		while (iteration-- >0) {
			for (Object string : listQueryString) {
				if (this.exists(string)) {
					resetTimeout();
					return $(string);
				}
				else {
					sleep(100);
				}
			}
		}
		resetTimeout();
		return null;
	}


	public SelenElement executeScript(String script) {
		WebElement obj = (WebElement)super.executeScript(script, null);
		return  new SelenElement(this,obj);
	}


	private SelenElement $(Object byOrStrObj) {
		if(byOrStrObj.getClass().getTypeName().equals("java.lang.String")){
			return $((String)byOrStrObj,0);	
		}
		else{
			return $((By)byOrStrObj,0);	
		}
		
	}

	private boolean exists(Object byOrStrObj) {
		if(byOrStrObj.getClass().getTypeName().equals("java.lang.String")){
			return exists((String)byOrStrObj,0);	
		}
		else{
			return exists((By)byOrStrObj,0);	
		}
	}





}
