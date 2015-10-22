package com.softtechlabs.selenquery.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

 class ReusableRemoteWebDriver extends RemoteWebDriver {

	private boolean isNewSession = true;
	private static final String SID_FILE = System.getProperty("java.io.tmpdir") + "seleniumSession.txt";


	 ReusableRemoteWebDriver(SupportedBrowsers browsers, DesiredCapabilities capabilities, boolean NewSession, String driverpath) {
		 buildDriver(browsers, capabilities, NewSession, driverpath, "localhost");
	 }


	private void buildDriver(SupportedBrowsers browsers, DesiredCapabilities capabilities, boolean NewSession, String driverpath, String host){
		String sessionData = null;
		String address;
		if (!NewSession) {
			sessionData = readPreviousSessionId();
		}
		if (sessionData != null) {
			String hostport = sessionData.split("\\|")[0];
			address = hostport;
			String SessionIdStirng = sessionData.split("\\|")[1];
			URL url = null;
			try {
				url = new URL(hostport);
			} catch (MalformedURLException e) {
			}
			CommandExecutor executor = new HttpCommandExecutor(url);
			setCommandExecutor(executor);
			setSessionId(SessionIdStirng);
			try {
				System.out.println(getCurrentUrl());
				isNewSession = false;
			} catch (Exception ex) {
				try {
					startSession(capabilities);
					saveSessionId(address + "|" + getSessionId().toString());
				} catch (Exception ex1) {
					// System.out.println(ex1.getMessage());
					address = startServer(browsers, driverpath, host);
					startSession(capabilities);
					saveSessionId(address + "|" + getSessionId().toString());
				}
			}

		} else {
			address = startServer(browsers, driverpath, host);
			startSession(capabilities);
			saveSessionId(address + "|" + getSessionId().toString());
		}
		
	}

	private String startServer(SupportedBrowsers browsers, String driverpath, String host) {
		int port = PortFinder.findFreePort();
		System.out.println();

		Runtime rt = Runtime.getRuntime();
		try {
			String portstr = "";
			if (browsers == SupportedBrowsers.Chrome) {
				portstr = "--port=" + port;
			} else if (browsers == SupportedBrowsers.InternetExplorer) {
				portstr = "/port=" + port;
			}

			rt.exec(new String[] { driverpath, portstr });

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String address = "http://" + host + ":" + port;
		URL url = null;
		try {
			url = new URL(address);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		CommandExecutor executor = new HttpCommandExecutor(url);
		setCommandExecutor(executor);
		return address;

	}

	private String readPreviousSessionId() {
		String sid = null;
		File sidFile = new File(SID_FILE);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(sidFile));
			sid = reader.readLine();
		} catch (IOException e) {
			// noop
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sid;
	}

	private void saveSessionId(String sid) {
		File sidFile = new File(SID_FILE);
		FileWriter writer = null;
		try {
			writer = new FileWriter(sidFile);
			writer.write(sid);
		} catch (IOException e) {
			System.out.println("could not save session for furture use" + e.getMessage());
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	void setReusedCapabilities(Capabilities capabilities) {
		Field capabilitiesField = getFieldSafely(this, RemoteWebDriver.class);
		writeValueToField(this, capabilitiesField, capabilities);
	}

	private static Field getFieldSafely(Object object, Class<?> clazz) {
		try {
			return clazz.getDeclaredField("capabilities");
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private static void writeValueToField(Object object, Field field, Object value) {
		boolean wasAccessible = field.isAccessible();
		if (!wasAccessible) {
			field.setAccessible(true);
		}
		try {
			field.set(object, value);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		if (!wasAccessible) {
			field.setAccessible(false);
		}
	}

	public boolean isNewSession() {
		return isNewSession;
	}

}
