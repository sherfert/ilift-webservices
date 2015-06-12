package data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Session {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST, optional = false)
	private User user;
	
	@ManyToOne(cascade = CascadeType.PERSIST, optional = false)
	private Exercise exercise;
	
	@ManyToOne(cascade = CascadeType.PERSIST, optional = false)
	private Equipment equipment;
	
	public Session() {}
	
	public Session(Exercise exercise, Equipment equipment, User user) {
		this.exercise = exercise;
		this.equipment = equipment;
		this.user = user;
	}



	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
