/**
 * 
 */
package data;

/**
 * @author satia
 *
 */
public class RepetitionObj {

	private String exerciseName;
	private double weightKg;
	private String eqTypeName;
	public RepetitionObj(String exerciseName, double weightKg, String eqTypeName) {
		this.exerciseName = exerciseName;
		this.weightKg = weightKg;
		this.eqTypeName = eqTypeName;
	}
	public String getExerciseName() {
		return exerciseName;
	}
	public double getWeightKg() {
		return weightKg;
	}
	public String getEqTypeName() {
		return eqTypeName;
	}
	@Override
	public String toString() {
		return "RepetitionObj [exerciseName=" + exerciseName + ", weightKg="
				+ weightKg + ", eqTypeName=" + eqTypeName + "]";
	}
	
	
}
