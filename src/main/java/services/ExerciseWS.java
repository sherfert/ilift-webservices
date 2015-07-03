package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import persistence.DataManager;

/**
 * The web serbive for exercises.
 * 
 * @author satia
 */
@Path("/exercise")
public class ExerciseWS {
	
	private Gson gson = new Gson();
	
	/**
	 * @return a json list of all exercises.
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAll() {
		return gson.toJson(DataManager.getAllExercises());
	}
	
}
