package cars.core.fancycars;

import cars.core.Car;

public class Tesla extends Car {
	protected double batteryLife;

	public Tesla(double batteryLife) {
		super(4, "Tesla", 0);
		this.batteryLife = batteryLife;
	}

	public String toString() {
		return "" + numWheels + " " + brand + " " + batteryLife;
	}
}