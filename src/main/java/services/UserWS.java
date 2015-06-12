/**
 * 
 */
package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import persistence.DataManager;

/**
 * @author floriment
 *
 */
@Path("/user")
public class UserWS {
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String identify(@PathParam("id") long id) {
		return DataManager.getUserbyId(id).toString();
	}
	
}
