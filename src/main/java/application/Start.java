/**
 * 
 */
package application;

import java.io.IOException;

import persistence.DataManager;
import persistence.PersistenceManager;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

import data.Session;

/**
 * @author floriment
 *
 */
public class Start {
	static final String REST_BASE_URI = "http://localhost:8080/ilift/";

	public static void main(String[] args) {

		PersistenceManager.connect();

		DataManager.createDefaultData();

		try {
			ResourceConfig rc = new PackagesResourceConfig("");

			rc.getProperties().put(
					"com.sun.jersey.spi.container.ContainerResponseFilters",
					"services.CrossDomainFilter");

			HttpServer restServer = HttpServerFactory.create(REST_BASE_URI, rc);
			restServer.start();
			System.out.println("Press Enter to stop the server. ");
			System.in.read();
			restServer.stop(0);
			PersistenceManager.disconnect();
			System.exit(0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
