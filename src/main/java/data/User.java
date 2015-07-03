package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.google.gson.annotations.Expose;

/**
 * Represents a user. It has an RFID tag (his gym card), a username, and a list
 * of session the User performed.
 * 
 * @author satia
 */
@Entity
@Cacheable(false)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Expose
	private long id;

	@Column(unique = true)
	@Expose
	private String rfidTag;

	@Column(unique = true)
	@Expose
	private String username;

	@Expose
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@OrderBy("date DESC")
	private List<Session> sessions;

	public User() {
	}

	public User(String rfidTag, String username) {
		this.rfidTag = rfidTag;
		this.username = username;
		sessions = new ArrayList<Session>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRfidTag() {
		return rfidTag;
	}

	public void setRfidTag(String rfidTag) {
		this.rfidTag = rfidTag;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	/**
	 * Adds a session
	 * 
	 * @param session
	 *            the session
	 */
	public void addSession(Session session) {
		sessions.add(session);
	}

}
