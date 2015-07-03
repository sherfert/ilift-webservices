/**
 * 
 */
package services;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import persistence.DataManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import data.User;

/**
 * Web service for the users.
 * 
 * @author floriment
 */
@Path("/user")
public class UserWS {

	/**
	 * Creates the json for a user.
	 * 
	 * @param user
	 *            the user
	 * @return the json representation.
	 */
	private String userToJson(User user) {
		GsonBuilder gbuilder = new GsonBuilder();
		Gson go = gbuilder.excludeFieldsWithoutExposeAnnotation().create();
		return go.toJson(user);
	}

	/**
	 * Gets the by RFID tag
	 * 
	 * @param rfid
	 *            the RFID tag
	 * @return the json user object.
	 */
	@GET
	@Path("/{rfid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String show(@PathParam("rfid") String rfid) {
		return userToJson(DataManager.getUserByTag(rfid));
	}

	/**
	 * Gets the user by database id.
	 * 
	 * @param id
	 *            the id
	 * @return the json user object.
	 */
	@GET
	@Path("/byID/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String showByID(@PathParam("id") long id) {
		return userToJson(DataManager.getUserById(id));
	}

	/**
	 * Get user by username.
	 * 
	 * @param username
	 *            the username.
	 * @return the json user object.
	 */
	@GET
	@Path("/byUsername/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@HeaderParam("Access-Control-Allow-Origin:*")
	public String getUserByUsername(@PathParam("name") String username) {
		return userToJson(DataManager.getUserByUsername(username));
	}

}
