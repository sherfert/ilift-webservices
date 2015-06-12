package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author floriment
 *
 */
@Path("/equipment")
public class EquipmentWS {
	
	@GET
	@Path("/identify/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String identify(@PathParam("id") long id) {
		return "";
	}
	
	
	
}
