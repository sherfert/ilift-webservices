package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import persistence.DataManager;

/**
 */
@Path("/exercise")
public class ExerciseWS {
	
	Gson gson = new Gson();
	
	@GET
	@Path("/all")
	@Produces(MediaType.TEXT_PLAIN)
	public String identify() {
		return gson.toJson(DataManager.getAllExercises());
	}
	
}
