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

/**
 * @author floriment
 *
 */
@Path("/user")
public class UserWS {
	
	Gson gson = new Gson();
	
	@GET
	@Path("/{rfid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String show(@PathParam("rfid") String rfid) {
		return gson.toJson(DataManager.getUserByTag(rfid));
	}
	
	@GET
	@Path("/byUsername/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@HeaderParam("Access-Control-Allow-Origin:*")
	public String getUserByUsername(@PathParam("name") String username){
		return gson.toJson(DataManager.getUserByUsername(username));
	}
	
}
