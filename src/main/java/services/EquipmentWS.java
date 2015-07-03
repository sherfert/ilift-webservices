package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import persistence.DataManager;

/**
 * Web service for the equipments.
 * 
 * @author floriment
 */
@Path("/equipment")
public class EquipmentWS {
	
	private Gson gson = new Gson();
	
	/**
	 * Gets an equipment by RFID tag.
	 * @param rfid the RFID tag.
	 * @return the equipment JSON.
	 */
	@GET
	@Path("/{rfid}")
	@Produces(MediaType.TEXT_PLAIN)
	public String show(@PathParam("rfid") String rfid) {
		return gson.toJson(DataManager.getEquipmentByTag(rfid));
	}
	
}
