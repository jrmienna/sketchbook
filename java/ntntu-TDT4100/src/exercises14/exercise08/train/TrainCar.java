package exercises14.exercise08.train;

public class TrainCar {

	private int deadWeight;
	
	public TrainCar(int weight) {
		this.deadWeight = weight;
	}
	public int getTotalWeight(){
		return deadWeight;
	}
	public void setDeadWeight(int deadWeight) {
		this.deadWeight = deadWeight;
	}
	public int getDeadWeight() {
		return deadWeight;
	}
}
