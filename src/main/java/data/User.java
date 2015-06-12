package data;

import java.util.List;

import javax.persistence.CascadeType;
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
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Session> sessions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Session> getSessions() {
		return sessions;
	}	

}
