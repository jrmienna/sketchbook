package exercises14.exercise08.train;

public class PassengerCar extends TrainCar {

	private int passangerCount;
	private static int PASSANGER_WEIGHT = 80; //kg
	
	public PassengerCar(int deadWeight, int passangers) {
		super(deadWeight);
		this.passangerCount = passangers;
	}
	
	@Override
	public int getTotalWeight() {
		return super.getDeadWeight() + PASSANGER_WEIGHT*passangerCount;
	}
	
	public int getPassengerCount() {
		return passangerCount;
	}
	public void setPassengerCount(int passangers) {
		this.passangerCount = passangers;
	}
	
}
