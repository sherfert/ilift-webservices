/**
 * 
 */
package application;

import java.io.IOException;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

/**
 * @author floriment
 *
 */
public class Start {
	static final String REST_BASE_URI = "http://localhost:8080/ilift/";
	
	public static void main(String[] args) {
		try {
			HttpServer restServer = HttpServerFactory.create(REST_BASE_URI);
			restServer.start();
			System.out.println("Press Enter to stop the server. ");
			System.in.read();

			restServer.stop(0);
			System.exit(0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
