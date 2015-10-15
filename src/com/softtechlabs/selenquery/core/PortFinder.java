package com.softtechlabs.selenquery.core;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Finds an available port on localhost.
 */
 class PortFinder {

	// the ports below 1024 are system ports
	private static final int MIN_PORT_NUMBER = 1024;

	// the ports above 49151 are dynamic and/or private
	private static final int MAX_PORT_NUMBER = 49151;

	/**
	 * Finds a free port between {@link #MIN_PORT_NUMBER} and
	 * {@link #MAX_PORT_NUMBER}.
	 *
	 * @return a free port
	 * @throws RuntimeException if a port could not be found
	 */
	public static int findFreePort() {
		for (int i = MIN_PORT_NUMBER; i <= MAX_PORT_NUMBER; i++) {
			if (available(i)) {
				return i;
			}
		}
		throw new RuntimeException("Could not find an available port between " + MIN_PORT_NUMBER + " and " + MAX_PORT_NUMBER);
	}

	private static boolean available(final int port) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
			return true;
		} catch (final IOException e) {
			return false;
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (final IOException e) {
					// can never happen
				}
			}
		}
	}
}
