package exercises14.exercise08.train;

import java.util.ArrayList;
import java.util.List;

public class Train {
	
	private List<TrainCar> train;
	
	public Train() {
		train = new ArrayList<TrainCar>();
	}
	
	public void addTrainCar(TrainCar trainCar) {
		train.add(trainCar);
	}
	
	public boolean contains(TrainCar trainCar) {
		return train.contains(trainCar);
	}
	
	public int getTotalWeight() {
		int weight = 0;
		for(TrainCar trainCar : train) {
			weight += trainCar.getTotalWeight();
		}
		return weight;
	}
	
	public int getPassengerCount() {
		int count = 0;
		for(TrainCar trainCar : train) {
			if( trainCar instanceof PassengerCar) {
				count += ((PassengerCar)trainCar).getPassengerCount();
			}
		}
		return count;
	}
	
	public int getCargoWeight() {
		int weight = 0;
		for(TrainCar trainCar : train) {
			if( trainCar instanceof CargoCar) {
				weight += ((CargoCar)trainCar).getCargoWeight();
			}
		}
		return weight;
	}
	
	public String toString() {
		String out = "";
		for (TrainCar trainCar : train) {
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
