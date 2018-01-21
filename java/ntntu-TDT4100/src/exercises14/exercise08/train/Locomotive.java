package exercises14.exercise08.train;

import java.util.ArrayList;
import java.util.List;

public class Locomotive {

	private List<TrainCar> locomotive;
	
	public Locomotive() {
		locomotive = new ArrayList<TrainCar>();
	}
	
	public void addTrainCar(TrainCar car) {
		locomotive.add(car);
	}
	public boolean contains(TrainCar car) {
		for(TrainCar trainCar : locomotive) {
			if(trainCar.equals(car)) {
				return true;
			}
		}
		return false;
	}
	public int getTotalWeight() {
		int totalWeight = 0;
		for(TrainCar trainCar : locomotive) {
			totalWeight += trainCar.getTotalWeight();
		}
		return totalWeight;
	}
	
	public int getPassengerCount() {
		int passangerCount = 0;
		for(TrainCar trainCar : locomotive) {
			if(trainCar instanceof PassengerCar) {
				passangerCount += ((PassengerCar) trainCar).getPassengerCount();
			}
		}
		return passangerCount;
	}
	
	public int getCargoWeight() {
		int totalCargoWeight = 0;
		for(TrainCar trainCar : locomotive) {
			if(trainCar instanceof CargoCar) {
				totalCargoWeight += ((CargoCar) trainCar).getCargoWeight();
			}
		}
		return totalCargoWeight;
	}
	
	@Override
	public String toString() {
		String out = "";
		
		for (TrainCar trainCar : locomotive) {
			if(trainCar instanceof PassengerCar) {
				out += "Passanger car:\n" +
						"passangers: " + ((PassengerCar) trainCar).getPassengerCount() + "\n";
			}
			if(trainCar instanceof CargoCar) {
				out += "Cargo car:\n" +
						"cargo weight: " + ((CargoCar) trainCar).getCargoWeight() + "\n";
			}
			out += "total weigth: " + trainCar.getTotalWeight() + "\n";
		}
		return out;
	}
}
