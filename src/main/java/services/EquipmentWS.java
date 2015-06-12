package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import persistence.DataManager;

/**
 * @author floriment
 *
 */
@Path("/equipment")
public class EquipmentWS {
	
	Gson gson = new Gson();
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String identify(@PathParam("id") String id) {
		//TODO convert it to Json
		return DataManager.getEquipmentByTag(id).toString();
	}
	
	
	
}
