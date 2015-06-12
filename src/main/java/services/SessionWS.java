/**
 * 
 */
package services;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import data.Session;
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
	public String identify(@PathParam("id") long id) {
		return "";
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public void postSession(String body){
		Session session = gson.fromJson(body, Session.class);
		DataManager.insertSession(session);
	}
	
	@GET
	@Path("/byUserId/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	@HeaderParam("Access-Control-Allow-Origin:*")
	public String getSessionsByUserId(@PathParam("uid") long id){
//		gson.toJson(DataManager.getSessionOfUserById(id));
		//TODO create the json representation of this shit.
		System.out.println("request arrived");
		JsonArray arr = new JsonArray();
		JsonObject eq = new JsonObject();
		eq.addProperty("id", 12332);
		eq.addProperty("item", "Item Name");
		eq.addProperty("rfid", "hgff56fhgf65jhgfj6");
		eq.addProperty("weight", 40);

		JsonObject session = new JsonObject();
		session.addProperty("date", "12/7/2015");
		session.add("equipment", eq);
		JsonObject exercise = new JsonObject();
		exercise.addProperty("name", "Bango Bongo");
		session.add("exercise", exercise);
		JsonObject user = new JsonObject();
		user.addProperty("id", id);
		session.addProperty("user", id);
		arr.add(session);
		arr.add(session);
		arr.add(session);
		arr.add(session);
		return arr.toString();
		//"equipment" -> id,item,rfid,weight
		//"exercise" -> name
		//"user" -> user obj.
//		arr.add(new JsonObject());
//		return DataManager.getSessionOfUserById(id).toString();
	}
	
	
}
