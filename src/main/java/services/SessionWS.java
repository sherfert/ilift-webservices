/**
 * 
 */
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
 * @author floriment
 *
 */
@Path("/session")
public class SessionWS {
	
	Gson gson = new Gson();
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String show(@PathParam("id") long id) {
		// XXX not implemented
		return "";
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public void create(String body){
		Session session = gson.fromJson(body, Session.class);
		DataManager.insertSession(session);
	}
	
	@GET
	@Path("/byUserId/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	@HeaderParam("Access-Control-Allow-Origin:*")
	public String getSessionsByUserId(@PathParam("uid") long id){
		List<Session> sessions = DataManager.getSessionOfUserById(id); 
		GsonBuilder gbuilder = new GsonBuilder();
		Gson go = gbuilder.excludeFieldsWithoutExposeAnnotation().create();
		return go.toJson(sessions);
	}
	
	@GET
	@Path("/sessionCounts/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@HeaderParam("Access-Control-Allow-Origin:*")
	public String getSessionCounts(@PathParam("name") String name) {
		List<StringLongTuple> sessionCounts = DataManager.getSessionCountsOfUserByName(name);
		return gson.toJson(sessionCounts);
	}
}
