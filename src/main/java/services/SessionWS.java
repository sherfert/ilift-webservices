package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import data.Session;
import data.StringLongTuple;
import persistence.DataManager;

/**
 * Web service for the sessions.
 * 
 * @author floriment
 */
@Path("/session")
public class SessionWS {

	private Gson gson = new Gson();

	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String show(@PathParam("id") long id) {
		// XXX not implemented, not needed any more
		return "";
	}

	/**
	 * Creates a new Session and inserts it in the database.
	 * 
	 * @param body
	 *            the json session object.
	 */
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public void create(String body) {
		Session session = gson.fromJson(body, Session.class);
		DataManager.insertSession(session);
	}

	/**
	 * Gets sessions by user id.
	 * 
	 * @param id
	 *            the user id
	 * @return the json session list.
	 */
	@GET
	@Path("/byUserId/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	@HeaderParam("Access-Control-Allow-Origin:*")
	public String getSessionsByUserId(@PathParam("uid") long id) {
		List<Session> sessions = DataManager.getSessionOfUserById(id);
		GsonBuilder gbuilder = new GsonBuilder();
		Gson go = gbuilder.excludeFieldsWithoutExposeAnnotation().create();
		return go.toJson(sessions);
	}

	/**
	 * Gets the session counts of all exercises the user did.
	 * 
	 * @param name
	 *            the username
	 * @return the session counts per exercise.
	 */
	@GET
	@Path("/sessionCounts/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@HeaderParam("Access-Control-Allow-Origin:*")
	public String getSessionCounts(@PathParam("name") String name) {
		List<StringLongTuple> sessionCounts = DataManager
				.getSessionCountsOfUserByName(name);
		return gson.toJson(sessionCounts);
	}

	/**
	 * Gets the repetitions for a user and exercise.
	 * 
	 * @param name
	 *            the username
	 * @param exName
	 *            the exercise name
	 * @param limit
	 *            a limit how many last sessions to include
	 * @return the repetitions.
	 */
	@GET
	@Path("/repetitions/{name}/{exName}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	@HeaderParam("Access-Control-Allow-Origin:*")
	public String getRepetitions(@PathParam("name") String name,
			@PathParam("exName") String exName, @PathParam("limit") int limit) {
		List<StringLongTuple> repititions = DataManager.getRepetitions(name, exName,
				limit);
		return gson.toJson(repititions);
	}
}
