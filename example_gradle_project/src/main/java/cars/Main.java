package cars;

import cars.core.Car;
import cars.core.fancycars.Tesla;

public class Main {
	public static void main(String[] args) {
		Car firstCar = new Car(4, "Toyota", 72.5);
		System.out.println(firstCar);

		Car secondCar = new Tesla(60.6);
		System.out.println(secondCar);
	}
}