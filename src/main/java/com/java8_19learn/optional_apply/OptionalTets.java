package com.java8_19learn.optional_apply;

import java.util.Optional;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-07-09 9:43
 * @Modified By:
 **/
public class OptionalTets {
	public static void main (String[] args) {
		try {
			Car car = new Car ();
			Optional<Car> optionalCar = Optional.ofNullable (car);
			System.out.println (optionalCar);
			if (optionalCar.isPresent ()){
				Car car_ = optionalCar.get ();
				System.out.println (car_);
			}
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}

	private <T> T getOptional(T t){
		return t;
	}
}
