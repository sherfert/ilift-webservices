package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class EqType {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Exercise> availableExercises;
	
	@Basic
	private String name;
	
	public EqType() {}
	
	public EqType(String name) {
		this.name = name;
		availableExercises = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Exercise> getAvailableExercises() {
		return availableExercises;
	}

	public void addAvailableExercise(Exercise availableExercise) {
		availableExercises.add(availableExercise);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
