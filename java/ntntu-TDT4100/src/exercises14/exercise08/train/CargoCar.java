package exercises14.exercise08.train;

public class CargoCar extends TrainCar {

	private int cargoWeight;
	
	public CargoCar(int deadWeight, int cargoWeight) {
		super(deadWeight);
		this.cargoWeight = cargoWeight;
	}
	
	@Override
	public int getTotalWeight() {
		return super.getDeadWeight() + cargoWeight;
	}
	
	public int getCargoWeight() {
		return cargoWeight;
	}
	public void setCargoWeight(int cargoWeight) {
		this.cargoWeight = cargoWeight;
	}
}
