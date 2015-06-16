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
 * @author floriment
 *
 */
@Path("/user")
public class UserWS {
	
	Gson gson = new Gson();
	
	private String userToJson(User user) {
		GsonBuilder gbuilder = new GsonBuilder();
		Gson go = gbuilder.excludeFieldsWithoutExposeAnnotation().create();
		return go.toJson(user);
	}
	
	@GET
	@Path("/{rfid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String show(@PathParam("rfid") String rfid) {
		return userToJson(DataManager.getUserByTag(rfid));
	}
	
	@GET
	@Path("/byID/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String showByID(@PathParam("id") long id) {
		return userToJson(DataManager.getUserById(id));
	}
	
	@GET
	@Path("/byUsername/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@HeaderParam("Access-Control-Allow-Origin:*")
	public String getUserByUsername(@PathParam("name") String username){
		return userToJson(DataManager.getUserByUsername(username));
	}
	
}
