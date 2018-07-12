package cars.core;

public class Car {
	protected int numWheels;
	protected String brand;
	protected double fuelTankSize;

	public Car(int numWheels, String brand, double fuelTankSize) {
		this.numWheels = numWheels;
		this.brand = brand;
		this.fuelTankSize = fuelTankSize;
	}

	public String toString() {
		return "" + numWheels + " " + brand + " " + fuelTankSize;
	}
}