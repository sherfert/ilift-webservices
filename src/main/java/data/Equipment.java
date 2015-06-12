package data;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Equipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private EqType type;
	
	@Column(unique = true)
	private String rfidTag;
	
	@Basic
	private double weightKg;
	
	public Equipment() {}

	public Equipment(EqType type, String rfidTag, double weightKg) {
		this.type = type;
		this.rfidTag = rfidTag;
		this.weightKg = weightKg;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EqType getType() {
		return type;
	}

	public void setType(EqType type) {
		this.type = type;
	}

	public String getRfidTag() {
		return rfidTag;
	}

	public void setRfidTag(String rfidTag) {
		this.rfidTag = rfidTag;
	}

	public double getWeightKg() {
		return weightKg;
	}

	public void setWeightKg(double weightKg) {
		this.weightKg = weightKg;
	}

}
