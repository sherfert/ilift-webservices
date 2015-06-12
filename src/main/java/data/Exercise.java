package data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Exercise {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Basic
	private String name;

	public Exercise() {}
	
	public Exercise(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
