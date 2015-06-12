package data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(unique = true)
	private String rfidReaderTag;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Session> sessions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRfidReaderTag() {
		return rfidReaderTag;
	}

	public void setRfidReaderTag(String rfidReaderTag) {
		this.rfidReaderTag = rfidReaderTag;
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
